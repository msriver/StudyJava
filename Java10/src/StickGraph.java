import javax.swing.*;
import java.awt.*;

public class StickGraph extends JFrame {
	private MyPanel panel = new MyPanel();
	
	class CorpData {
		int[] years = 
	}
	
	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawLine(100, 100, 100, 700);
			g.drawString("년도", 70, 110);
			g.drawString("2016", 70, 220);
			g.drawString("2017", 70, 340);
			g.drawString("2018", 70, 460);
			g.drawString("2019", 70, 580);
			
			
			g.fillRect(100, 190, 200, 60);
			g.fillRect(100, 310, 320, 60);
			g.fillRect(100, 430, 480, 60);
			g.fillRect(100, 550, 440, 60);
			
			g.drawString("5억", 310, 220);
			g.drawString("8억", 430, 340);
			g.drawString("12억", 590, 460);
			g.drawString("11억", 550, 580);
			
		}
		
	}
	
	public StickGraph() {
		setTitle("막대그래프");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(1000,1000);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new StickGraph();
	}
}
