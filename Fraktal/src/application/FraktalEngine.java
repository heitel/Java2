package application;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FraktalEngine {
	public final static int MAX = 256; // Größe der Farbtabelle festlegen
	public final static int MANDEL = 0; // Typ Mandelbrot
	public final static int JULIA = 1; // Typ Julia Set
	public final static int MU = 0; // µ-Ebene
	public final static int RECIPMU = 1; // 1/µ-Ebene
	public final static int LAMBDA = 2; // lambda-Ebene
	public final static int RECIPLAMBDA = 3; // 1/lambda-Ebene
	public final static int CPU_COUNT = 4;

	// Parameter zum Julia set
	public final static double CONSTC[] = { -0.12375, 0.56508, // 0
			-0.12, 0.74, // 1
			-0.481762, -0.531657, // 2
			-0.39054, -0.58679, // 3
			0.27334, 0.00742, // 4
			-0.11, 0.6557, // 5
			0.11031, -0.67037, // 6
			-0.194, 0.6557, // 7
			-0.15652, 1.03225, // 8
			-0.74543, 0.11301, // 9
			0.113008, -0.745429, // 10
			-0.53165, -0.48176, // 11
			-0.11, 0.67, // 12
			0.0, 0.0, // 13
			0.0, 1.0 // 14
	};

	private double xmin, ymin;
	private double dx;
	private int type;
	private int ebene;
	private double cr, ci;
	private Color[] colorTable;
	private WritableImage bImage;
	
	public FraktalEngine(double xmin, double ymin, double dx, int type, int ebene, 
			double cr, double ci, Color[] colorTable, WritableImage bImage) {
		this.xmin = xmin;
		this.ymin = ymin;
		this.dx = dx;
		this.type = type;
		this.ebene = ebene;
		this.cr = cr;
		this.ci = ci;
		this.colorTable = colorTable;
		this.bImage = bImage;
	}
	
	public void doCalc(boolean synch) {
		long startTime = System.nanoTime();
		Worker wa[] = new Worker[CPU_COUNT];

		for (int i = 0; i < CPU_COUNT; i++) {
			Worker w = null;

			switch (type) {
			case MANDEL:
				w = new MandelWorker(this, i);
				break;

			case JULIA:
				w = new JuliaWorker(this, i);
				break;
			}
			w.start();
			wa[i] = w;
		}
		if (synch) {
			for (int i = 0; i < wa.length; i++) {
				try {
					if (wa[i] != null) {
						wa[i].join();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// // Wie lange hat die Berechnung gedauert?
			long diff = System.nanoTime() - startTime;
			System.out.println(diff / 1e6 + "ms");

		}
	}

	public void calcMandel(int tNr) {
		if (bImage != null) {
			PixelWriter pw = bImage.getPixelWriter();
			double width = bImage.getWidth();
			double height = bImage.getHeight();
			int intervall = (int) (width / CPU_COUNT);

			double x = xmin + (tNr * intervall) * dx;
			double y;
			//
	
			for (int i = tNr * intervall; i < (tNr + 1) * intervall; i++) {
				if (tNr == 0) {
					// statusBar.setProgress(((i + 1) * 100 / width)*CPU_COUNT);
				}
				y = ymin;
				for (int j = 0; j < height; j++) {
					int farbe = mandelfarbe(x, y);
					pw.setColor(i, j, colorTable[farbe]);
					y += dx;
				}
				x += dx;
			}
		}
	}

	public void calcJulia(int tNr) {
		if (bImage != null) {
			PixelWriter pw = bImage.getPixelWriter();
			double width = bImage.getWidth();
			double height = bImage.getHeight();
			int intervall = (int) (width / CPU_COUNT);

			double x = xmin + (tNr * intervall) * dx;
			double y;
			//

			for (int i = tNr * intervall; i < (tNr + 1) * intervall; i++) {
				if (tNr == 0) {
					// statusBar.setProgress(((i + 1) * 100 / width)*CPU_COUNT);
				}
				y = ymin;
				for (int j = 0; j < height; j++) {
					int farbe = juliafarbe(x, y);
					pw.setColor(i, j, colorTable[farbe]);
					y += dx;
				}
				x += dx;
			}
		}
	}

	/**
	 * Berechnet die Farbe des Punktes z -> z^2 + c
	 * 
	 * @param x
	 *            Realteil von z
	 * @param y
	 *            Imaginärteil von z
	 * @return der Farbindex
	 */
	private int juliafarbe(double x, double y) {
		// Transformation
		Complex z = new Complex(x, y);
		switch (ebene) {
		case MU:
			break;
		case RECIPMU:
			z = z.over(0.8);
			z = z.reciprocal();
			break;
		case LAMBDA:
			z = z.times(z).minus(z).negation();
			break;
		case RECIPLAMBDA:
			z = z.over(0.7);
			z = z.reciprocal();
			z = z.times(z).minus(z).negation();
			break;
		}
		x = z.x;
		y = z.y;
		//
		int i = 0;
		double real = x;
		double imag = y;
		double tmp = 0.0;
		//
		for (; i < MAX && tmp < 4.0; i++) {
			tmp = real * real - imag * imag;
			imag = 2.0 * imag * real + ci;
			real = tmp + cr;
		}
		return i - 1;
	}

	private int mandelfarbe(double cx, double cy) {
		// Tranformation
		Complex z = new Complex(cx, cy);
		switch (ebene) {
		case MU:
			break;
		case RECIPMU:
			z = z.over(0.8);
			z = z.reciprocal();
			break;
		case LAMBDA:
			z = z.times(z).minus(z).negation();
			break;
		case RECIPLAMBDA:
			z = z.over(0.7);
			z = z.reciprocal();
			z = z.times(z).minus(z).negation();
			break;
		}
		cx = z.x;
		cy = z.y;
		//
		int i = 0;
		double real = 0.0;
		double imag = 0.0;
		double tmp = 0.0;
		//
		for (; i < MAX && tmp < 4.0; i++) {
			tmp = real * real - imag * imag;
			imag = 2.0 * imag * real + cy;
			real = tmp + cx;
		}
		return i - 1;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public void setYmin(double ymin) {
		this.ymin = ymin;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public int getEbene() {
		return ebene;
	}

	public void setEbene(int ebene) {
		this.ebene = ebene;
	}

	public double getCr() {
		return cr;
	}

	public double getCi() {
		return ci;
	}

	public void setbImage(WritableImage bImage) {
		this.bImage = bImage;
	}

	public void setParameter(int index) {
		cr = CONSTC[2 * index];
		ci = CONSTC[2 * index + 1];
	}

}
