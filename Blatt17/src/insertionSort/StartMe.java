package insertionSort;
import javax.swing.JFrame;


public class StartMe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Sort-Display");
		f.setBounds(0, 0, 600, 550);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new BalkenPanel());
	}
}
