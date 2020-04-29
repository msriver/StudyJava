import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MouseListenerEx extends JFrame {
	private JLabel la = new JLabel("Hello");
	
	public MouseListenerEx() {
		this.setTitle("Mouse event 연습");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		c.addMouseListener(new MyMouseListener());
		c.setLayout(null);
		la.setSize(50, 20);
		la.setLocation(30,30);
		c.add(la);
		
		setSize(250, 250);
		setVisible(true);
	}
	
	
	class MyMouseListener implements MouseListener{
		
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
			int x = e.getX();
			int y = e.getY();
			la.setLocation(x,y);    //레이블의 위치를 재조정  
									//x,y는 마우스가 눌려지는 이벤트 발생시 그 이벤트 발생 x,y좌표를 리턴받음
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static void main(String[] args) {
		new MouseListenerEx();
	}
}
