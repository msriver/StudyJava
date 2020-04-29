import javax.swing.*;
import java.awt.*;


public class DrawImage extends JFrame {
	private MyPanel panel = new MyPanel();
	
	class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("images/apple.jpg");
		private Image img = icon.getImage();
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 20, 20, this);
			g.setColor(c);
		}
	}
	
	public DrawImage() {
		this.setTitle("12-6");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(300,420);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DrawImage();
	}
}
