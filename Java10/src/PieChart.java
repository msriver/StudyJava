import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class PieChart extends JFrame {
	private String [] itemName = {"apple", "cherry", "strawberry", "prune"};
	private int [] data = new int[4];
	private int [] drawAngle = new int[4];
	private JTextField[] tf = new JTextField[4];
	private JButton startBtn = new JButton("차트그리기");
	private ChartPanel chart = new ChartPanel();
	private Color[] color = {
			Color.RED,
			Color.BLUE,
			Color.magenta,
			Color.YELLOW
	};
	
	class InputPanel extends JPanel {
		public InputPanel() {
			this.setBackground(Color.GRAY);
			for(int i=0; i<itemName.length; i++) {
				this.add(new JLabel(itemName[i]));
				tf[i] = new JTextField(5);
				this.add(tf[i]);
			}
			
			startBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int sum = 0;
					for(int i=0; i<tf.length;i++) {
						try {
							data[i] = Integer.parseInt(tf[i].getText());
							sum += data[i];
						} catch(Exception e1) {
							data[i] = 0;
						}
					}
					for(int i=0; i<tf.length;i++) {
						drawAngle[i] = (int)Math.round((double)data[i]/(double)sum*360);
					}
					chart.repaint();
				}
			});
			this.add(startBtn);
		}
	}
	
	class ChartPanel extends JPanel {
		public ChartPanel() {
			this.setBackground(Color.LIGHT_GRAY);
		}
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			int startAngle = 0;
			for(int i=0; i<itemName.length; i++) {
				g.setColor(color[i]);
				g.drawString(itemName[i]+" "+(int)Math.round(drawAngle[i]/360.0*100.0)+"%", 50+i*150, 50);
				g.fillArc(200, 100, 300, 300, startAngle, drawAngle[i]);
				startAngle += drawAngle[i];
			}
		}
	}
	
	public PieChart() {
		this.setTitle("파이차트만들기");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(new InputPanel(), BorderLayout.NORTH);
		c.add(chart, BorderLayout.CENTER);
		
		setSize(700,500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new PieChart();
	}
}
