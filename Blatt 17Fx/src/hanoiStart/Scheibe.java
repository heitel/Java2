package hanoiStart;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;

public class Scheibe {
	private Color color;
	private Rectangle2D r;
	private int nr;

	public Scheibe(Color c, int nr) {
		this.color = c;
		this.nr = nr;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Rectangle2D getR() {
		return r;
	}

	public void setR(Rectangle2D r) {
		this.r = r;
	}

	public int getNr() {
		return nr;
	}

	public boolean contains(Point2D p) {
		return r.contains(p);
	}

	public void draw(GraphicsContext gc) {
		if (r != null) {
			gc.setFill(color);
			gc.fillRect(r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
			gc.setFont(new Font(18));
			gc.setFill(Color.WHITE);
			gc.fillText("" + (nr + 1), r.getMinX() + 6, r.getMinY() + 20);
			gc.setFill(Color.BLACK);
			gc.fillText("" + (nr + 1), r.getMinX() + 5, r.getMinY() + 19);

			gc.setStroke(Color.WHITE);
			gc.setLineCap(StrokeLineCap.ROUND);
			gc.setLineWidth(2);
			gc.strokeLine(r.getMinX(), r.getMinY(), r.getMaxX(), r.getMinY());
			gc.strokeLine(r.getMinX(), r.getMaxY(), r.getMinX(), r.getMinY());
			gc.setStroke(Color.GRAY);
			gc.setLineWidth(2);
			gc.strokeLine(r.getMaxX(), r.getMinY() + 1, r.getMaxX(), r.getMaxY());
			gc.strokeLine(r.getMinX() + 1, r.getMaxY(), r.getMaxX(), r.getMaxY());
		}
	}

}
