import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;


public class TimeClock extends JFrame {
	JLabel timeLabel = new JLabel("zzzzzzzzzzzzz");
	String hour,min,sec;
	
	public TimeClock() {
		this.setTitle("리스너를 여러가지방법으로 구현해보자");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		
		
		c.add(timeLabel, BorderLayout.CENTER);
		
		JButton bt1 = new JButton("외부클래스");
		JButton bt2 = new JButton("내부클래스");
		JButton bt3 = new JButton("익명클래스");
		JButton bt4 = new JButton("메인클래스가 직접 상속");
		
		bt1.addActionListener(new MyAction(this));
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		btnPanel.add(bt1);
		btnPanel.add(bt2);
		btnPanel.add(bt3);
		btnPanel.add(bt4);
		
		c.add(btnPanel, BorderLayout.SOUTH);
		
		this.setSize(600,500);
		this.setVisible(true);
	}
	
	
	public void timesetting() {
		Calendar now = Calendar.getInstance();
		hour = Integer.toString(now.get(Calendar.HOUR_OF_DAY));
		min = Integer.toString(now.get(Calendar.MINUTE));
		sec = Integer.toString(now.get(Calendar.SECOND));
	}
	
	
	
	
	public static void main(String[] args) {
		new TimeClock();
	}
}




class MyAction implements ActionListener {
	private TimeClock t;
	
	public MyAction(TimeClock t) {
		this.t = t;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		t.timesetting();
		t.timeLabel.setText(t.hour + ":" + t.min + ":" + t.sec);
	}
	
}
