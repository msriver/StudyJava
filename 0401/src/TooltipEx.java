import javax.swing.*;
import java.awt.*;


public class TooltipEx extends JFrame {
	private Container c;
	public TooltipEx() {
		setTitle("��������");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		createToolBar();
		setSize(400,150);
		setVisible(true);
	}
	
	private void createToolBar() {
		JToolBar bar = new JToolBar("my menu");
		bar.setBackground(Color.LIGHT_GRAY);
		JButton newbtn = new JButton("New");
		newbtn.setToolTipText("������ �����մϴ�.");
		bar.add(newbtn);
		
		JButton openBtn = new JButton(new ImageIcon("images/open.jpg"));
		openBtn.setToolTipText("������ ���ϴ�.");
		bar.add(openBtn);
		bar.addSeparator();
		
		JButton saveBtn = new JButton(new ImageIcon("images/save.jpg"));
		saveBtn.setToolTipText("������ �����մϴ�.");
		bar.add(saveBtn);
		bar.addSeparator();
		bar.add(new JLabel("search"));
		bar.addSeparator();
		JTextField tf = new JTextField("text field");
		tf.setToolTipText("ã���� �ϴ� ���ڿ��� �Է��ϼ���");
		bar.add(tf);
		c.add(bar,BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		new TooltipEx();
	}
}
