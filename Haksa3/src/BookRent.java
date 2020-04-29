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

		query = "select student_copy.id, student_copy.name, book_pt.title, bookRent.rDate" + " from  bookRent, student_copy, book_pt"
				+ " where bookRent.id=student_copy.id" + " and bookRent.bookNo=book_pt.no" + " order by bookRent.no";

		setLayout(null);// ���̾ƿ�����. ���̾ƿ� ��� ����.

		JLabel l_dept = new JLabel("�а�");
		l_dept.setBounds(10, 10, 30, 20);
		add(l_dept);

		String[] dept = { "��ü", "��ǻ�ͽý���", "��Ƽ�̵��", "��ǻ�Ͱ���" };
		JComboBox cb_dept = new JComboBox(dept);
		cb_dept.setBounds(45, 10, 100, 20);
		add(cb_dept);

		cb_dept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				query = "select student_copy.id, student_copy.name, book_pt.title, bookRent.rDate" + 
						" from  bookRent, student_copy,book_pt" + 
						" where bookRent.id=student_copy.id" + " and bookRent.bookNo=book_pt.no";
				JComboBox jcb = (JComboBox) e.getSource();
				int index = jcb.getSelectedIndex(); //������ �������� �ε����� ���Ѵ�.
				
				if(index == 0) {
					query += " order by bookRent.no";
				} else if(index == 1) {
					query += " and student_copy.department = '��ǻ�ͽý���'" + " order by bookRent.no";
				} else if(index == 2) {
					query += " and student_copy.department = '��Ƽ�̵��'" + " order by bookRent.no";
				} else if(index == 3) {
					query += " and student_copy.department = '��ǻ�Ͱ���'" + " order by bookRent.no";
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