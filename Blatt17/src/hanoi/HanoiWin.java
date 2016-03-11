package hanoi;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class HanoiWin extends JFrame {
	private MainPanel mainPanel = new MainPanel();
	
	public HanoiWin(String title) throws HeadlessException {
		super(title);
		
		buildWindow();
	}

	private void buildWindow() {
		add(mainPanel);
	}
	
	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager
		.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	
		HanoiWin	win = new HanoiWin("Türme von Hanoi");
//		win.setUndecorated(true);
//		win.setExtendedState(JFrame.MAXIMIZED_BOTH);
		win.setBounds(0, 0, 500, 400);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
