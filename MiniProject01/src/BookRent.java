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

		setLayout(null);// 레이아웃설정. 레이아웃 사용 안함.

		JLabel l_dept = new JLabel("학과");
		l_dept.setBounds(10, 10, 30, 20);
		add(l_dept);

		String[] dept = { "전체", "국어국문학과", "기계공학과", "러시아학과", "멀티미디어학부","수학과", "정보보안학과", "컴퓨터공학과" };
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
				int index = jcb.getSelectedIndex(); //선택한 아이템의 인덱스를 구한다.
				
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
		
		String colName[] = { "학번", "이름", "도서명", "대출일" };
		model = new DefaultTableModel(colName, 0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(470, 200));
		add(table);
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(10, 40, 460, 250);
		add(sp);

		setSize(490, 400);// 화면크기
		setVisible(true);

		// 전체목록
		list();
	}

	public void list() {
		try {
			System.out.println("연결되었습니다.....");
			System.out.println(query);
			// Select문 실행
			ResultSet rs = Haksa.stmt.executeQuery(query);

			// JTable 초기화
			model.setNumRows(0);

			while (rs.next()) {
				String[] row = new String[4];// 컬럼의 갯수가 4
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