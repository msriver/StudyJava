import javax.swing.*;
import java.awt.*;

public class StickChart extends JFrame {
	private MyPanel panel = new MyPanel();
	
	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawLine(100, 100, 100, 700);
			g.drawLine(100, 700, 700, 700);
			g.drawString("(단위 : 억)", 35, 110);
			g.drawString("(분기)", 670, 720);
			
			g.drawString("1/4", 220, 720);
			g.drawString("2/4", 340, 720);
			g.drawString("3/4", 460, 720);
			g.drawString("4/4", 580, 720);
			
			g.drawString("12", 70, 220);
			g.drawString("9", 70, 340);
			g.drawString("6", 70, 460);
			g.drawString("3", 70, 580);
			
			g.setColor(Color.RED);
			g.drawLine(100, 700, 220, 500);
			g.drawLine(220, 500, 380, 300);
			g.drawLine(380, 300, 460, 220);
			g.drawLine(460, 220, 580, 180);
		}
		
	}
	
	public StickChart() {
		this.setTitle("막대그래프");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(panel);
		
		this.setSize(1000,1000);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new StickChart();
	}
}
