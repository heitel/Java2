import java.awt.HeadlessException;

import javax.swing.JFrame;

public class KreisWin extends JFrame  {
	private KreisPanel panel = new KreisPanel();
	
	public KreisWin(String title) throws HeadlessException {
		super(title);
		add(panel);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KreisWin win = new KreisWin("Schnittpunkt zweier Kreise in der Ebene");

		win.setBounds(0, 0, 600, 600);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
