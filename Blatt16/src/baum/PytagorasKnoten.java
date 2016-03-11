package baum;

import java.awt.Color;
import java.awt.Graphics;

public class PytagorasKnoten extends Knoten {
	private double alpha;

	public PytagorasKnoten(Baum baum, Knoten parent, double a, Color color,
			double x, double y, double alpha) {
		super(baum, parent, a, color, x, y);

		this.alpha = alpha;

		child = new PytagorasKnoten[2];
	}

	public void draw(Graphics g) {
		double scale = baum.getScale();
		double sx = x * scale;
		double sy = y * scale;
		double sa = a * scale;
		double cosa = sa * Math.cos(alpha);
		double sina = sa * Math.sin(alpha);

		int p0x = (int) sx;
		int p0y = (int) sy;
		int p1x = (int) (sx - cosa);
		int p1y = (int) (sy - sina);
		int p2x = (int) (sx - cosa + sina);
		int p2y = (int) (sy - sina - cosa);
		int p3x = (int) (sx + sina);
		int p3y = (int) (sy - cosa);
		maxx = p0x;
		maxy = p0y;

		if (g != null) {
			g.setColor(color);
			g.drawLine(p0x, p0y, p1x, p1y);
			g.drawLine(p1x, p1y, p2x, p2y);
			g.drawLine(p2x, p2y, p3x, p3y);
			g.drawLine(p3x, p3y, p0x, p0y);
		}

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].draw(g);
			}
		}
	}

	@Override
	public void addOneChild() {
		double c = 4 * a / 5;
		double b = 3 * a / 5;
		double phi = alpha + Math.asin(3.0 / 5.0);
		double cosa = c * Math.cos(phi);
		double sina = c * Math.sin(phi);
		double x3 = x + a * Math.sin(alpha);
		double y3 = y - a * Math.cos(alpha);
		
		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].addOneChild();
			}
		}
		
		if (child[0] == null) {
			child[0] = new PytagorasKnoten(baum, this, c, Color.YELLOW, x3, y3,
					phi);
		}
		if (child[1] == null) {
			child[1] = new PytagorasKnoten(baum, this, b, Color.RED, x3 - cosa,
					y3 - sina, alpha - Math.PI / 2 + Math.asin(3.0 / 5.0));
		}
	}

	@Override
	public void removeOneChild() {
		if (child[0] == null && child[1] == null) {
			parent.remove();
		}

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].removeOneChild();
			}
		}
	}

	public double calcArea() {
		double area = a * a;
		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				area += child[i].calcArea();
			}
		}
		return area;
	}
}
