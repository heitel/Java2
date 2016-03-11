package baumStart;

import java.awt.Graphics;
import java.awt.Point;

public class Baum {
	private static final double SIZE = 100.0;
	private int level;
	private double scale;
	private Knoten wurzel;

	public Baum(int level, int scale, int type) {
		createTree(level, scale, type);
	}

	private void createTree(int level, int scale, int type) {
		setScale(scale);
		if (type == 0) {
			wurzel = new PytagorasKnoten(this, null, SIZE, 3.5 * SIZE,
					4.5 * SIZE, 0);
		}
		this.level = level;
	}

	public Point getMax() {
		return new Point(1000, 1000);// ToDO!
	}

	public void setLevel(int level) {
		if (wurzel != null) {
			for (int i = 0; i < level - this.level; i++) {
				wurzel.addOneChild();
			}
			for (int i = 0; i < this.level - level; i++) {
				wurzel.removeOneChild();
			}
			this.level = level;
		}

	}

	public void setScale(int scale) {
		this.scale = scale / SIZE;
	}

	public double getScale() {
		return scale;
	}

	public void draw(Graphics g) {
		if (wurzel != null) {
			wurzel.draw(g);
		}
	}

}
