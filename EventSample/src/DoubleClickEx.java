import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class DoubleClickEx extends JFrame {
	public DoubleClickEx() {
		setTitle("double click");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.addMouseListener(new Mymouse());
		this.setSize(300,200);
		this.setVisible(true);
	}
	
	class Mymouse implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getClickCount() == 2) {
				int r = (int)(Math.random()*256);
				int g = (int)(Math.random()*256);
				int b = (int)(Math.random()*256);
				
				Component c = (Component)e.getSource();
				c.setBackground(new Color(r,b,g));
			}
				
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public static void main(String[] args) {
		new DoubleClickEx();
	}
}
