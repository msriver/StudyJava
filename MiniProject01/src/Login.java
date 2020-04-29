import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JPanel{
	
	JTextField tfLoginId;
	JPasswordField tfLoginPw;
	
	JLabel idLabel;
	JLabel pwLabel;
	
	JButton loginbtn = new JButton("Login!!");
	
	DigitalClock dg;
	
	public static boolean loginSection = false;
	
	public Login() {
		setLayout(null);
		
		tfLoginId = new JTextField(15);
		tfLoginPw = new JPasswordField(15);
		tfLoginPw.setEchoChar('*');
		
		idLabel = new JLabel("아이디  ");
		pwLabel = new JLabel("비밀번호  ");
		
		add(idLabel);
		add(tfLoginId);
		
		add(pwLabel);
		add(tfLoginPw);
		
		idLabel.setBounds(10, 20, 100, 100);
		pwLabel.setBounds(10, 50, 100, 100);
		
		tfLoginId.setBounds(70, 54, 200, 30);
		tfLoginPw.setBounds(70, 86, 200, 30);
		
		
		add(loginbtn);
		loginbtn.setBounds(96, 158, 100, 50);
		
		loginbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String userid = tfLoginId.getText();
					String userpw = tfLoginPw.getText();
					String cId = new String();
					String cPw=new String();
					
					ResultSet rs = Haksa.stmt.executeQuery("select * from user_info where id = 'admin'");
					
					while(rs.next()) {
						cId = rs.getString("id");
						cPw = rs.getString("pw");
					}
					
						if(!userid.equals(cId)) {
							JOptionPane.showMessageDialog(null, "아이디 및 비밀번호를 다시 확인해주세요");
							return;
						} else if(!userpw.equals(cPw)) {
							JOptionPane.showMessageDialog(null, "아이디 및 비밀번호를 다시 확인해주세요");
							return;
						} else {
							loginSection = true;
							JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다.");
							//setVisible(false);
							removeAll();
							revalidate();
							repaint();
							dg = new DigitalClock();
							add(dg);
							dg.setBounds(5, 10, 300, 290);
						}
					
						rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				
				
			}
		});
		setSize(310, 300);
		setVisible(true);
	}
	
}
