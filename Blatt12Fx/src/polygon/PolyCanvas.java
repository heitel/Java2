package polygon;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class PolyCanvas extends ResizeableCanvas {
	private int ecken = 3;
	private int winkel = 0;

	public PolyCanvas() {
		super();
	}

	public void setEcken(int ecken) {
		this.ecken = ecken;
		draw();
	}

	public void setWinkel(int winkel) {
		this.winkel = winkel;
		draw();
	}

	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		double width = getWidth();
		double height = getHeight();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);

		Point2D cp = new Point2D(width / 2, height / 2);
		double alpha = Math.toRadians(360.0 / ecken);
		double beta = Math.toRadians(winkel);
		double r = Math.min(width, height) / 2;
		double[] x = new double[ecken];
		double[] y = new double[ecken];
		for (int i = 0; i < y.length; i++) {
			x[i] = cp.getX() + r * Math.cos(alpha * i + beta);
			y[i] = cp.getY() + r * Math.sin(alpha * i + beta);
		}
		gc.setStroke(Color.WHITE);
		gc.strokePolygon(x, y, x.length);

		gc.setStroke(Color.RED);
		int len = y.length;
		for (int i = 0; i < len - 2; i++) {
			for (int j = i+2; j < i + len - 1; j++) {
				gc.strokeLine(x[i], y[i], x[j%len], y[j%len]);
			}
		}
		
		double klein = 6;
		gc.setFill(Color.LIGHTGREEN);
		gc.fillOval(x[0]-klein, y[0]-klein, 2*klein, 2*klein);
	}
}