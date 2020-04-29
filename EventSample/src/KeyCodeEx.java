import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyCodeEx extends JFrame {
	private JLabel la = new JLabel();
	
	public KeyCodeEx() {
		setTitle("key code ¿¹Á¦");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		
	}

	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			
			la.setText(e.getKeyText(e.getKeyCode()));
			if(e.getKeyChar)
		}
	}

}
