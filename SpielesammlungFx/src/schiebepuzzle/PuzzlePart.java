package schiebepuzzle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PuzzlePart extends ResizeableCanvas {
	private Image img;
	private int nr;
	private boolean showNr;

	public PuzzlePart(Image img, int nr) {
		this.img = img;
		this.nr = nr;
	}

	@Override
	protected void draw() {
		if (img != null) {
			double width = getWidth();
			double height = getHeight();

			GraphicsContext gc = getGraphicsContext2D();
			gc.drawImage(img, 1, 1, width - 2, height - 2);
			if (showNr) {
				gc.setFont(new Font(20));
				gc.setFill(Color.BLACK);
				gc.fillText("" + nr, 5, 18);
				gc.setFill(Color.WHITE);
				gc.fillText("" + nr, 6, 19);
			}
		}
	}

	public void setShowNr(boolean showNr) {
		this.showNr = showNr;
		draw();
	}

	public int getNr() {
		return nr;
	}
}
