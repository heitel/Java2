package monteCarlo;
import java.awt.HeadlessException;

import javax.swing.JFrame;


public class MonteCarloWin extends JFrame {
	public MonteCarloWin(String title) throws HeadlessException {
		super(title);
		add(new MonteCarloPanel());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MonteCarloWin	win = new MonteCarloWin("Pi mit Regentropfen berechnen");
		
		win.setBounds(0, 0, 400, 422);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
