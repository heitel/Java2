package baum;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SierpinskiKnoten extends Knoten {
	private static final double SINALPHA = Math.sin(Math.toRadians(60));

	public SierpinskiKnoten(Baum baum, Knoten parent, double a, Color color,
			double x, double y) {
		super(baum, parent, a, color, x, y);
		
		child = new SierpinskiKnoten[3];
	}

	public void draw(GraphicsContext g) {
		double scale = baum.getScale();
		double sx = x * scale;
		double sy = y * scale;
		double sa = a * scale;

		int p0x = (int) sx;
		int p0y = (int) sy;
		int p1x = (int) (sx + sa);// p1y = p0y!
		int p2x = (int) (sx + sa / 2);
		int p2y = (int) (sy + sa * SINALPHA);
		maxx = p1x;
		maxy = p2y;

		if (g != null) {
			g.setStroke(color);
			g.strokeLine(p0x, p0y, p1x, p0y);
			g.strokeLine(p1x, p0y, p2x, p2y);
			g.strokeLine(p2x, p2y, p0x, p0y);
		}
		
		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].draw(g);
			}
		}
	}

	public void addOneChild() {
		double c = a / 2;
		double sina = c * SINALPHA;

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].addOneChild();
			}
		}

		if (child[0] == null) {
			child[0] = new SierpinskiKnoten(baum, this, c, Color.RED,
					x + c / 2, y - sina);
		}
		if (child[1] == null) {
			child[1] = new SierpinskiKnoten(baum, this, c, Color.GREEN, x + a
					- c / 2, y + sina);
		}
		if (child[2] == null) {
			child[2] = new SierpinskiKnoten(baum, this, c, Color.BLUE, x - c
					/ 2, y + sina);
		}
	}

	public void removeOneChild() {
		if (child[0] == null && child[1] == null && child[2] == null) {
			parent.remove();
		}

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].removeOneChild();
			}
		}
	}

	public double calcArea() {
		double area = a * a * Math.sqrt(3) / 4;

		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				area += child[i].calcArea();
			}
		}

		return area;
	}
}
