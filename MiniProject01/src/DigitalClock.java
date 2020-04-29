import java.awt.Font;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DigitalClock extends JPanel {
	
	JLabel timeLabel = new JLabel();
	public static boolean stop = false;
	
	public DigitalClock() {
	
		this.setSize(310, 300);
		this.setVisible(true);
		//this.setLayout(null);
		
		this.add(timeLabel);
		timeLabel.setFont(new Font("Gothic", Font.ITALIC, 55));
		
		ClockThread clockth = new ClockThread(timeLabel);
		Thread th = new Thread(clockth);
		th.start();
	}
	
	public static void main(String[] args) {
		new DigitalClock();
	}
}

class ClockThread implements Runnable {
	JLabel timeLabel;
	
	public ClockThread(JLabel label) {
		timeLabel = label;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Calendar time = Calendar.getInstance();
		int hour = time.get(Calendar.HOUR_OF_DAY);
		int min = time.get(Calendar.MINUTE);
		int sec = time.get(Calendar.SECOND);
		
		while(!DigitalClock.stop) {
			timeLabel.setText(Integer.toString(hour) + " : " + Integer.toString(min) + " : " + Integer.toString(sec));
			sec++;
			if(sec == 60) {
				sec = 0;
				min += 1;
			} 
			if(min == 60) {
				min = 0;
				hour += 1;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		System.out.println("쓰레드종료");
	}
	
}
