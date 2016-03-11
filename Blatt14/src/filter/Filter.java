package filter;

import java.awt.image.BufferedImage;
import java.util.Stack;

public abstract class Filter {
	protected BufferedImage img;
	protected int[] rgb;
	protected int width;
	protected int height;

	public Filter() {
	}

	public void setData(Stack<BufferedImage> stack, BufferedImage img) {
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
		rgb = img.getRGB(0, 0, width, height, null, 0, width);
		
		BufferedImage im = new BufferedImage(width, height,
				img.getType());
		im.setData(img.getData());
		stack.push(im);
	}
	
	public abstract BufferedImage doFilter();
}
