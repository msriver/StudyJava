import javax.swing.*;
import java.awt.*;

public class PaintComponentEx extends JFrame {
	public PaintComponentEx() {
		setTitle("new Button // ");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c= getContentPane();
		c.setLayout(new FlowLayout());
		MyButton b = new MyButton("new button");
		b.setOpaque(true); b.setBackground(Color.CYAN);
		c.add(b);
		setSize(250,200);
		setVisible(true);
	}
	
	
	class MyButton extends JButton{
		public MyButton(String s) {
			super(s);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.RED);
			g.drawOval(0, 0, this.getWidth(), this.getHeight());
		}
	}
	
	
	public static void main(String[] args) {
		new PaintComponentEx();
	}
}
