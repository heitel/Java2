package baum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Baum {
	private static final double SIZE = 100.0;
	private Knoten wurzel;
	private int level;
	private double area;
	private int anz;
	private final Point max = new Point();
	private double scale;

	public Baum(int level, int scale, int type) {
		createTree(level, scale, type);
	}

	private void createTree(int level, int scale, int type) {
		if (type == 0) {
			wurzel = new PytagorasKnoten(this, null, SIZE, Color.WHITE,
					3.5 * SIZE, 4.5 * SIZE, 0);
		}
		if (type == 1) {
			wurzel = new SierpinskiKnoten(this, null, SIZE, Color.WHITE, SIZE,
					SIZE);
		}
		
		if (type == 2) {
			wurzel = new VonKochKnoten(this, null, SIZE, Color.WHITE, SIZE, SIZE, 0);
		}
		
		for (int i = 0; i < level - 1; i++) {
			wurzel.addOneChild();
		}
		this.level = level;
		
		setScale(scale);	
	}

	public void draw(Graphics g) {
		if (wurzel != null) {
			wurzel.draw(g);
			g.setColor(Color.WHITE);
			g.drawString("Fläche: " + getArea(), 10, 20);
			g.drawString("Anzahl: " + getAnz(), 10, 40);
		}
	}

	public double getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale / SIZE;
		setupBounds();
	}

	public double getArea() {
		return area;
	}

	public int getAnz() {
		return anz;
	}

	public Point getMax() {
		return max;
	}

	public void setLevel(int level) {
		if (wurzel != null) {
			for (int i = 0; i < level - this.level; i++) {
				wurzel.addOneChild();
			}
			for (int i = 0; i < this.level - level; i++) {
				wurzel.removeOneChild();
			}
			setupBounds();
		}
		this.level = level;
	}

	private void setupBounds() {
		max.x = max.y = 0;
		wurzel.draw(null);
		wurzel.calcBoundingBox(max);
		max.x += 16;
		max.y += 16;
		anz = wurzel.count();
		area = wurzel.calcArea();
	}
}
