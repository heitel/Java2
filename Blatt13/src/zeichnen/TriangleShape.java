package zeichnen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

public class TriangleShape extends Shape {

	public TriangleShape(Color c, int x, int y, int width, int height) {
		super(c, x, y, width, height);
	}

	@Override
	public void draw(Graphics2D g) {
		Polygon p = calcPoly();

		g.setColor(color);
		g.fillPolygon(p);
	}

	private Polygon calcPoly() {
		Polygon p = new Polygon();
		p.addPoint(r.x, r.y + r.height);
		p.addPoint(r.x + r.width, r.y + r.height);
		p.addPoint(r.x+r.width/2, r.y);
		
		return p;
	}
	@Override
	public double calcArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcUmfang() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean contains(Point p) {
		Polygon poly = calcPoly();
		
		return poly.contains(p);
	}

	@Override
	public String getTitle() {
		return "Shape Info: Rect";
	}

}
