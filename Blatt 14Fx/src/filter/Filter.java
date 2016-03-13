package filter;

import java.nio.IntBuffer;
import java.util.Stack;

import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;

public abstract class Filter {
	protected final static WritablePixelFormat<IntBuffer> FORMAT = PixelFormat.getIntArgbInstance();
	protected WritableImage img;
	protected int[] rgb;
	protected int width;
	protected int height;

	public Filter() {
	}

	public void setData(Stack<WritableImage> stack, WritableImage img) {
		this.img = img;
		width = (int) img.getWidth();
		height = (int) img.getHeight();
		PixelReader reader = img.getPixelReader();
		rgb = new int[width * height];
		reader.getPixels(0, 0, width, height, FORMAT, rgb, 0, width);

		WritableImage im = new WritableImage(reader, width, height);
		stack.push(im);
	}
	
	protected void buffer2Img() {
		PixelWriter writer = img.getPixelWriter();
		writer.setPixels(0, 0, width, height, FORMAT, rgb, 0, width);
	}

	public abstract WritableImage doFilter();
}
