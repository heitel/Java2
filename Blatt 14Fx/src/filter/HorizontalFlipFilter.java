package filter;

import javafx.scene.image.WritableImage;

public class HorizontalFlipFilter extends Filter {

	public HorizontalFlipFilter() {
	}

	@Override
	public WritableImage doFilter() {
		for (int i = 0; i < height/2; i++) {
			for (int j = 0; j < width; j++) {
				int tmp = rgb[i*width+j];
				rgb[i*width+j] = rgb[(height-i-1)*width+j];
				rgb[(height-i-1)*width+j] = tmp;
			}			
		}
		
		buffer2Img();
		return img;
	}

}
