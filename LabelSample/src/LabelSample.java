import javax.swing.*;
import java.awt.*;

public class LabelSample extends JFrame {

	public LabelSample() {
		this.setTitle("���̺���");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		JLabel textLabel = new JLabel("������");
		
		ImageIcon bear = new ImageIcon("images/bear.png");
		JLabel imageLabel = new JLabel(bear);
		
		ImageIcon bearIcon = new ImageIcon("images/bear.png");
		JLabel label = new JLabel("bear zz",bearIcon, SwingConstants.CENTER);
		
		c.add(textLabel);
		c.add(imageLabel);
		c.add(label);
		
		setSize(400,600);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new LabelSample();
			
	}

}
