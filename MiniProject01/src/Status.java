import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Status extends JPanel {
	
	ResultSet rs = null;
	
	private Vector<String> itemName = new Vector<>();
	private Vector<Integer> data = new Vector<>();
	private Vector<Integer> drawAngle = new Vector<>();
	private Color[] color = {
			Color.RED,
			Color.BLUE,
			Color.magenta,
			Color.PINK,
			Color.GREEN,
			Color.CYAN,
			Color.YELLOW
	};
	private ChartPanel chart;
	
	
	public Status() {
		
		
		setLayout(new BorderLayout());
		getInfo();
		chart = new ChartPanel();
		chart.repaint();
		add(chart, BorderLayout.CENTER);
		
		
		
		this.setSize(530,500);
		this.setVisible(true);
	}
	
	public void getInfo() {
		int sum = 0;
		
		try {
			rs = Haksa.stmt.executeQuery("select s.dept, count(br.id) as count\r\n" + 
					"from student s, bookrent br\r\n" + 
					"where s.id = br.id\r\n" + 
					"group by s.dept\r\n"+
					"order by count(br.id) desc");
			int i =0;
			while(rs.next()) {
				itemName.add(rs.getString("dept"));
				data.add(Integer.parseInt(rs.getString("count")));
				sum += data.get(i);
				System.out.println(i+"¹øÂ° " + itemName.get(i) + ", " + data.get(i) +", " + sum);
				i++;
			}
			for(int j=0; j<data.size(); j++) {
				drawAngle.add((int)Math.round((double)data.get(j)/(double)sum*360));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	class ChartPanel extends JPanel {
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int startAngle = 0;
			for(int i =0; i<itemName.size();i++) {
				g.setColor(color[i]);
				g.drawString(itemName.get(i)+" "+(int)Math.round(drawAngle.get(i)/360.0*100.0)+"%", 50, 70+i*60);
				g.fillArc(200, 20, 300, 300, startAngle, drawAngle.get(i));
				startAngle += drawAngle.get(i);
			}
		}
	}
	
	
	
	
}
