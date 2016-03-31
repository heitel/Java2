package baum;

import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class BaumCanvas extends ResizeableCanvas {
	private Baum baum;

	public BaumCanvas() {
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		if (baum != null) {
			baum.draw(gc);
		}
	}

	public void createTree(int level, int scale, int type) {
		baum = new Baum(level, scale, type);

		update();
	}

	private void update() {
		Point max = baum.getMax();
		resize(max.x, max.y);
		draw();
	}

	public void setLevel(int level) {
		if (baum != null) {
			baum.setLevel(level);
			update();
		}
	}

	public void setScale(int scale) {
		if (baum != null) {
			baum.setScale(scale);
			update();
		}
	}
}
