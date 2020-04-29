import java.awt.*;
import java.awt.event.*;
import java.util.Set;

import javax.swing.*;

class MyDialog extends JDialog {
	private JTextField tfId = new JTextField(10);
	private JPasswordField tfPw = new JPasswordField(10);
	private JButton loginBtn = new JButton("LOGIN");
	
	public MyDialog(JFrame frame, String title) {
		super(frame, title,true);
		setLayout(new FlowLayout());
		add(tfId); add(tfPw); add(loginBtn); 
		this.setSize(200,200);
		
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//로그인처리를 해야한다. 여기서, DB연동, ID, PW 일치여부 확인
				System.out.println("로그인처리...");
				setVisible(false);
			}
		});
	}
}


public class DialogEx extends JFrame {
	private MyDialog dialog;
	
	public DialogEx() {
		this.setTitle("dialog");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JButton btn = new JButton("로그인");
		
		dialog = new MyDialog(this, "login2222");
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
			}
		});
		
		getContentPane().add(btn);
		setSize(250,200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new DialogEx();
	}
}
