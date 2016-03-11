package memory;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class MemoryWin extends JFrame  {
	private MainPanel mainPanel = new MainPanel(null);

	public MemoryWin(String title) throws HeadlessException {
		super(title);
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
		MemoryWin win = new MemoryWin("Spiel Memory");

		win.setBounds(0, 0, 500, 480);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
