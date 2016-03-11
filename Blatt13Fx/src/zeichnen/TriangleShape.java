package zeichnen;

import java.awt.geom.Path2D;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class TriangleShape extends Shape {
	private static final long serialVersionUID = 8336848214335101816L;

	public TriangleShape(Color c, double x, double y, double width, double height) {
		super(c, x, y, width, height);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillPolygon(getX(), getY(), 3);
	}

	private Path2D.Double calcPath() {
		double left = r.getMinX();
		double bottom = r.getMaxY();
		double right = r.getMaxX();
		double top = r.getMinY();
		double mx = r.getMinX() + r.getWidth() / 2;

		Path2D.Double path = new Path2D.Double();
		path.moveTo(left, bottom);
		path.lineTo(right, bottom);
		path.lineTo(mx, top);
		path.closePath();

		return path;
	}

	private double[] getX() {
		double left = r.getMinX();
		double right = r.getMaxX();
		double mx = r.getMinX() + r.getWidth() / 2;

		return new double[] { left, right, mx };
	}

	private double[] getY() {
		double bottom = r.getMaxY();
		double top = r.getMinY();

		return new double[] { bottom, bottom, top };
	}

	@Override
	public double calcArea() {
		return r.getWidth() * r.getHeight() / 2;
	}

	@Override
	public double calcUmfang() {
		double w = r.getWidth();
		double w2 = w / 2;
		double h = r.getHeight();
		double x = Math.sqrt(w2 * w2 + h * h);
		return 2 * x + w;
	}

	@Override
	public boolean contains(Point2D p) {
		Path2D.Double path = calcPath();
		return path.contains(p.getX(), p.getY());
	}

	@Override
	public String getTitle() {
		return "Triangle";
	}

	@Override
	public javafx.scene.shape.Shape convertToFx() {
		double[] x = getX();
		double[] y = getY();
		Polygon poly = new Polygon(x[0], y[0], x[1], y[1], x[2], y[2]);
		poly.setFill(color);
		return poly;
	}
}
