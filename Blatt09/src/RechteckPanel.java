import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class RechteckPanel extends JPanel {

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	
// a + b
		Rectangle r[] = new Rectangle[4];
		
		r[0] = new Rectangle(10, 10, 30, 40);
		r[1] = (Rectangle) r[0].clone();
		r[1].translate(30, 0);
		r[2] = (Rectangle) r[0].clone();
		r[2].translate(0, 40);
		r[3] = (Rectangle) r[0].clone();
		r[3].translate(30, 40);
		
		for (int i = 0; i < r.length; i++) {
			g.drawRect(r[i].x, r[i].y, r[i].width, r[i].height);
		}
// c
		Rectangle a1 = new Rectangle(100, 100, 50, 70);
		g.drawRect(a1.x, a1.y, a1.width, a1.height);
		Rectangle a2 = new Rectangle(70, 90, 70, 30);
		g.drawRect(a2.x, a2.y, a2.width, a2.height);
		Rectangle schnitt = a1.intersection(a2);
		g.setColor(Color.RED);
		g.drawRect(schnitt.x, schnitt.y, schnitt.width, schnitt.height);				
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
