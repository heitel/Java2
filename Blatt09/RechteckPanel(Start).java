import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class RechteckPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

// Code für Aufgabe 6	
// a + b
//...		
// c
//...		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame win = new JFrame("Rechtecke");
		
		win.setBounds(0, 0, 300, 300);
		win.setVisible(true);
		win.add(new RechteckPanel());
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
