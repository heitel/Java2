import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class FarbenPanel extends JPanel implements MouseWheelListener{
	//...
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

// Code für Aufgabe 7	
		//...
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rot = e.getWheelRotation();
		
		// Farbsteuerung über Mausrad
		if (rot>0) {
			//...
		}
		else {
			//...
		}
		repaint();// Veranlasse neuzeichnen
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame win = new JFrame("Farben");
		
		win.setBounds(0, 0, 300, 300);
		win.setVisible(true);
		FarbenPanel f = new FarbenPanel();
		f.addMouseWheelListener(f);
		win.add(f);
		win.validate();
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
