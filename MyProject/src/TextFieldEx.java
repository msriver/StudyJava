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

		setTitle("예제 11-8변형 // 학사관리프로그램");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container c = getContentPane();

		c.setLayout(new FlowLayout());

		

		c.add(new JLabel("이름"));

		tfName = new JTextField(20);

		c.add(tfName);

		

		c.add(new JLabel("학과"));

		this.tfDepartment = new JTextField(20);

		c.add(this.tfDepartment);

		

		c.add(new JLabel("주소"));

		this.tfAddress = new JTextField(20);

		c.add(tfAddress);

		

		c.add(new JLabel("학번"));

		this.tfId = new JTextField(20);

		c.add(tfId);

		

		this.taList = new JTextArea(10,23);

		c.add(new JScrollPane(this.taList));

		

		btnList = new JButton("목록");

		btnInsert = new JButton("입력");

		btnDelete = new JButton("삭제");

		btnUpdatae = new JButton("수정");

		btnUpdatae.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "계속하시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					//삭제처리. DB연동해서 삭제
					//삭제처리완료 메세지 출력
					JOptionPane.showMessageDialog(null, "삭제되었습니다.","이것은 메세지다이아로그!!", JOptionPane.WARNING_MESSAGE);
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