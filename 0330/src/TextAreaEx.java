import javax.swing.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class TextAreaEx extends JFrame {
	private JTextField tf = new JTextField(20);
	private JTextArea ta = new JTextArea(7,20);
	
	public TextAreaEx() {
		setTitle("���� 11-9");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("�Է��� ����Ű �Է�"));
		c.add(tf);
		c.add(new JScrollPane(ta));
		
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField j = (JTextField)e.getSource();
				ta.append(j.getText() + "\n");
				j.setText("");
			}
		});
		
		setSize(300,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TextAreaEx();
	}
}
