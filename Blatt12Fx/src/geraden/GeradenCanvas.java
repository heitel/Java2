package geraden;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GeradenCanvas extends ResizeableCanvas {
	private double m1 = 1;
	private double b1 = 100;
	private double m2 = 0;
	private double b2 = 50;
	private double alpha = 0;

	public GeradenCanvas() {
		super();
		setOnMouseMoved(e -> move(e));
		setOnScroll(e -> scroll(e));
	}

	private void scroll(ScrollEvent e) {
		alpha += e.getDeltaY() / 10;
		m2 = Math.tan(Math.toRadians(alpha));

		double x = e.getX();
		double y = e.getY();
		b2 = y - m2 * x;
		draw();
	}

	private void move(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		b2 = y - m2 * x;
		draw();
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		double width = getWidth();
		double height = getHeight();

		gc.setFill(Color.LIGHTGRAY);
		gc.fillRect(0, 0, width, height);

		gc.setStroke(Color.BLACK);
		gc.strokeLine(0, b1, width, width * m1 + b1);

		gc.setStroke(Color.RED);
		gc.strokeLine(0, b2, width, width * m2 + b2);

		Point2D s = berechneSchnittpunkt();
		if (s != null) {
			zeichneSchnittpunkt(gc, s);
		}

		double schnittwinkel = Math.toDegrees(Math.atan(Math.abs(((m1 - m2) / (1 + m1 * m2)))));

		Stage win = (Stage)getScene().getWindow();
		win.setTitle("Schnittpunkt zweier Geraden. Schnittwinkel: " + Math.round(schnittwinkel)+"°");
	}

	private void zeichneSchnittpunkt(GraphicsContext gc, Point2D s) {
		double r = 5;

		gc.setStroke(Color.GREEN);
		gc.strokeOval(s.getX() - r, s.getY() - r, 2 * r, 2 * r);
	}

	private Point2D berechneSchnittpunkt() {
		if (m1 == m2) {
			if (b1 == b2) {
				System.out.println("Die Geraden liegen aufeinander.");
			} else {
				System.out.println("Die Geraden sind parallel.");
			}
			return null;
		}
		double x = (b2 - b1) / (m1 - m2);
		double y = m1 * x + b1;
		return new Point2D(x, y);
	}

}
