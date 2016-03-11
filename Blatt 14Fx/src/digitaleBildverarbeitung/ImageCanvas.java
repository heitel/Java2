package digitaleBildverarbeitung;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageCanvas extends ResizeableCanvas {
	private WritableImage img;

	public ImageCanvas() {

	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		double width = getWidth();
		double height = getHeight();
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, width, height);

		if (img != null) {
			double scaleX = width / img.getWidth();
			double scaleY = height / img.getHeight();
			double scale = Math.min(scaleX, scaleY);

			double imgW = img.getWidth() * scale;
			double imgH = img.getHeight() * scale;
			double x = (width - imgW) / 2;
			double y = (height - imgH) / 2;
			gc.drawImage(img, x, y, imgW, imgH);
		}
	}

	public void setImg(WritableImage img) {
		this.img = img;
		draw();
	}

}
