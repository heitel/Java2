package wuerfel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class WPanel extends JPanel {
	private int zahl = 1;

	public WPanel(int z) {
		zahl = z;
		setBackground(Color.WHITE);
	}

	public void setZahl(int z) {
		zahl = z;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();

		int min = Math.min(width, height);
		
		g.setColor(Color.YELLOW);
		g.fillRect((width-min)/2, (height-min)/2, min - 1, min - 1);
		g.setColor(Color.BLACK);
		
		Rectangle r[] = calcRect(min);
		for (int i = 0; i < r.length; i++) {
			if (paintDot(i)) {
				g.fillOval(r[i].x, r[i].y, r[i].width, r[i].height);
			}
		}

	}

	private boolean paintDot(int i) {
		switch (zahl) {
		case 1:
			if (i == 6) {
				return true;
			}
			break;
		case 2:
			if (i == 0 || i == 5) {
				return true;
			}
			break;
		case 3:
			if (i == 0 || i == 5 || i == 6) {
				return true;
			}
			break;
		case 4:
			if (i == 0 || i == 5 || i == 2 || i == 3) {
				return true;
			}
			break;
		case 5:
			if (i == 0 || i == 5 || i == 2 || i == 3 || i == 6) {
				return true;
			}
			break;
		case 6:
			if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
				return true;
			}
			break;
		}
		return false;
	}

	private Rectangle[] calcRect(int min) {
		int dx = min / 3;
		int dy = min / 3;
		Rectangle r[] = new Rectangle[7];
		Rectangle tmp = new Rectangle(dx / 4 +(getWidth()-min)/2, dy / 4+(getHeight()-min)/2, dx / 2, dy / 2);

		for (int i = 0; i < r.length; i++) {
			r[i] = (Rectangle) tmp.clone();
		}

		r[1].translate(0, dy);
		r[2].translate(0, 2 * dy);
		r[3].translate(2 * dx, 0);
		r[4].translate(2 * dx, dy);
		r[5].translate(2 * dx, 2 * dy);
		r[6].translate(dx, dy);

		return r;
	}

}
