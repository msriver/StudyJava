import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseListenerAllEx extends JFrame {
	private JLabel la = new JLabel("no mouse event");
	
	public MouseListenerAllEx() {
		setTitle ("mouse리스너와 모션리스너");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.setLayout(new FlowLayout());
		MyMouse mouse = new MyMouse();
		c.addMouseListener(mouse);
		c.addMouseMotionListener(mouse);
		
		c.add(la);
		this.setSize(300,200);
		this.setVisible(true);
		
		
	}
	
	class MyMouse implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			la.setText("mouse dragged (" + e.getX() + "," + e.getY() + ")");
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			la.setText("mouse moved (" + e.getX() + "," + e.getY() + ")");
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			Component c = (Component)e.getSource();
			c.setBackground(Color.CYAN);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			Component c = (Component)e.getSource();
			c.setBackground(Color.YELLOW);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			la.setText("mouse pressed (" + e.getX() + "," + e.getY() + ")");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			la.setText("mouse released (" + e.getX() + "," + e.getY() + ")");
		}
		
	}

	public static void main(String[] args) {
		new MouseListenerAllEx();
	}
}
