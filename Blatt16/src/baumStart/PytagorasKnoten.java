package baumStart;

import java.awt.Color;
import java.awt.Graphics;

public class PytagorasKnoten extends Knoten {
	private double alpha;

	public PytagorasKnoten(Baum baum, Knoten parent, double a, double x,
			double y, double alpha) {
		super(baum, parent, a, x, y);
		this.alpha = alpha;
		child = new PytagorasKnoten[2];
	}

	@Override
	public void draw(Graphics g) {
		double s = baum.getScale();
		double sa = s * a;
		double cosa = sa * Math.cos(alpha);
		double sina = sa * Math.sin(alpha);
		double sx = s * x;
		double sy = s * y;
		double x1 = sx - cosa;
		double y1 = sy - sina;

		g.setColor(Color.WHITE);
		g.drawLine((int) sx, (int) sy, (int) x1, (int) y1);

		double x2 = x1 + sina;
		double y2 = y1 - cosa;
		g.setColor(Color.RED);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

		double x3 = x2 + cosa;
		double y3 = y2 + sina;
		g.setColor(Color.GREEN);
		g.drawLine((int) x2, (int) y2, (int) x3, (int) y3);
		g.setColor(Color.BLUE);
		g.drawLine((int) x3, (int) y3, (int) sx, (int) sy);

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].draw(g);
			}
		}
	}

	@Override
	public void addOneChild() {
		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].addOneChild();
			}
		}
		double b = 4 * a / 5;
		double c = 3 * a / 5;

		double beta = Math.acos(4.0 / 5) + alpha;
		double cosa = a * Math.cos(alpha);
		double sina = a * Math.sin(alpha);
		double x1 = x - cosa;
		double y1 = y - sina;
		double x2 = x1 + sina;
		double y2 = y1 - cosa;
		double x3 = x2 + cosa;
		double y3 = y2 + sina;

		if (child[0] == null) {
			child[0] = new PytagorasKnoten(baum, this, b, x3, y3, beta);
		}
		if (child[1] == null) {
			double xn = x3 - b * Math.cos(beta);
			double yn = y3 - b * Math.sin(beta);
			child[1] = new PytagorasKnoten(baum, this, c, xn, yn, (beta+Math.PI*3.0/2));
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

}
