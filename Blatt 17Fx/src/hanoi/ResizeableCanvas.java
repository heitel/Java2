package hanoi;

import javafx.scene.canvas.Canvas;

public abstract class ResizeableCanvas extends Canvas {

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
		super.setWidth(width);
		super.setHeight(height);
	}
}
