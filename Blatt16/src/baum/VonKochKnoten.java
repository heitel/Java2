package baum;

import java.awt.Color;
import java.awt.Graphics;

public class VonKochKnoten extends Knoten {
	private double alpha;

	public VonKochKnoten(Baum baum, Knoten parent, double a, Color color,
			double x, double y, double alpha) {
		super(baum, parent, a, color, x, y);

		this.alpha = alpha;
		child = new VonKochKnoten[4];
	}

	public void draw(Graphics g) {
		double scale = baum.getScale();
		double sx = x * scale;
		double sy = y * scale;
		double sa = a * scale;

		int p0x = (int) sx;
		int p0y = (int) sy;
		int p1x = (int) (sx + sa * Math.cos(alpha));
		int p1y = (int) (sy + sa * Math.sin(alpha));

		maxx = p1x;
		maxy = p1y;

		if (g != null && child[0]==null) {
			g.setColor(color);
			g.drawLine(p0x, p0y, p1x, p1y);
		}

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].draw(g);
			}
		}
	}

	public void addOneChild() {
		double c = a / 3;
		double sina = c * Math.sin(alpha);
		double cosa = c * Math.cos(alpha);

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].addOneChild();
			}
		}

		if (child[0] == null) {
			child[0] = new VonKochKnoten(baum, this, c, Color.PINK, x, y, alpha);
		}
		if (child[1] == null) {
			child[1] = new VonKochKnoten(baum, this, c, Color.RED, x + cosa, y+sina,
					alpha - Math.toRadians(60));
		}
		if (child[2] == null) {
			child[2] = new VonKochKnoten(baum, this, c, Color.GREEN, 
					x + cosa + c * Math.cos(alpha - Math.toRadians(60)),
					y + sina + c * Math.sin(alpha - Math.toRadians(60)), alpha + Math.toRadians(60));
		}
		if (child[3] == null) {
			child[3] = new VonKochKnoten(baum, this, c, Color.BLUE, x + 2 * cosa,
					y+2*sina, alpha);
		}
	}

	public void removeOneChild() {
		if (child[0] == null && child[1] == null && child[2] == null
				&& child[3] == null) {
			parent.remove();
		}

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].removeOneChild();
			}
		}
	}

	public double calcArea() {
		double area = 0;

		return area;
	}
}
