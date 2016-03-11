package zeichnen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class Shape implements Serializable {
	protected Rectangle r;
	protected Color color;
	
	public Shape(Color c, int x, int y, int width, int height) {
		color = c;
		r = new Rectangle(x, y, width, height);
	}
	
	public void showInfoDialog(ZeichenWin win) {
		InfoDialog	dlg = new InfoDialog(win, getTitle(), false, this);
		dlg.setBounds(r.x, r.y, 250, 300);
		dlg.setVisible(true);
	}

	public Rectangle getR() {
		return r;
	}

	public void setR(Rectangle r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		color = c;
	}
	
	public abstract void draw(Graphics2D g);
	public abstract double calcArea();
	public abstract double calcUmfang();
	public abstract boolean contains(Point p);
	public abstract String getTitle();
}
