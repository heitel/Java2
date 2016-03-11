package kreise;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

class KreisCanvas extends ResizeableCanvas {
	private Point2D m1;
	private double r1 = 150;
	private Point2D m2 = new Point2D(0, 0);
	private double r2 = 200;

	public KreisCanvas() {
		super();
		setOnMouseMoved(e -> move(e));
		setOnScroll(e -> scroll(e));
	}

	private void scroll(ScrollEvent e) {
		r2 += e.getDeltaY() / 10.0;
		r2 = Math.max(r2, 0);
		draw();
	}

	private void move(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		m2 = new Point2D(x, y);
		draw();
	}

	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		double width = getWidth();
		double height = getHeight();
		gc.clearRect(0, 0, width, height);
		m1 = new Point2D(width / 2, height / 2);

		gc.setLineWidth(1);
		gc.setStroke(Color.BLACK);
		gc.strokeOval(m1.getX() - r1, m1.getY() - r1, 2 * r1, 2 * r1);
		gc.setFill(new Color(0, 0, 1, 0.5));
		gc.fillOval(m1.getX() - r1, m1.getY() - r1, 2 * r1, 2 * r1);
		
		gc.setStroke(Color.BLACK);
		gc.strokeOval(m2.getX() - r2, m2.getY() - r2, 2 * r2, 2 * r2);
		gc.setFill(new Color(1, 0, 0, 0.5));
		gc.fillOval(m2.getX() - r2, m2.getY() - r2, 2 * r2, 2 * r2);
		
		Point2D[] s = calcSchnittpunkt();
		if (s != null) {
			drawSchnittpunkt(s, gc);
		}
	}

	private void drawSchnittpunkt(Point2D[] s, GraphicsContext gc) {
		double klein = 5;

		gc.setStroke(Color.GREEN);
		gc.setLineWidth(2);
		for (int i = 0; i < s.length; i++) {
			gc.strokeOval(s[i].getX() - klein, s[i].getY() - klein, 2 * klein, 2 * klein);
		}
	}

	private Point2D[] calcSchnittpunkt() {
		double dx = m2.getX() - m1.getX();
		double dy = m2.getY() - m1.getY();
		double d = Math.sqrt(dx * dx + dy * dy);
		System.out.println("d = " + d);
		double r12 = r1 * r1;
		double r22 = r2 * r2;
		double p = (r12 - r22 + d * d) / (2 * d);
		System.out.println("p = " + p);
		double wurzel = r12 - p * p;
		if (wurzel >= 0) {
			double h = Math.sqrt(wurzel);
			System.out.println("h = " + h);
			double fx = m1.getX() + (p * dx) / d;
			double fy = m1.getY() + (p * dy) / d;
			System.out.println("F: " + fx + "/" + fy);

			return new Point2D[] { new Point2D(fx + (h * dy) / d, fy - (h * dx) / d),
					new Point2D(fx - (h * dy) / d, fy + (h * dx) / d) };
		}

		return null;
	}
}