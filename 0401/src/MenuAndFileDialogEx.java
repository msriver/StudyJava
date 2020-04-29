import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;

public class MenuAndFileDialogEx extends JFrame {
	private JLabel imageLabel = new JLabel();
	
	public MenuAndFileDialogEx() {
		setTitle("14-9");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(imageLabel);
		createMenu();
		setSize(350,200);
		setVisible(true);
		
	}
	
	private void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		
		openItem.addActionListener(new OpenActionListener());
		fileMenu.add(openItem);
		mb.add(fileMenu);
		setJMenuBar(mb);
	}
	
	
		class OpenActionListener implements ActionListener {
		private JFileChooser chooser;
		
		public OpenActionListener() {
			chooser = new JFileChooser();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg","gif","png");
			chooser .setFileFilter(filter);
			
			int ret = chooser.showOpenDialog(null);
			if(ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지않았습니다.");
				return;
			}
			String filePath = chooser.getSelectedFile().getPath();
			imageLabel.setIcon(new ImageIcon(filePath));
			pack();
		}
		
	}
		public static void main(String[] args) {
			new MenuAndFileDialogEx();
		}
}
