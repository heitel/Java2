package zeichnen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements Serializable {
	private static final long serialVersionUID = -2149084930885623161L;
	protected transient Rectangle2D r;
	protected transient Color color;
	private transient InfoDialog dlg = null;

	public Shape(Color c, double x, double y, double width, double height) {
		color = c;
		r = new Rectangle2D(x, y, width, height);
	}

	public void showInfoDialog(ZeichnenCanvas canvas) {
		if (dlg == null) {
			dlg = new InfoDialog(this, canvas);
			dlg.show();
		} else {
			dlg.toFront();
		}
	}

	public void closeInfoDialog() {
		this.dlg = null;
	}

	public Rectangle2D getR() {
		return r;
	}

	public void setR(Rectangle2D r) {
		this.r = r;
		if (dlg != null) {
			dlg.update();
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color c) {
		color = c;
	}

	private void writeObject(ObjectOutputStream oos) throws IOException {
		oos.writeDouble(color.getRed());
		oos.writeDouble(color.getGreen());
		oos.writeDouble(color.getBlue());
		oos.writeDouble(color.getOpacity());

		oos.writeDouble(r.getMinX());
		oos.writeDouble(r.getMinY());
		oos.writeDouble(r.getWidth());
		oos.writeDouble(r.getHeight());
	}

	private void readObject(ObjectInputStream ois) throws IOException {
		double red = ois.readDouble();
		double green = ois.readDouble();
		double blue = ois.readDouble();
		double op = ois.readDouble();
		color = new Color(red, green, blue, op);

		double x = ois.readDouble();
		double y = ois.readDouble();
		double w = ois.readDouble();
		double h = ois.readDouble();
		r = new Rectangle2D(x, y, w, h);
	}

	public abstract void draw(GraphicsContext gc);

	public abstract double calcArea();

	public abstract double calcUmfang();

	public abstract boolean contains(Point2D p);

	public abstract String getTitle();

	public abstract javafx.scene.shape.Shape convertToFx();
}
