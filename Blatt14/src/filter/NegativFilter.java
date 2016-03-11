package filter;

import java.awt.image.BufferedImage;


public class NegativFilter extends Filter {
	public NegativFilter() {
	}

	@Override
	public BufferedImage doFilter() {
		for (int i = 0; i < rgb.length; i++) {
			int red = 255 - (rgb[i]>>16) & 0xFF;
			int green = 255 - (rgb[i]>>8) & 0xFF;
			int blue = 255 - rgb[i] & 0xFF;
			rgb[i] = red<<16 | green << 8 | blue;
		}
		
		img.setRGB(0, 0, width, height, rgb, 0, width);
		return img;
	}

}
