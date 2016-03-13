package digitaleBildverarbeitung;

import java.util.Stack;

import filter.Filter;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageCanvas extends ResizeableCanvas {
	private WritableImage img;
	private Stack<WritableImage> undoStack = new Stack<>();

	public ImageCanvas() {
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		double width = getWidth();
		double height = getHeight();
		gc.setFill(Color.GRAY);
		gc.fillRect(0, 0, width, height);

		if (img != null) {
			double scaleX = width / img.getWidth();
			double scaleY = height / img.getHeight();
			double scale = Math.min(scaleX, scaleY);

			double imgW = img.getWidth() * scale;
			double imgH = img.getHeight() * scale;
			double x = (width - imgW) / 2;
			double y = (height - imgH) / 2;
			gc.drawImage(img, x, y, imgW, imgH);
		}
	}

	public void doFilter(ActionEvent e) {
		try {
			MenuItem item = (MenuItem) e.getSource();
			String filterName = "filter." + (String) item.getUserData();
			Class<?> c = Class.forName(filterName);
			Filter filter = (Filter) c.newInstance();
			filter.setData(undoStack, img);
			img = filter.doFilter();
			draw();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (OutOfMemoryError e1) {
			e1.printStackTrace();
		}
	}

	public void doBack(ActionEvent e) {
		if (!undoStack.isEmpty()) {
			img = undoStack.pop();
			draw();
		} else {
			System.out.println("Es gibt kein zurück!");
		}
	}

	public void setImg(WritableImage img) {
		this.img = img;
		undoStack.clear();
		draw();
	}

	public WritableImage getImg() {
		return img;
	}
}
