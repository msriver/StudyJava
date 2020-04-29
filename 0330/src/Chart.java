import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Chart extends JFrame {

	private MyPanel panel = new MyPanel();
	
	class MyPanel extends JPanel{

		int vGap = 10;
		int height = 20;
		int startX = 100;
		int startY = 100;
		int a = 2*height+height-15;
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
		
			/* 막대그래프 예제
			 * g.drawString("1/4분기", startX-50, startY+a); 
			 * g.drawString("2/4분기", startX-50,
			 * startY+2*a-15); 
			 * g.drawString("3/4분기", startX-50, startY+3*a-30);
			 * g.drawString("4/4분기", startX-50, startY+4*a-45); 
			 * g.fillRect(startX,
			 * startY+height+vGap, 50, height); 
			 * g.fillRect(startX,
			 * startY+height+vGap+height+vGap, 150, height); 
			 * g.fillRect(startX,
			 * startY+height+vGap+height+vGap+height+vGap, 85, height); 
			 * g.fillRect(startX,
			 * startY+height+vGap+height+vGap+height+vGap+height+vGap, 42, height);
			 */
			
			/* 꺾은선 그래프
			 * g.drawLine(50,350,100,300); g.drawLine(100,300,150,260); g.drawLine(150, 260,
			 * 200, 280);
			 */
			
			g.setColor(Color.RED);
			g.fillArc(100, 400, 100, 100, 0, 45);
			g.setColor(Color.GREEN);
			g.fillArc(100, 400, 100, 100, 45, 135);
			g.setColor(Color.black);
			g.fillArc(100, 400, 100, 100, 180, 90);
			g.setColor(Color.blue);
			g.fillArc(100, 400, 100, 100, 270, 90);
		}
		
	}
	
	public Chart() {
		this.setTitle("차트");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(panel);

		
		this.setSize(500,500);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Chart();
	}
}
