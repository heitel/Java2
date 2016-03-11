package kniffel;
import javax.swing.JFrame;

public class Kniffel {
	public static void main(String[] args) {
		KniffelWin win = new KniffelWin("Kniffel");
		win.setBounds(0, 0, 810, 400);
		win.setVisible(true);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
