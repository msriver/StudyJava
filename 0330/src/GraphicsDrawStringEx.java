import javax.swing.*;
import java.awt.*;
public class GraphicsDrawStringEx extends JFrame{
	private MyPanel panel = new MyPanel();
	
	public GraphicsDrawStringEx() {
		setTitle("예제 12-2");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setContentPane(panel);		
		
		setSize(250,200);
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawString("자바는 재밌다.", 30, 30);
			g.drawString("얼마나? ", 60, 60);
			g.setColor(Color.RED);
			g.drawLine(20, 20, 100, 100);
			g.fillOval(20, 20, 80, 80);
			g.setColor(Color.GREEN);
			g.fillRect(20, 20, 80, 80);
			g.setColor(Color.BLUE);
			g.fillRoundRect(60, 60, 120, 80, 40, 60);
		}
	}

	
	public static void main(String[] args) {
		new GraphicsDrawStringEx();
	}
}

