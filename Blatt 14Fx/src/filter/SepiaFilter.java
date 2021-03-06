package filter;

import javafx.scene.image.WritableImage;

public class SepiaFilter extends Filter {
	public SepiaFilter() {
	}

	@Override
	public WritableImage doFilter() {
		for (int i = 0; i < rgb.length; i++) {
			int alpha = (rgb[i] >> 24) & 0xFF;
			int r = (rgb[i] >> 16) & 0xFF;
			int g = (rgb[i] >> 8) & 0xFF;
			int b = rgb[i] & 0xFF;
			int or = Math.min(255, (int) ((r * .393) + (g * .769) + (b * .189)));
			int og = Math.min(255, (int) ((r * .349) + (g * .686) + (b * .168)));
			int ob = Math.min(255, (int) ((r * .272) + (g * .534) + (b * .131)));
			rgb[i] = alpha << 24 | or << 16 | og << 8 | ob;
		}

		buffer2Img();
		return img;
	}
}
