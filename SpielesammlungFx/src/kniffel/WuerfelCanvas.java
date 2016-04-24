package kniffel;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WuerfelCanvas extends ResizeableCanvas {
	private int zahl = 1;

	public WuerfelCanvas() {
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		double w = getWidth();
		double h = getHeight();
		gc.clearRect(0, 0, w, h);

		double size = Math.min(w, h);
		double left = (w - size) / 2;
		double top = (h - size) / 2;
		gc.setFill(Color.YELLOW);
		gc.fillRect(left, top, size, size);

		gc.setFill(Color.BLACK);
		Rectangle2D r[] = calcRect(size);
		for (int i = 0; i < r.length; i++) {
			if (paintDot(i)) {
				gc.fillOval(r[i].getMinX(), r[i].getMinY(), r[i].getWidth(), r[i].getHeight());
			}
		}
	}
	
	public void setZahl(int z) {
		zahl = z;
		draw();
	}
	
	public int getZahl() {
		return zahl;
	}
	
	private boolean paintDot(int i) {
		switch (zahl) {
		case 1:
			if (i == 6) {
				return true;
			}
			break;
		case 2:
			if (i == 0 || i == 5) {
				return true;
			}
			break;
		case 3:
			if (i == 0 || i == 5 || i == 6) {
				return true;
			}
			break;
		case 4:
			if (i == 0 || i == 5 || i == 2 || i == 3) {
				return true;
			}
			break;
		case 5:
			if (i == 0 || i == 5 || i == 2 || i == 3 || i == 6) {
				return true;
			}
			break;
		case 6:
			if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
				return true;
			}
			break;
		}
		return false;
	}

	private Rectangle2D[] calcRect(double size) {
		double dx = size / 3;
		double dy = size / 3;
		Rectangle2D r[] = new Rectangle2D[7];

		double x = dx / 4 + (getWidth() - size) / 2;
		double y = dy / 4 + (getHeight() - size) / 2;
		r[0] = new Rectangle2D(x, y, dx / 2, dy / 2);
		r[1] = new Rectangle2D(x, y + dy, dx / 2, dy / 2);
		r[2] = new Rectangle2D(x, y + 2 * dy, dx / 2, dy / 2);
		r[3] = new Rectangle2D(x + 2 * dx, y, dx / 2, dy / 2);
		r[4] = new Rectangle2D(x + 2 * dx, y + dy, dx / 2, dy / 2);
		r[5] = new Rectangle2D(x + 2 * dx, y + 2 * dy, dx / 2, dy / 2);
		r[6] = new Rectangle2D(x + dx, y + dy, dx / 2, dy / 2);

		return r;
	}
}
