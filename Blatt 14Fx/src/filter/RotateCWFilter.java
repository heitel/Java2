package filter;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;


public class RotateCWFilter extends Filter {

	public RotateCWFilter() {
	}

	@Override
	public WritableImage doFilter() {
		WritableImage im = new WritableImage(height, width);
		int[] rgbNew = new int[width * height]; 
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgbNew[i*height+j] = rgb[(height-j-1)*width+i];
			}
		}
		PixelWriter writer = im.getPixelWriter();
		writer.setPixels(0, 0, height, width, FORMAT, rgbNew, 0, height);
		return im;
	}
}
