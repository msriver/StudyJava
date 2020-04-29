import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class GraphicsDrawLineMouseEx extends JFrame {
	private MyPanel panel = new MyPanel();
	
	public GraphicsDrawLineMouseEx() {
		setTitle("¿¹Á¦ 12-10");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(panel);
		setSize(300,300);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new GraphicsDrawLineMouseEx();
	}
	
	class MyPanel extends JPanel {
		private Vector <Point> vStart = new Vector<Point>();
		private Vector<Point> VEnd = new Vector<>();
		public MyPanel() {
			this.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					Point startP = e.getPoint();
					vStart.add(startP);
				}
				public void mouseReleased(MouseEvent e) {
					Point endP = e.getPoint();
					VEnd.add(endP);
					repaint();
				}
			});
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			for(int i =0;i<vStart.size();i++) {
				Point s = vStart.elementAt(i);
				Point e = VEnd.elementAt(i);
				g.drawLine((int)s.getX(), (int)s.getY(), (int)e.getX(), (int)e.getY());
			}
		}
	}
}
