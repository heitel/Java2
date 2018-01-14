package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class FraktalCanvas extends ResizeableCanvas {

	private Color colorTable[] = new Color[FraktalEngine.MAX];

	// linke obere Ecke der Ebene
	private double xmin, xminOld;
	private double ymin, yminOld;

	// Umrechnungsfaktoren pixel -> Koordinaten
	private double dx = 0.01;

	// TransformationsEbene
	private Point2D lastMouse;
	private FraktalEngine engine;

	public FraktalCanvas() {
		initColorTable();
		initEbene();
		engine = new FraktalEngine(xmin, ymin, dx, FraktalEngine.MANDEL, FraktalEngine.MU, FraktalEngine.CONSTC[14],
				FraktalEngine.CONSTC[15], colorTable, bImage);
		// EventHandler
		setOnScroll(e -> wheel(e));
		setOnMouseMoved(e -> move(e));
		setOnMouseDragged(e -> drag(e));
		setOnMousePressed(e -> press(e));
		setOnMouseReleased(e -> release(e));
	}

	private void move(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		lastMouse = new Point2D(x, y);
	}

	private Point2D convertScreen2Real(double x, double y) {
		return new Point2D(x * dx + xmin, y * dx + ymin);
	}

	private void wheel(ScrollEvent e) {
		double faktor = 10;
		if (e.isAltDown()) {
			faktor = 2;
		}
		double deltaY = -e.getDeltaY();

		Point2D real1 = convertScreen2Real(lastMouse.getX(), lastMouse.getY());
		dx += (dx / faktor) * Math.signum(deltaY);
		dx = Math.abs(dx);
		Point2D real2 = convertScreen2Real(lastMouse.getX(), lastMouse.getY());

		xmin += real1.getX() - real2.getX();
		ymin += real1.getY() - real2.getY();

		doRecalc(false);
	}

	private void release(MouseEvent e) {
		doRecalc(true);
	}

	private void press(MouseEvent e) {
		lastMouse = new Point2D(e.getX(), e.getY());
		xminOld = xmin;
		yminOld = ymin;
	}

	private void drag(MouseEvent e) {
		Point2D currentMouse = new Point2D(e.getX(), e.getY());
		Point2D diff = currentMouse.subtract(lastMouse);

		xmin = xminOld - diff.getX() * dx;
		ymin = yminOld - diff.getY() * dx;
		doRecalc(false);
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.drawImage(bImage, 0, 0);
	}

	private void initEbene() {
		xmin = -1.5;
		ymin = -1.5;
		dx = 0.01;
	}

	private void initColorTable() {
		int max = colorTable.length;
		for (int i = 0; i < max; i++) {
			double tmp = (double) i / max;
			double h = tmp * 360.0;
			double b = 1.0 - tmp * tmp * tmp;
			colorTable[i] = Color.hsb(h, 0.9, b);
		}
	}

	public Color[] getColorTable() {
		return colorTable;
	}

	public void setType(int type) {
		engine.setType(type);
		initEbene();
		doRecalc(true);
	}

	public void setEbene(int index) {
		engine.setEbene(index);
		initEbene();
		doRecalc(true);
	}

	public void setParameter(int index) {
		engine.setParameter(index);
		doRecalc(true);
	}

	public void calcFraktal(boolean onScreen, boolean synch) {
		// setCursor(waitCursor);

		if (onScreen) {
			engine.setXmin(xmin);
			engine.setYmin(ymin);
			engine.setDx(dx);
			engine.setbImage(bImage);
			engine.doCalc(synch);
		} else {
			final int bigWidth = 2250 * 4;
			final int bigHeight = 1500 * 4;
			double scale = Math.min(bImage.getWidth() / bigWidth, bImage.getHeight() / bigHeight);
			WritableImage img = new WritableImage(bigWidth, bigHeight);
			double deltaX = dx * scale;
			Color[] cTable = new Color[colorTable.length];
			for (int i = 0; i < cTable.length; i++) {
				cTable[i] = new Color(colorTable[i].getRed(), colorTable[i].getGreen(), colorTable[i].getBlue(),
						colorTable[i].getOpacity());
			}
			FraktalEngine offEngine = new FraktalEngine(xmin, ymin, deltaX, engine.getType(), engine.getEbene(),
					engine.getCr(), engine.getCi(), cTable, img);
			offEngine.doCalc(true);
			Platform.runLater(() -> saveImg(img));
		}
	}

	public void doRecalc(boolean synch) {
		calcFraktal(true, synch);
		draw();
	}

	private int count = 0;

	public void animateColors() {
		float offset = 0.01f;
		count++;
		float hsb[] = new float[3];
		PixelReader pr = bImage.getPixelReader();
		PixelWriter pw = bImage.getPixelWriter();
		int width = (int) bImage.getWidth();
		int height = (int) bImage.getHeight();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = pr.getArgb(i, j);
				java.awt.Color.RGBtoHSB((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, hsb);
				hsb[0] += offset;
				hsb[0] = hsb[0] - (int) hsb[0];
				int tmp = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
				pw.setArgb(i, j, tmp);
			}

		}

		for (int i = 0; i < colorTable.length; i++) {
			int red = (int) (colorTable[i].getRed() * 255);
			int green = (int) (colorTable[i].getGreen() * 255);
			int blue = (int) (colorTable[i].getBlue() * 255);
			java.awt.Color.RGBtoHSB(red, green, blue, hsb);
			hsb[0] += offset;
			hsb[0] = hsb[0] - (int) hsb[0];
			if (count == 100) {
				System.out.println("initColorTable");
				initColorTable();
				count = 0;
				break;
			}
			int tmp = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
			colorTable[i] = Color.rgb((tmp >> 16) & 0xFF, (tmp >> 8) & 0xFF, tmp & 0xFF);
		}
		draw();
	}

	public void save() {
		calcFraktal(false, true);
	}

	private void saveImg(WritableImage img) {
		try {
			File f = new File("output.png");
			BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
			if (!ImageIO.write(bimg, "png", f)) {
				System.out.println("Fehler beim schreiben");
			} else {
				System.out.println("SChreiben OK");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
