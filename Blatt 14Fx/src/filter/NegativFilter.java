package filter;

import javafx.scene.image.WritableImage;

public class NegativFilter extends Filter {
	public NegativFilter() {
	}

	@Override
	public WritableImage doFilter() {
		for (int i = 0; i < rgb.length; i++) {
			int alpha = (rgb[i] >> 24) & 0xFF;
			int red = 255 - (rgb[i] >> 16) & 0xFF;
			int green = 255 - (rgb[i] >> 8) & 0xFF;
			int blue = 255 - rgb[i] & 0xFF;
			rgb[i] = alpha << 24 | red << 16 | green << 8 | blue;
		}

		buffer2Img();
		return img;
	}

}
