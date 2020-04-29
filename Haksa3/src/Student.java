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

public class Student extends JPanel {
	
	
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
	
	public Student() {
		
		
		
		
		this.setLayout(new FlowLayout());

		this.add(new JLabel("�й�"));
		tfId = new JTextField(14);
		this.add(tfId);
		
		btnSearch = new JButton("�˻�");
		this.add(btnSearch);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) { 
					JOptionPane.showMessageDialog(null, "�й��� �Է��ϼ���");
					return;
				}
				try {
					Haksa.srs = Haksa.stmt.executeQuery("select * from student_copy where id like '"+tfId.getText()+"%'");
					model.setNumRows(0);
					while(Haksa.srs.next()) {
						String[] tempRows = new String[4];
						tempRows[0] = Haksa.srs.getString("id");
						tempRows[1] = Haksa.srs.getString("name");
						tempRows[2] = Haksa.srs.getString("department");
						tempRows[3] = Haksa.srs.getString("address");
						model.addRow(tempRows);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.add(new JLabel("�̸�"));
		this.tfName = new JTextField(20);
		this.add(this.tfName);

		this.add(new JLabel("�а�"));
		this.tfDepartment = new JTextField(20);
		this.add(tfDepartment);

		this.add(new JLabel("�ּ�"));
		this.tfAddress = new JTextField(20);
		this.add(tfAddress);

		model = new DefaultTableModel(colnames, 0);
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(260, 200));
		this.add(new JScrollPane(table));
		
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
						Haksa.stmt.executeUpdate("insert into student_copy values ('"+id+"', '"+name+"', '"+dept+"', '"+addr+"')");
						
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
						Haksa.stmt.executeUpdate("delete from student_copy where id = '"+tfId.getText()+"'");
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
					Haksa.srs = Haksa.stmt.executeQuery("update student_copy set name = '"+tfName.getText()+"', department = '"+tfDepartment.getText()+"', address = '"+tfAddress.getText()+"' where id = '"+tfId.getText()+"'");
					JOptionPane.showMessageDialog(null, "�����Ϸ�.");
					list();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		this.add(this.btnList);
		this.add(this.btnInsert);
		this.add(this.btnDelete);
		this.add(this.btnUpdate);

		setSize(280,500);
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
			Haksa.srs = Haksa.stmt.executeQuery("select * from student_copy order by department");
			model.setNumRows(0);
			while(Haksa.srs.next()) {
				String[] tempRows = new String[4];
				tempRows[0] = Haksa.srs.getString("id");
				tempRows[1] = Haksa.srs.getString("name");
				tempRows[2] = Haksa.srs.getString("department");
				tempRows[3] = Haksa.srs.getString("address");
				model.addRow(tempRows);
			}
			tfId.setText("");
			tfName.setText("");
			tfDepartment.setText("");
			tfAddress.setText("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}