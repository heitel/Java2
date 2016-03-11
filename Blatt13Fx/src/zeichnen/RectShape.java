package zeichnen;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class RectShape extends Shape {
	private static final long serialVersionUID = 7457353544652045708L;

	public RectShape(Color c, double x, double y, double width, double height) {
		super(c, x, y, width, height);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillRect(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
	}

	@Override
	public double calcArea() {
		return r.getWidth() * r.getHeight();
	}

	@Override
	public double calcUmfang() {
		return 2 * (r.getWidth() + r.getHeight());
	}

	@Override
	public boolean contains(Point2D p) {
		return r.contains(p);
	}

	@Override
	public String getTitle() {
		return "Rect";
	}

	@Override
	public javafx.scene.shape.Shape convertToFx() {
		Rectangle rect = new Rectangle(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
		rect.setFill(color);
		return rect;
	}
}
