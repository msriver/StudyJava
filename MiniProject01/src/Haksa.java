import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Haksa extends JFrame {
	JPanel panel;
	static Connection conn;
	static Statement stmt;
	static ResultSet srs;
	
	private Student student = null;
	private BookRent bookRent = null;
	private Login login = null;
	private Status status = null;
	private DigitalClock dc = null;
	
	private ImageIcon icon = new ImageIcon("img/loginicon.png");
	private JLabel imageLabel = new JLabel();
	
	
	String id;
	String pw;
	
	public Haksa() {
		this.setTitle("학사관리");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//연결
		connectDb();
		
		//창을 닫을시 연결 해제
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					if (srs != null)
						srs.close();
					if (stmt != null)
						stmt.close();
					if (conn != null)
						conn.close();
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			@Override
			public void windowOpened(WindowEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				imageLabel.setIcon(icon);
				panel.add(imageLabel);
				imageLabel.setBounds(136, 1, 300, 300);
				login = new Login();
				panel.add(login);
				panel.setLayout(null);
				login.setLocation(100, 250);
			}
		});
		
			JMenuBar bar = new JMenuBar();

			JMenu m_student = new JMenu("학생관리");// File메뉴
			bar.add(m_student);
			JMenu m_book = new JMenu("도서관리");// Edit메뉴
			bar.add(m_book);
			JMenu m_status = new JMenu("차트집계");
			bar.add(m_status);

			JMenuItem mi_list = new JMenuItem("학생정보");
			m_student.add(mi_list);
			
			mi_list.addActionListener(new ActionListener() {
				@Override
				/*
				 * 아래 코드는 거의 공식과도 같다. 잘 기억해두자.
				 */
				public void actionPerformed(ActionEvent arg0) {
					if(Login.loginSection) {
						if (DigitalClock.stop == false)
							DigitalClock.stop = true;
						panel.removeAll();
						panel.revalidate();
						panel.repaint();
						student = new Student();
						panel.add(student);
						panel.setLayout(null);
						student.setBounds(110,45,280,500);
					} else {
						JOptionPane.showMessageDialog(null,"권한이 없습니다.");
					}
				}
			});

			JMenuItem mi_bookRent = new JMenuItem("대출목록");
			m_book.add(mi_bookRent);
			mi_bookRent.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if(Login.loginSection) {
						panel.removeAll();
						panel.revalidate();
						panel.repaint();
						bookRent = new BookRent();
						panel.add(bookRent);
						panel.setLayout(null);
						bookRent.setLocation(20, 20);;
					} else {
						JOptionPane.showMessageDialog(null, "권한이 없습니다.");
					}
					

				}
			});

			JMenuItem mi_chart = new JMenuItem("도서대출현황차트");
			m_status.add(mi_chart);
			mi_chart.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(Login.loginSection) {
						panel.removeAll();
						panel.revalidate();
						panel.repaint();
						status = new Status();
						panel.add(status);
						panel.setLayout(null);
						status.setLocation(5, 60);
					} else {
						JOptionPane.showMessageDialog(null, "권한이 없습니다.");
					}
				}
			});
			
			
			panel = new JPanel();
			this.add(panel);

			this.setJMenuBar(bar);
			this.setSize(550, 600);
			this.setLocationRelativeTo(null); // 해상도에 상관없이 화면 가운데에 창을 띄어준다.
			this.setVisible(true);
		
		
		
	}

	static void connectDb() {
		// DB연결
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");
					stmt = conn.createStatement();
					System.out.println("DB연결 완료##");
				} catch (ClassNotFoundException e) {
					System.out.println("JDBC 드라이버 로드 에러");
				} catch (SQLException e) {
					System.out.println("DB 연결 에러");
				}
	}
	
	
	public static void main(String[] args) {
		//new Login();
			new Haksa();
	}
}
