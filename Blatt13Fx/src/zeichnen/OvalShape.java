package zeichnen;

import java.awt.geom.Ellipse2D;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class OvalShape extends Shape {
	private static final long serialVersionUID = -7128355077294629053L;

	public OvalShape(Color c, double x, double y, double width, double height) {
		super(c, x, y, width, height);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
	}

	@Override
	public double calcArea() {
		return r.getWidth() * r.getHeight() * Math.PI / 4;
	}

	@Override
	public double calcUmfang() {
		double a = r.getWidth() / 2;
		double b = r.getHeight() / 2;

		double lambda = (a - b) / (a + b);
		double lambda3 = lambda * lambda * 3;

		return Math.PI * (a + b) * (1 + (lambda3 / (10 + Math.sqrt(4 - lambda3))));
	}

	@Override
	public boolean contains(Point2D p) {
		Ellipse2D e = new Ellipse2D.Double(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());

		return e.contains(p.getX(), p.getY());
	}

	@Override
	public String getTitle() {
		return "Oval";
	}
	
	@Override
	public javafx.scene.shape.Shape convertToFx() {
		Ellipse e = new Ellipse(r.getMinX()+r.getWidth()/2, r.getMinY()+r.getHeight()/2, r.getWidth()/2, r.getHeight()/2);
		e.setFill(color);
		return e;
	}
}
