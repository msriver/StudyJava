import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ComboActionEx extends JFrame {
	private String[] fruits = {
			"apple", "banana", "kiwi", "mango"
	};
	private ImageIcon[] images = {
			new ImageIcon("images/apple.jpg"),
			new ImageIcon("images/banana.jpg"),
			new ImageIcon("images/kiwi.jpg"),
			new ImageIcon("images/mango.jpg")
	};
	private JLabel imgLabel = new JLabel(images[0]);
	private JComboBox<String> strCombo = new JComboBox<>(fruits);
	
	public ComboActionEx() {
		setTitle("예제 11-13//콤보박스 & 이벤트리스너");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(strCombo);
		c.add(imgLabel);
		
		strCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JComboBox<String> jb = (JComboBox<String>)e.getSource();
				int index = jb.getSelectedIndex();
				imgLabel.setIcon(images[index]);
			}
		});
		
		setSize(300,250);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new ComboActionEx();
	}
	
}
