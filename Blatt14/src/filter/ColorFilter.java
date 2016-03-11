package filter;

import java.awt.image.BufferedImage;

public abstract class ColorFilter extends Filter {
	protected int mask;

	public ColorFilter() {
	}

	@Override
	public BufferedImage doFilter() {
		for (int i = 0; i < rgb.length; i++) {
			rgb[i] = rgb[i] & mask;
		}
		
		img.setRGB(0, 0, width, height, rgb, 0, width);
		return img;
	}


}
