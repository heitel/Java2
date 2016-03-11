package filter;

import java.awt.image.BufferedImage;

public class HorizontalFlipFilter extends Filter {

	public HorizontalFlipFilter() {
	}

	@Override
	public BufferedImage doFilter() {
		for (int i = 0; i < height/2; i++) {
			for (int j = 0; j < width; j++) {
				int tmp = rgb[i*width+j];
				rgb[i*width+j] = rgb[(height-i-1)*width+j];
				rgb[(height-i-1)*width+j] = tmp;
			}			
		}
		img.setRGB(0, 0, width, height, rgb, 0, width);
		return img;
	}

}
