package filter;

import java.awt.image.BufferedImage;

public class BWFilter extends Filter {
	public BWFilter() {
	}

	@Override
	public BufferedImage doFilter() {
		for (int i = 0; i < rgb.length; i++) {
			int r = (rgb[i] >> 16) & 0xFF;
			int g = (rgb[i] >> 8) & 0xFF;
			int b = rgb[i] & 0xFF;
			// lightness
			 int val = (max(r, g, b) + min(r, g, b)) / 2;

			// average
			// int val = (r+b+g)/3;

			// luminosity
			//int val = (int) (0.21 * r + 0.71 * g + 0.07 * b);
			rgb[i] = val << 16 | val << 8 | val;
		}

		img.setRGB(0, 0, width, height, rgb, 0, width);
		return img;
	}

	private int max(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}

	private int min(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}
}
