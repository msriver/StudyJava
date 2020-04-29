import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class HaksaTc extends JFrame {
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;

	JTextField tfId = null;
	JTextField tfName = null;
	JTextField tfDepartment = null;
	JTextField tfAddress = null;
	JTextArea taList = null;

	JButton btnList = null;
	JButton btnInsert = null;
	JButton btnUpdate = null;
	JButton btnDelete = null;

	JButton btnSearch = null;

	String[] colnames = {"�й�", "�̸�", "�а�"};
	DefaultTableModel model = new DefaultTableModel(colnames, 0);
	JTable table = new JTable(model);

	public HaksaTc() {
		this.setTitle("�л����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//jdbc����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");// ����

			stmt = conn.createStatement();
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		//����������� Connection Close
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		});

		Container c = this.getContentPane();
		c.setLayout(new FlowLayout());

		c.add(new JLabel("�й�"));
		this.tfId = new JTextField(14);
		c.add(this.tfId);

		this.btnSearch = new JButton("�˻�");
		c.add(this.btnSearch);

		c.add(new JLabel("�̸�"));
		this.tfName = new JTextField(20);
		c.add(this.tfName);

		c.add(new JLabel("�а�"));
		this.tfDepartment = new JTextField(20);
		c.add(this.tfDepartment);

		c.add(new JLabel("�ּ�"));
		this.tfAddress = new JTextField(20);
		c.add(this.tfAddress);

		
		table.setPreferredScrollableViewportSize(new Dimension(250, 200));
		add(new JScrollPane(table));

		//table���� ������ �����ϸ� ������ �� �ֵ��� TextField�� ���
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getComponent();// Ŭ���� ���̺� ���ϱ�
				model = (DefaultTableModel) table.getModel();// ���̺��� �� ���ϱ�
				String id = (String) model.getValueAt(table.getSelectedRow(), 0);
				tfId.setText(id);
				String name = (String) model.getValueAt(table.getSelectedRow(), 1);
				tfName.setText(name);
				String dept = (String) model.getValueAt(table.getSelectedRow(), 2);
				tfDepartment.setText(dept);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});

		this.btnList = new JButton("���");
		this.btnInsert = new JButton("���");
		this.btnUpdate = new JButton("����");
		this.btnDelete = new JButton("����");

		//�л��˻�
		this.btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");// ����

					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from student where id='" + tfId.getText() + "'");

					//����ʱ�ȭ
					taList.setText("");

					//�÷������
					taList.append("�й�" + "\t" + "�̸�" + "\t" + "�а�" + "\n");
					taList.append("======================================\n");

					if (rs.next()) {

						taList.append(rs.getString("id") + "\t" + rs.getString("name") + "\t"
								+ rs.getString("deparment") + "\n");
						tfName.setText(rs.getString("name"));
						tfDepartment.setText(rs.getString("department"));
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null)
							rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		});

		//�л����
		this.btnList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				list();
			}
		});

		//�л����
		this.btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user", "hong");// ����

					stmt = conn.createStatement();
					stmt.executeUpdate("insert into student values ('20202019','�����','��������а�','����� ����');");
					

					//������. �޼���� ��ȯ��õ
					rs = stmt.executeQuery("select * from student order by id");

					//����ʱ�ȭ
					taList.setText("");

					//�÷������
					taList.append("�й�" + "\t" + "�̸�" + "\t" + "�а�" + "\n");
					taList.append("======================================\n");

					while (rs.next()) {
						//System.out.print(rs.getString("id")+"\t");
						//System.out.print(rs.getString("name")+"\t");
						//System.out.println(rs.getString("dept"));
						taList.append(rs.getString("id") + "\t" + rs.getString("name") + "\t"
								+ rs.getString("department") + "\n");
					}
					rs.close();
					

					//���ó���Ϸ� �޽��� ���
					JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�");

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		//�л�����
		this.btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					stmt.executeUpdate("update student set name='" + tfName.getText() + "', dept='"
							+ tfDepartment.getText() + "' where id='" + tfId.getText() + "'");
					System.out.println("update student set name='" + tfName.getText() + "', dept='"
							+ tfDepartment.getText() + "' where id='" + tfId.getText() + "'");

					//������. �޼���� ��ȯ��õ
					rs = stmt.executeQuery("select * from student where id='" + tfId.getText() + "'");

					//JTable �ʱ�ȭ
					model.setNumRows(0);

					while (rs.next()) {
						String[] row = new String[3];// �÷��� ������ 3
						row[0] = rs.getString("id");
						row[1] = rs.getString("name");
						row[2] = rs.getString("department");
						model.addRow(row);
					}
					rs.close();

					//���ó���Ϸ� �޽��� ���
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		this.btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg) {
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					//����ó��. DB�����ؼ� ����

					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle", "ora_user",
								"hong");// ����

						stmt = conn.createStatement();
						stmt.executeUpdate("delete from student where id='" + tfId.getText() + "'");
						System.out.println("delete from student where id='" + tfId.getText() + "'");

						//������. �޼���� ��ȯ��õ
						rs = stmt.executeQuery("select * from student where id='" + tfId.getText() + "'");

						//����ʱ�ȭ
						taList.setText("");

						//�÷������
						taList.append("�й�" + "\t" + "�̸�" + "\t" + "�а�" + "\n");
						taList.append("======================================\n");

						while (rs.next()) {
							
							taList.append(rs.getString("id") + "\t" + rs.getString("name") + "\t" + rs.getString("dept")
									+ "\n");
						}
						rs.close();
					

					} catch (Exception e) {
						e.printStackTrace();
					}

					//�Է��׸� �ʱ�ȭ
					tfId.setText("");
					tfName.setText("");
					tfDepartment.setText("");

					//����ó���Ϸ� �޽��� ���
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�");

				}

			}
		});

		c.add(this.btnList);
		c.add(this.btnInsert);
		c.add(this.btnUpdate);
		c.add(this.btnDelete);

		this.setSize(295, 500);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void list() {
		try {
			System.out.println("����Ǿ����ϴ�.....");

			// Select�� ����
			ResultSet rs = stmt.executeQuery("select * from student");

			// JTable �ʱ�ȭ
			model.setNumRows(0);

			while (rs.next()) {
				String[] row = new String[3];// �÷��� ������ 3
				row[0] = rs.getString("id");
				row[1] = rs.getString("name");
				row[2] = rs.getString("department");
				model.addRow(row);
			}
			rs.close();

		} catch (Exception e1) {
			// e.getStackTrace();
			System.out.println(e1.getMessage());
		}
	}

	public static void main(String[] args) {
		new HaksaTc();

	}

}