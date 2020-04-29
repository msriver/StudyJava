import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Gallery extends JFrame {
	private JButton[] buttons = new JButton [2];
	private ImageIcon[] btnicons = {
			new ImageIcon("images/leftarrow.png"),	
			new ImageIcon("images/rightarrow.png")};
	private ImageIcon[] images = {
			new ImageIcon("images/apple.jpg"),
			new ImageIcon("images/pear.jpg"),
			new ImageIcon("images/cherry.jpg")};
	private JLabel imageLabel = new JLabel();
	private int page = 0;
	
	public Gallery() {
		this.setTitle("Gallery");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(Color.GRAY);
		
		
		for(int i=0;i<buttons.length; i++) {
			buttons[i] = new JButton(btnicons[i]);
			
			btnPanel.add(buttons[i]);
		}
		
		imageLabel.setIcon(images[page]);
		c.add(btnPanel, BorderLayout.NORTH);
		c.add(imageLabel, BorderLayout.CENTER);
		
		this.setSize(250, 200);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Gallery();
	}
	
	class MyAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
}