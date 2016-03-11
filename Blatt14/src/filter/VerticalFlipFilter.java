package filter;

import java.awt.image.BufferedImage;

public class VerticalFlipFilter extends Filter {

	public VerticalFlipFilter() {
	}

	@Override
	public BufferedImage doFilter() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width / 2; j++) {
				int tmp = rgb[i * width + j];
				rgb[i * width + j] = rgb[(i * width) + width - j - 1];
				rgb[(i * width) + width - j - 1] = tmp;
			}
		}

		img.setRGB(0, 0, width, height, rgb, 0, width);
		return img;
	}

}
