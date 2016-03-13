package filter;

import javafx.scene.image.WritableImage;

public class VerticalFlipFilter extends Filter {

	public VerticalFlipFilter() {
	}

	@Override
	public WritableImage doFilter() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width / 2; j++) {
				int tmp = rgb[i * width + j];
				rgb[i * width + j] = rgb[(i * width) + width - j - 1];
				rgb[(i * width) + width - j - 1] = tmp;
			}
		}

		buffer2Img();
		return img;
	}

}
