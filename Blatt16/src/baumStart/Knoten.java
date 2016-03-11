package baumStart;

import java.awt.Graphics;

public abstract class Knoten {
	protected Baum baum;
	protected Knoten parent;
	protected Knoten child[];
	protected double a;
	protected double x, y;

	public Knoten(Baum baum, Knoten parent, double a, double x, double y) {
		this.baum = baum;
		this.parent = parent;
		this.a = a;
		this.x = x;
		this.y = y;
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
			anz += child[i].count();
		}
		return anz;
	}

	public int getDepth() {
		if (parent == null) {
			return 0;
		}
		return parent.getDepth() + 1;
	}
}
