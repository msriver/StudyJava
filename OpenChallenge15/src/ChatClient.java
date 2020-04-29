import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ChatClient extends JFrame implements ActionListener {
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private Socket socket = null;
	private Receiver receiver = null; // JTextArea�� ��ӹް� Runnable �������̽��� ������ Ŭ�����μ� ���� ������ ��� ��ü
	private JTextField sender = null; // JTextField ��ü�μ� ������ ������ ��� ��ü
	private NameDialog dialog;
	private StringBuffer clientName = new StringBuffer();
	private StringBuffer serverName = new StringBuffer();
	
	public ChatClient() {
		
		setTitle("Ŭ���̾�Ʈ ä�� â"); // ������ Ÿ��Ʋ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ ���� ��ư(X)�� Ŭ���ϸ� ���α׷� ����
		
		Container c = getContentPane();
		
		c.setLayout(new BorderLayout()); //BorderLayout ��ġ�������� ���
		receiver = new Receiver(); // �������� ���� �޽����� ����� ���۳�Ʈ
		receiver.setEditable(false); // ���� �Ұ�
		
		sender = new JTextField();
		sender.addActionListener(this);

		c.add(new JScrollPane(receiver),BorderLayout.CENTER); // ��ũ�ѹٸ� ����  ScrollPane �̿�
		c.add(sender,BorderLayout.SOUTH);
		
		dialog = new NameDialog(this, "pick your name", clientName);
		dialog.setVisible(true);
		
		setSize(400, 200); // �� 400 �ȼ�, ���� 200 �ȼ��� ũ��� ������ ũ�� ����
		setVisible(true); // �������� ȭ�鿡 ��Ÿ������ ����
		
		try {
			setupConnection();
		} catch (IOException e) {
			handleError(e.getMessage());
		}
		
		Thread th = new Thread(receiver); // ���κ��� �޽��� ������ ���� ������ ����
		th.start();
	}
	private void setupConnection() throws IOException {
		socket = new Socket("localhost", 9999); // Ŭ���̾�Ʈ ���� ����
		// System.out.println("�����");
		receiver.append("������ ���� �Ϸ�");
		int pos = receiver.getText().length();
		receiver.setCaretPosition(pos); // caret �������� ���� ���������� �̵�
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Ŭ���̾�Ʈ�κ����� �Է� ��Ʈ��
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Ŭ���̾�Ʈ���� ��� ��Ʈ��
		
		out.write(clientName+"\n");
		out.flush();
		serverName.append(in.readLine());
	}


	private static void handleError(String string) {
		System.out.println(string);
		System.exit(1);
	}
	
	private class Receiver extends JTextArea implements Runnable {
		@Override
		public void run() {
			String msg = null;
			while (true) {
				try {
					msg = in.readLine(); // ���κ��� �� ���� ���ڿ� �ޱ�
				} catch (IOException e) {
					handleError(e.getMessage());
				} 
				this.append("\n "  + serverName + " : " + msg); // ���� ���ڿ��� JTextArea�� ���
				int pos = this.getText().length();
				this.setCaretPosition(pos); // caret(Ŀ��)�� ���� ���������� �̵�
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // JTextField�� <Enter> Ű ó��
		if (e.getSource() == sender) {
			String msg = sender.getText(); // �ؽ�Ʈ �ʵ忡 ����ڰ� �Է��� ���ڿ�
			try {
				out.write(msg+"\n"); // ���ڿ� ����
				out.flush();
				
				receiver.append("\n" + clientName + " : " + msg); // JTextArea�� ���
				int pos = receiver.getText().length();
				receiver.setCaretPosition(pos); // caret �������� ���� ���������� �̵�
				sender.setText(null); // �Է�â�� ���ڿ� ����
			} catch (IOException e1) {
				handleError(e1.getMessage());
			} 
		}
	}
	
	class NameDialog extends JDialog {
		private JTextField tfName = new JTextField(10);
		private JButton okButton = new JButton("start!");
		private JLabel label = new JLabel("����� �г��� �Է� ");
		private StringBuffer clientName;
		
		public NameDialog(JFrame frame,String title, StringBuffer Name){
			super(frame, title, true);
			clientName = Name;
			setLayout(new FlowLayout());
			this.setResizable(false);
			this.add(label);
			this.add(tfName);
			this.add(okButton);
			setSize(200,100);
			
			okButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					clientName.append(tfName.getText());
					setVisible(false);
				}
			});
		}
		
		
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}
}
