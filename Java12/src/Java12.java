import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Java12 extends JFrame{

	private MyPanel panel=new MyPanel();
	
	public Java12() {
		this.setTitle("Graphic");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(panel, BorderLayout.CENTER);
		createMenu(panel);
		
		this.setSize(500, 500);
		this.setVisible(true);
	}
		
	private void createMenu(MyPanel panel) {
		MyPanel p = panel;
		JMenuBar mb = new JMenuBar();
		JMenu[] menus = {
				new JMenu("미구현1"),
				new JMenu("미구현2"),
				new JMenu("미구현3"),
				new JMenu("설정")
		};
		JMenuItem colorsetting = new JMenuItem("Color");
		menus[3].add(colorsetting);
		
		for(int i=0; i<menus.length; i++) {
			mb.add(menus[i]);
		}
		colorsetting.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				Color userColor = JColorChooser.showDialog(null, "Color pick", Color.black);
				p.userColorPick = userColor;
			}
		});
		setJMenuBar(mb);
	}
	
	class MyPanel extends JPanel{

		private ArrayList<Point> aPoint=new ArrayList<Point>();		
		private ArrayList<Boolean> aPointFlag=new ArrayList<Boolean>();
		private Color userColorPick;

		
		
		Point startP;
		Point endP;
		
		public MyPanel() {
			addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseDragged(MouseEvent e) {
					
					aPoint.add(e.getPoint());
					aPointFlag.add(false);
					repaint();
				}

				@Override
				public void mouseMoved(MouseEvent e) {}
			});
			
			addMouseListener(new MouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					aPoint.add(e.getPoint());
					aPointFlag.add(false);
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					aPointFlag.remove(aPointFlag.size()-1);	
					aPointFlag.add(true);
					repaint();//눌렀다 바로 띈 경우를 위해
					
				}
				@Override
				public void mouseClicked(MouseEvent arg0) {}
				@Override
				public void mouseEntered(MouseEvent e) {}
				@Override
				public void mouseExited(MouseEvent e) {}
				});
		}
		@Override
		protected void paintComponent(Graphics g) {			
			super.paintComponent(g);
			
			for(int i=0;i<aPoint.size();i++) {
				//첫번째 점이거나 이전 점이 마지막 점이면
				if(i==0 || aPointFlag.get(i-1)==true) {
					startP=aPoint.get(i);		
				}				
				
				endP=aPoint.get(i);
				//g.setColor(userColorPick);
				g.drawLine((int)startP.getX(), (int)startP.getY(), (int)endP.getX(), (int)endP.getY());
				startP=endP;				
			}			
		}		
	}
	public static void main(String[] args) {
		new Java12();
	}
}
