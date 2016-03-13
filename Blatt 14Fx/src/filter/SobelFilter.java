package filter;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class SobelFilter extends Filter {

	public SobelFilter() {

	}

	@Override
	public WritableImage doFilter() {
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
				int p00 = rgb[(i - 1) * width + j - 1];
				int p01 = rgb[(i - 1) * width + j];
				int p02 = rgb[(i - 1) * width + j + 1];
				int p10 = rgb[i * width + j - 1];
				int p11 = rgb[i * width + j];
				int p12 = rgb[i * width + j + 1];
				int p20 = rgb[(i + 1) * width + j - 1];
				int p21 = rgb[(i + 1) * width + j];
				int p22 = rgb[(i + 1) * width + j + 1];

				int alpha = p11 >> 24 &0xFF;
				int red = (-3 * (p00 >> 16 & 0xFF) + 3 * (p02 >> 16 & 0xFF)
						- 10 * (p10 >> 16 & 0xFF) + 10 * (p12 >> 16 & 0xFF) - 3
						* (p20 >> 16 & 0xFF) + 3 * (p22 >> 16 & 0xFF));

				int redy = (-3 * (p00 >> 16 & 0xFF) - 10 * (p01 >> 16 & 0xFF)
						- 3 * (p02 >> 16 & 0xFF) + 3 * (p20 >> 16 & 0xFF) +10
						* (p21 >> 16 & 0xFF) + 3 * (p22 >> 16 & 0xFF));

				red = (int)Math.sqrt(red*red + redy*redy);
				if (red > 255) {
					red = 255;
				}
				if (red < 0) {
					red = 0;
				}
				int green =  (-3 * (p00 >> 8 & 0xFF) + 3 * (p02 >> 8 & 0xFF)
						- 10 * (p10 >> 8 & 0xFF) + 10 * (p12 >> 8 & 0xFF) - 3
						* (p20 >> 8 & 0xFF) + 3 * (p22 >> 8 & 0xFF));
				int greeny = (-3 * (p00 >> 8 & 0xFF) - 10 * (p01 >> 8 & 0xFF)
						- 3 * (p02 >> 8 & 0xFF) + 3 * (p20 >> 8 & 0xFF) +10
						* (p21 >> 8 & 0xFF) + 3 * (p22 >> 8 & 0xFF));

				green = (int)Math.sqrt(green*green + greeny*greeny);
				if (green > 255) {
					green = 255;
				}
				if (green < 0) {
					green = 0;
				}
				int blue =  (-3 * (p00  & 0xFF) + 3 * (p02  & 0xFF)
						- 10 * (p10  & 0xFF) + 10 * (p12  & 0xFF) - 3
						* (p20  & 0xFF) + 3 * (p22  & 0xFF));
				int bluey = (-3 * (p00 & 0xFF) - 10 * (p01 & 0xFF)
						- 3 * (p02  & 0xFF) + 3 * (p20 & 0xFF) +10
						* (p21 & 0xFF) + 3 * (p22 & 0xFF));

				blue = (int)Math.sqrt(blue*blue + bluey*bluey);
				if (blue > 255) {
					blue = 255;
				}
				if (blue < 0) {
					blue = 0;
				}
				tmp[i * width + j] = alpha << 24 | red << 16 | green << 8 | blue;
			}
		}
		PixelWriter writer = img.getPixelWriter();
		writer.setPixels(0, 0, width, height, FORMAT, tmp, 0, width);
		return img;

	}

}
