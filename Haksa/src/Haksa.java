import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
//temp
public class Haksa extends JFrame {
	//DB������ ���� ��������
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet srs = null;
	
	//�Է��ʵ�
	JTextField tfId = null;
	JTextField tfName = null;
	JTextField tfDepartment = null;
	JTextField tfAddress = null;
	
	//������ ����� JTable���� ������
	String[] colnames = {"�й�", "�̸�", "�а�","�ּ�"};
	DefaultTableModel model;
	JTable table;
	
	//C.R.U.D ��ư��
	JButton btnList = null;
	JButton btnInsert = null;
	JButton btnUpdate = null;
	JButton btnDelete = null;
	
	JButton btnSearch = null;
	
	public Haksa() {
		//DB����
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
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
					try {
						if(srs!=null) srs.close();
						if(stmt!=null) stmt.close();
						if(conn!=null) conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		setTitle("�л�������α׷� v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		c.add(new JLabel("�й�"));
		tfId = new JTextField(14);
		c.add(tfId);
		
		btnSearch = new JButton("�˻�");
		c.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) { 
					JOptionPane.showMessageDialog(null, "�й��� �Է��ϼ���");
					return;
				}
				try {
					srs = stmt.executeQuery("select * from student_copy where id like '"+tfId.getText()+"%'");
					model.setNumRows(0);
					while(srs.next()) {
						String[] tempRows = new String[4];
						tempRows[0] = srs.getString("id");
						tempRows[1] = srs.getString("name");
						tempRows[2] = srs.getString("department");
						tempRows[3] = srs.getString("address");
						model.addRow(tempRows);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		c.add(new JLabel("�̸�"));
		this.tfName = new JTextField(20);
		c.add(this.tfName);

		c.add(new JLabel("�а�"));
		this.tfDepartment = new JTextField(20);
		c.add(tfDepartment);

		c.add(new JLabel("�ּ�"));
		this.tfAddress = new JTextField(20);
		c.add(tfAddress);

		model = new DefaultTableModel(colnames, 0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		c.add(new JScrollPane(table));
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable) e.getComponent();
				model = (DefaultTableModel) table.getModel();
				String id = (String) model.getValueAt(table.getSelectedRow(), 0);
				tfId.setText(id);
				String name = (String) model.getValueAt(table.getSelectedRow(), 1);
				tfName.setText(name);
				String dept = (String) model.getValueAt(table.getSelectedRow(), 2);
				tfDepartment.setText(dept);
				String adrs = (String) model.getValueAt(table.getSelectedRow(), 3);
				tfAddress.setText(adrs);
			}
		});
		
		btnList = new JButton("���");
		btnInsert = new JButton("�Է�");
		btnDelete = new JButton("����");
		btnUpdate = new JButton("����");
		
		//��Ϲ�ư ������ ������ �����ͼ� ���. �����Ͱ� ������� �ʴ� �̻� ����� x
		btnList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)  {
					list();
			}
		});
		
		//�Է¹�ư ���� �� ������ �Է�. ��ĭ�̶� ��ĭ�� ������ �޼��� ����
		btnInsert.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				String dept = tfDepartment.getText();
				String addr = tfAddress.getText();
				String id = tfId.getText();
				
				if(checkVacuum(name, dept, addr, id)) {
					// DB�� INSERT ������ ������ ����ֱ�
					try {
						stmt.executeUpdate("insert into student_copy values ('"+id+"', '"+name+"', '"+dept+"', '"+addr+"')");
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "�й��� �����ʵ��Դϴ�. �Է��� ������ �ٽ� Ȯ�����ּ���");
						return;
					} 
					JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.");
					list();
					tfName.setText("");
					tfDepartment.setText("");
					tfAddress.setText("");
					tfId.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "��ĭ���� �ۼ����ּ���");
				}
			}
		});
		
		//������ư ������ ����.
		btnDelete.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "����Ͻðڽ��ϱ�?","Confirm",JOptionPane.YES_NO_OPTION);

				if(result == JOptionPane.YES_OPTION) {

					try {
						stmt.executeUpdate("delete from student_copy where id = '"+tfId.getText()+"'");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
					//����ó���Ϸ� �޼��� ���
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.","Delete Success", JOptionPane.WARNING_MESSAGE);
					list();
				}
			}
		});

		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					srs = stmt.executeQuery("update student_copy set name = '"+tfName.getText()+"', department = '"+tfDepartment.getText()+"', address = '"+tfAddress.getText()+"' where id = '"+tfId.getText()+"'");
					JOptionPane.showMessageDialog(null, "�����Ϸ�.");
					list();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		c.add(this.btnList);
		c.add(this.btnInsert);
		c.add(this.btnDelete);
		c.add(this.btnUpdate);

		setSize(550,500);
		//setResizable(false);
		setVisible(true);
	}

	private boolean checkVacuum(String name,String dept,String addr,String id) {
		if(name.equals("") || dept.equals("") || addr.equals("") || id.equals("")) {
			return false;
		} else {
			return true;
		}
	}
	
	public void list() {
		try {
			ResultSet rs = stmt.executeQuery("select * from student_copy order by id");
			model.setNumRows(0);
			while(rs.next()) {
				String[] tempRows = new String[4];
				tempRows[0] = rs.getString("id");
				tempRows[1] = rs.getString("name");
				tempRows[2] = rs.getString("department");
				tempRows[3] = rs.getString("address");
				model.addRow(tempRows);
			}
			tfId.setText("");
			tfName.setText("");
			tfDepartment.setText("");
			tfAddress.setText("");
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new Haksa();
	}
}