package selectionSort;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SortCanvas extends ResizeableCanvas {
	private char[] data = null;
	private char min = 0;
	private char max = 0;

	public SortCanvas(String txt) {
		setData(txt);
	}

	public void setData(String txt) {
		data = txt.toCharArray();
		findMinMax();
		draw();
	}

	private void findMinMax() {
		min = 0xFFFF;
		max = 0;

		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				if (data[i] > max) {
					max = data[i];
				}
				if (data[i] < min) {
					min = data[i];
				}
			}
		}
	}

	@Override
	protected void draw() {
		double width = getWidth();
		double height = getHeight();
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, width, height);

		if (data != null) {
			int len = data.length;
			double pw = width / len;
			int diff = max - min + 1;
			double unit = height / diff;

			for (int i = 0; i < len; i++) {
				double h = (data[i] - min + 1) * unit;
				double x = i * pw;
				double y = height - h;
				gc.setFill(Color.CADETBLUE);
				gc.fillRect(x, y, pw - 1, h);
				gc.setFill(Color.BLACK);
				gc.fillText("" + data[i], x+3, y+10);
			}
		}
	}
}
