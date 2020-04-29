import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DigitalClock extends JFrame {
	private BackgroundPanel backPanel = new BackgroundPanel();
	private JLabel hourDisplay = new JLabel("H");
	private JLabel minDisplay = new JLabel("M");
	private JLabel secDisplay = new JLabel("S");
	private JLabel[] tt = {
			new JLabel(":"),
			new JLabel(":")
	};
	private static Integer hour,min,sec;
	private Thread th;
	
	public DigitalClock() {
		this.setTitle("Digital Clock");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		backPanel.setLayout(null);
		hourDisplay.setBounds(100, 100, 150, 150);
		minDisplay.setBounds(250, 100, 150, 150);
		secDisplay.setBounds(400, 100, 150, 150);
		
		hourDisplay.setFont(new Font("Gothic", Font.ITALIC, 80));
		minDisplay.setFont(new Font("Gothic", Font.ITALIC, 80));
		secDisplay.setFont(new Font("Gothic", Font.ITALIC, 80));
		
		for(int i =0; i<tt.length; i++) {
			tt[i].setFont(new Font("Gothic", Font.ITALIC, 80));
			tt[i].setBounds(200+(i*150), 100, 150, 150);
			backPanel.add(tt[i]);
		}
		
		backPanel.add(hourDisplay);
		backPanel.add(minDisplay);
		backPanel.add(secDisplay);
		
		ClockThread clock = new ClockThread(this);
		th = new Thread(clock);
		
		c.add(backPanel);
		this.setSize(900,900);
		this.setVisible(true);
		
		th.start();
		ClockThread2 th2 = new ClockThread2(tt);
		th2.start();
	}
	
	void getTime() {
		Calendar now = Calendar.getInstance();
		hour = now.get(Calendar.HOUR_OF_DAY);
		min = now.get(Calendar.MINUTE);
		sec = now.get(Calendar.SECOND);
	}
	
	void setTime() {
		hourDisplay.setText(hour.toString());
		minDisplay.setText(min.toString());
		secDisplay.setText(sec.toString());
	}
	
	void twinkle() {
		
	}
	
	public static void main(String[] args) {
		new DigitalClock();
	}
}

class BackgroundPanel extends JPanel {
	private ImageIcon icon = new ImageIcon("images/background.jpg");
	private Image backImg = icon.getImage();
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(backImg, 0, 0, getWidth(), getHeight(), this);
	}
}

class ClockThread implements Runnable {
	
	DigitalClock dg = null;
	
	public ClockThread(DigitalClock dg) {
		this.dg = dg;
	}
	
	@Override
	public void run() {
		while(true) {
			dg.getTime();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				return;
			}
			dg.setTime();
		}
	}
}

class ClockThread2 extends Thread {
	JLabel[] tt;
	public ClockThread2(JLabel[] tt ) {
		this.tt = tt;
	}
	
	@Override
	public void run() {
		while(true) {
			tt[0].setText("");
			tt[1].setText("");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tt[0].setText(":");
			tt[1].setText(":");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}



