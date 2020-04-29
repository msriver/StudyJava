import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookRent extends JPanel {
	DefaultTableModel model = null;
	JTable table = null;


	String query;

	public BookRent() {

		query = "select student.id, student.name, book.title, bookRent.rDate" 
				+ " from  bookRent, student, book"
				+ " where bookRent.id=student.id" 
				+ " and bookRent.bookNo=book.no" 
				+ " order by bookRent.no";

		setLayout(null);// ���̾ƿ�����. ���̾ƿ� ��� ����.

		JLabel l_dept = new JLabel("�а�");
		l_dept.setBounds(10, 10, 30, 20);
		add(l_dept);

		String[] dept = { "��ü", "������а�", "�����а�", "���þ��а�", "��Ƽ�̵���к�","���а�", "���������а�", "��ǻ�Ͱ��а�" };
		JComboBox cb_dept = new JComboBox(dept);
		cb_dept.setBounds(45, 10, 100, 20);
		add(cb_dept);

		cb_dept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				query = "select student.id, student.name, book.title, bookRent.rDate" + 
						" from  bookRent, student , book" + 
						" where bookRent.id=student.id" + " and bookRent.bookNo=book.no";
				JComboBox jcb = (JComboBox) e.getSource();
				int index = jcb.getSelectedIndex(); //������ �������� �ε����� ���Ѵ�.
				
				if(index == 0) {
					query += " order by bookRent.no";
				} else if(index == 1) {
					query += " and student.dept = '"+dept[1]+"'" + " order by bookRent.no";
				} else if(index == 2) {
					query += " and student.dept = '"+dept[2]+"'" + " order by bookRent.no";
				} else if(index == 3) {
					query += " and student.dept = '"+dept[3]+"'" + " order by bookRent.no";
				} else if(index == 4) {
					query += " and student.dept = '"+dept[4]+"'" + " order by bookRent.no";
				} else if(index == 5) {
					query += " and student.dept = '"+dept[5]+"'" + " order by bookRent.no";
				} else if(index == 6) {
					query += " and student.dept = '"+dept[6]+"'" + " order by bookRent.no";
				} else if(index == 7) {
					query += " and student.dept = '"+dept[7]+"'" + " order by bookRent.no";
				}
				list();
			}
		});
		
		String colName[] = { "�й�", "�̸�", "������", "������" };
		model = new DefaultTableModel(colName, 0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(470, 200));
		add(table);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 40, 460, 250);
		add(sp);

		setSize(490, 400);// ȭ��ũ��
		setVisible(true);

		// ��ü���
		list();
	}

	public void list() {
		try {
			System.out.println("����Ǿ����ϴ�.....");
			System.out.println(query);
			// Select�� ����
			ResultSet rs = Haksa.stmt.executeQuery(query);

			// JTable �ʱ�ȭ
			model.setNumRows(0);

			while (rs.next()) {
				String[] row = new String[4];// �÷��� ������ 4
				row[0] = rs.getString("id");
				row[1] = rs.getString("name");
				row[2] = rs.getString("title");
				row[3] = rs.getString("rdate");
				model.addRow(row);
			}
			rs.close();

		} catch (Exception e1) {
			// e.getStackTrace();
			System.out.println(e1.getMessage());
		}
	}

}