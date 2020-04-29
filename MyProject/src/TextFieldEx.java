import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 

public class TextFieldEx extends JFrame {

	JTextField tfId = null;

	JTextField tfName = null;

	JTextField tfDepartment = null;

	JTextField tfAddress = null;

	JTextArea taList = null;

	

	JButton btnList = null;

	JButton btnInsert = null;

	JButton btnUpdatae = null;

	JButton btnDelete = null;

	

	

	public TextFieldEx() {

		setTitle("���� 11-8���� // �л�������α׷�");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();

		c.setLayout(new FlowLayout());

		

		c.add(new JLabel("�̸�"));

		tfName = new JTextField(20);

		c.add(tfName);

		

		c.add(new JLabel("�а�"));

		this.tfDepartment = new JTextField(20);

		c.add(this.tfDepartment);

		

		c.add(new JLabel("�ּ�"));

		this.tfAddress = new JTextField(20);

		c.add(tfAddress);

		

		c.add(new JLabel("�й�"));

		this.tfId = new JTextField(20);

		c.add(tfId);

		

		this.taList = new JTextArea(10,23);

		c.add(new JScrollPane(this.taList));

		

		btnList = new JButton("���");

		btnInsert = new JButton("�Է�");

		btnDelete = new JButton("����");

		btnUpdatae = new JButton("����");

		btnUpdatae.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "����Ͻðڽ��ϱ�?","Confirm",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					//����ó��. DB�����ؼ� ����
					//����ó���Ϸ� �޼��� ���
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.","�̰��� �޼������̾Ʒα�!!", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		c.add(this.btnList);

		c.add(this.btnInsert);

		c.add(this.btnDelete);

		c.add(this.btnUpdatae);

		

		

		setSize(300,500);

		setVisible(true);

	}

	

	

	

	public static void main(String[] args) {

		new TextFieldEx();

	}

}