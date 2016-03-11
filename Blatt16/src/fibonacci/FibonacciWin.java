package fibonacci;
import java.awt.HeadlessException;

import javax.swing.JFrame;


public class FibonacciWin extends JFrame {
	public FibonacciWin(String title) throws HeadlessException {
		super(title);
		add(new FibonacciPanel());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FibonacciWin	win = new FibonacciWin("Fibonacci-Spirale");
		
		win.setBounds(0, 0, 400, 422);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
