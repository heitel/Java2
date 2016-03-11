package filter;

import java.awt.image.BufferedImage;

public class SharpenFilter extends Filter {

	public SharpenFilter() {
	}

	@Override
	public BufferedImage doFilter() {
		int[] tmp = new int[height * width];

		// Ränder kopieren
		for (int i = 0; i < width; i++) {
			tmp[i] = rgb[i];
		}
		for (int i = 0; i < width; i++) {
			tmp[(height - 1) * width + i] = rgb[(height - 1) * width + i];
		}
		for (int i = 0; i < height; i++) {
			tmp[(height - 1) * width] = rgb[(height - 1) * width];
		}
		for (int i = 0; i < height; i++) {
			tmp[(height - 1) * width + width - 1] = rgb[(height - 1) * width
					+ width - 1];
		}
		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {
				int oben = rgb[(i - 1) * width + j];
				int links = rgb[i * width + j - 1];
				int rechts = rgb[i * width + j + 1];
				int unten = rgb[(i + 1) * width + j];
				int selbst = rgb[i * width + j];

				int red = (-(oben >> 16 & 0xFF) - (links >> 16 & 0xFF)
						+ (selbst >> 16 & 0xFF) * 5 - (rechts >> 16 & 0xFF) - (unten >> 16 & 0xFF));
				if (red > 255) {
					red = 255;
				}
				if (red < 0) {
					red = 0;
				}
				int green = (-(oben >> 8 & 0xFF) - (links >> 8 & 0xFF)
						+ (selbst >> 8 & 0xFF) * 5 - (rechts >> 8 & 0xFF) - (unten >> 8 & 0xFF));
				if (green > 255) {
					green = 255;
				}
				if (green < 0) {
					green = 0;
				}
				int blue = (-(oben & 0xFF) - (links & 0xFF) + (selbst & 0xFF)
						* 5 - (rechts & 0xFF) - (unten & 0xFF));
				if (blue > 255) {
					blue = 255;
				}
				if (blue < 0) {
					blue = 0;
				}
				tmp[i * width + j] = red << 16 | green << 8 | blue;
			}
		}
		img.setRGB(0, 0, width, height, tmp, 0, width);
		return img;
	}
}
