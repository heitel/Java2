package filter;

import java.awt.image.BufferedImage;


public class RotateCWFilter extends Filter {

	public RotateCWFilter() {
	}

	@Override
	public BufferedImage doFilter() {
		BufferedImage im = new BufferedImage(height, width, img.getType());
		int[] rgbNew = im.getRGB(0, 0, height, width, null, 0, height);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				rgbNew[i*height+j] = rgb[(height-j-1)*width+i];
			}
		}
		im.setRGB(0, 0, height, width, rgbNew, 0, height);
		return im;
	}

}
