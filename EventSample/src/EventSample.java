import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


//외부클래스로 만드는 방법, 메인메서드 존재클래스 외부에 
class MyAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) { // e는 이벤트가 발생하면 자동으로 전달되는 ActionEvent 
		//Action이벤트가 발생될 때 처리할 작업
		//System.out.println("외부클래스");
		JButton text = (JButton)e.getSource();
		if(text.getText().equals("버튼1")) {
			text.setText("button1");
		} else {
			text.setText("버튼1");
		}
	}
	
}



public class EventSample extends JFrame implements ActionListener { //메인메서드가 있는클래스에 ActionListener 인터페이스를 상속받아 직접 오버라이딩 한다.

	public EventSample() {
		this.setTitle("Event");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn1 = new JButton("버튼1");
		JButton btn2 = new JButton("버튼2");
		JButton btn3 = new JButton("버튼3");
		JButton btn4 = new JButton("버튼4");
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		c.add(btn4);
		btn1.addActionListener(new MyAction());
		btn2.addActionListener(new MyAction2());
		btn4.addActionListener(this); // 클래스자신 4번째 방법을 이용해 버튼에 리스너를 연결한 모습
		btn3.addActionListener(new ActionListener() {
			//코드가 짧고 한군데서만 사용하는 경우에 익명클래스를 쓰면 편하다.
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("익명클래스");
				
			}});
		
		
		this.setSize(500,500);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new EventSample();
	}

	
	//내부클래스로 작성한것
	class MyAction2 implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("내부클래스");
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("클래스자신");
	}
}
