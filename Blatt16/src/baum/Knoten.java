package baum;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Knoten {
	protected Baum baum;
	protected Knoten parent;
	protected Color color;
	protected double a;
	protected Knoten child[];
	protected double x, y;
	protected int maxx;
	protected int maxy;

	public Knoten(Baum baum, Knoten parent, double a, Color color, double x, double y) {
		this.baum = baum;
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.a = a;
		this.color = color;
	}

	public abstract void draw(Graphics g);
	public abstract void addOneChild();
	public abstract void removeOneChild();
	
	public void remove() {
		for (int i = 0; i < child.length; i++) {
			child[i] = null;
		}
	}

	public int count() {
		int anz = 1;
		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				anz += child[i].count();
			}
		}
		return anz;
	}

	public abstract double calcArea();

	public void calcBoundingBox(Point max) {
		if (max.x < maxx) {
			max.x = maxx;
		}
		if (max.y < maxy) {
			max.y = maxy;
		}
	
		for (int i = 0; i < child.length; i++) {
			if (child[i] != null) {
				child[i].calcBoundingBox(max);
			}
		}
	}

	public int getDepth() {
		if (parent == null) {
			return 0;
		}
		return 1 + parent.getDepth();
	}
}
