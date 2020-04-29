import javax.swing.*;
import java.awt.*;


public class TooltipEx extends JFrame {
	private Container c;
	public TooltipEx() {
		setTitle("툴팁예제");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		createToolBar();
		setSize(400,150);
		setVisible(true);
	}
	
	private void createToolBar() {
		JToolBar bar = new JToolBar("my menu");
		bar.setBackground(Color.LIGHT_GRAY);
		JButton newbtn = new JButton("New");
		newbtn.setToolTipText("파일을 생성합니다.");
		bar.add(newbtn);
		
		JButton openBtn = new JButton(new ImageIcon("images/open.jpg"));
		openBtn.setToolTipText("파일을 엽니다.");
		bar.add(openBtn);
		bar.addSeparator();
		
		JButton saveBtn = new JButton(new ImageIcon("images/save.jpg"));
		saveBtn.setToolTipText("파일을 저장합니다.");
		bar.add(saveBtn);
		bar.addSeparator();
		bar.add(new JLabel("search"));
		bar.addSeparator();
		JTextField tf = new JTextField("text field");
		tf.setToolTipText("찾고자 하는 문자열을 입력하세요");
		bar.add(tf);
		c.add(bar,BorderLayout.NORTH);
	}
	
	public static void main(String[] args) {
		new TooltipEx();
	}
}
