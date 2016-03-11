import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;


public class KreisPanel extends JPanel implements MouseMotionListener,
MouseWheelListener{
	private final Color color1 = Color.RED;
	private Point m1 = new Point();
	private int r1 = 200;

	private final Color color2 = new Color(0x8800FF00, true);
	private Point m2 = new Point();
	private int r2 = 200;

	public KreisPanel() {
		super();
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rad = e.getWheelRotation() * 5;
		if (r2 + rad > 0) {
			r2 += rad;
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		m2 = e.getPoint();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();

		// Kreis 1 ist immer in der Mitte des Fensters
		m1.x = width / 2;
		m1.y = height / 2;
		g.setColor(color1);
		drawCircle(g, m1, r1);
		g.setColor(color2);
		drawCircle(g, m2, r2);
		
		g.setColor(Color.BLACK);
		Point s[] = calcSchnittPunkt();
		if (s != null) {
			drawCircle(g, s[0], 5);
			g.setColor(Color.BLACK);
			drawCircle(g, s[1], 5);
		}
	}

	private Point[] calcSchnittPunkt() {
		Point erg[] = null;
		double x1 = m1.x;
		double y1 = m1.y;
		double r1 = this.r1;

		double x2 = m2.x;
		double y2 = m2.y;
		double r2 = this.r2;

		// Berechne Verbindungslinie der Mittelpunkte
		double dx = (x2 - x1);
		double dy = (y2 - y1);

		// Betrag davon
		double d = Math.sqrt(dx * dx + dy * dy);

		// Pythagoras linkes und rechtes Dreieck
		double a = ((r2 * r2 - r1 * r1 + d * d) / (2 * d));

		// Höhe des Dreiecks
		double radikant = r2 * r2 - a * a;
		if (radikant >= 0) {
			double h = Math.sqrt(radikant) / d;

			// Fußpunkt
			a /= d;
			double fx = x2 - a * dx;
			double fy = y2 - a * dy;

			int s1x = (int) (fx + h * dy);
			int s1y = (int) (fy - h * dx);
			int s2x = (int) (fx - h * dy);
			int s2y = (int) (fy + h * dx);
			erg = new Point[2];
			erg[0] = new Point(s1x, s1y);
			erg[1] = new Point(s2x, s2y);
		}
		return erg;
	}

	private void drawCircle(Graphics g, Point m, int r) {
		g.fillOval(m.x - r, m.y - r, 2 * r, 2 * r);
		g.setColor(Color.WHITE);
		g.drawLine(m.x-3, m.y, m.x+3, m.y);
		g.drawLine(m.x, m.y-3, m.x, m.y+3);
	}

}
