package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

public abstract class ResizeableCanvas extends Canvas {
	protected WritableImage bImage;

	public ResizeableCanvas() {
		widthProperty().addListener(e -> draw());
		heightProperty().addListener(e -> draw());
	}

	protected abstract void draw();

	@Override
	public boolean isResizable() {
		return true;
	}

	@Override
	public double minWidth(double height) {
		return 10;
	}

	@Override
	public double minHeight(double width) {
		return 10;
	}

	@Override
	public double maxWidth(double height) {
		return 10000;
	}

	@Override
	public double maxHeight(double width) {
		return 10000;
	}

	@Override
	public void resize(double width, double height) {
		if (width != getWidth() || height != getHeight()) {
			bImage = new WritableImage((int) width, (int) height);
		}
		super.setWidth(width);
		super.setHeight(height);
	}
}
