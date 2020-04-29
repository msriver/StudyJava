import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Haksa extends JFrame {
	JPanel panel;
	static Connection conn;
	static Statement stmt;
	static ResultSet srs;
	
	public Haksa() {
		this.setTitle("�л����");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// DB����
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");
			stmt = conn.createStatement();
			System.out.println("DB���� �Ϸ�##");
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC ����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("DB ���� ����");
		}
		//â�� ������ ���� ����
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuBar bar = new JMenuBar();

		JMenu m_student = new JMenu("�л�����");// File�޴�
		bar.add(m_student);
		JMenu m_book = new JMenu("��������");// Edit�޴�
		bar.add(m_book);

		JMenuItem mi_list = new JMenuItem("�л�����");
		m_student.add(mi_list);
		mi_list.addActionListener(new ActionListener() {
			@Override
			/*
			 * �Ʒ� �ڵ�� ���� ���İ��� ����. �� ����ص���.
			 */
			public void actionPerformed(ActionEvent arg0) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				panel.add(new Student());
				panel.setLayout(null);
			}
		});

		JMenuItem mi_bookRent = new JMenuItem("������");
		m_book.add(mi_bookRent);
		mi_bookRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				panel.add(new BookRent());
				panel.setLayout(null);

			}
		});

		panel = new JPanel();
		this.add(panel);

		this.setJMenuBar(bar);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null); // �ػ󵵿� ������� ȭ�� ����� â�� ����ش�.
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Haksa();
	}
}
