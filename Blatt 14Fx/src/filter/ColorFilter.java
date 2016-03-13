package filter;

import javafx.scene.image.WritableImage;

public abstract class ColorFilter extends Filter {
	protected int mask;

	public ColorFilter() {
	}

	@Override
	public WritableImage doFilter() {
		for (int i = 0; i < rgb.length; i++) {
			rgb[i] = rgb[i] & mask;
		}

		buffer2Img();
		return img;
	}
}
