import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


//�ܺ�Ŭ������ ����� ���, ���θ޼��� ����Ŭ���� �ܺο� 
class MyAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) { // e�� �̺�Ʈ�� �߻��ϸ� �ڵ����� ���޵Ǵ� ActionEvent 
		//Action�̺�Ʈ�� �߻��� �� ó���� �۾�
		//System.out.println("�ܺ�Ŭ����");
		JButton text = (JButton)e.getSource();
		if(text.getText().equals("��ư1")) {
			text.setText("button1");
		} else {
			text.setText("��ư1");
		}
	}
	
}



public class EventSample extends JFrame implements ActionListener { //���θ޼��尡 �ִ�Ŭ������ ActionListener �������̽��� ��ӹ޾� ���� �������̵� �Ѵ�.

	public EventSample() {
		this.setTitle("Event");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn1 = new JButton("��ư1");
		JButton btn2 = new JButton("��ư2");
		JButton btn3 = new JButton("��ư3");
		JButton btn4 = new JButton("��ư4");
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		c.add(btn4);
		btn1.addActionListener(new MyAction());
		btn2.addActionListener(new MyAction2());
		btn4.addActionListener(this); // Ŭ�����ڽ� 4��° ����� �̿��� ��ư�� �����ʸ� ������ ���
		btn3.addActionListener(new ActionListener() {
			//�ڵ尡 ª�� �ѱ������� ����ϴ� ��쿡 �͸�Ŭ������ ���� ���ϴ�.
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("�͸�Ŭ����");
				
			}});
		
		
		this.setSize(500,500);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new EventSample();
	}

	
	//����Ŭ������ �ۼ��Ѱ�
	class MyAction2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("����Ŭ����");
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Ŭ�����ڽ�");
	}
}
