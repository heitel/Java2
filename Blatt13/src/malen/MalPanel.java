package malen;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class MalPanel extends JPanel implements MouseListener,
		MouseMotionListener, Printable {
	private Point oldPoint;
	private Color currentColor = Color.RED;
	private int strokeSize = 10;
	private int style[] = { BasicStroke.CAP_ROUND, BasicStroke.CAP_SQUARE };
	private int styleIndex = 0;
	private Stroke stroke;
	private BufferedImage image;
	private Graphics2D g;
	private File file;

	public MalPanel() {
		doNew();
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(image, 0, 0, this);
	}

	public void doNew(){
		adjustStroke();
		image = new BufferedImage(1600, 1200,
				BufferedImage.TYPE_INT_ARGB);
		g = image.createGraphics();
		repaint();
	}
	
	public void setStrokeSize(int strokeSize) {
		this.strokeSize = strokeSize;
		adjustStroke();
	}

	private void adjustStroke() {
		stroke = new BasicStroke(strokeSize, style[styleIndex],
				BasicStroke.JOIN_MITER);
		
	}

	public void setStyleIndex(int idx) {
		styleIndex = idx;
		adjustStroke();
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		g.setColor(currentColor);
		g.setStroke(stroke);
		g.drawLine(oldPoint.x, oldPoint.y, p.x, p.y);
		oldPoint = p;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		oldPoint = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public void doSaveAs() {
		try {
			JFileChooser chooser = new JFileChooser(new File("."));

			if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
				ImageIO.write(image, "png", f);
				file = f;
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doOpen() {
		try {
			JFileChooser chooser = new JFileChooser(new File("."));

			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				File f = chooser.getSelectedFile();
				image = ImageIO.read(f);
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

	public void doSave() {
		try {
			if (file != null) {
				ImageIO.write(image, "png", file);
			} else {
				doSaveAs();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPrint() {
		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if (printJob.printDialog())
			try {
				printJob.print();
			} catch (PrinterException pe) {
				System.out.println("Error printing: " + pe);
			}
		
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		
		if (pageIndex > 0) {
			return (NO_SUCH_PAGE);
		} else {
			Graphics2D g2d = (Graphics2D) graphics;
			AffineTransform t = g2d.getTransform();
			g2d.setTransform(new AffineTransform());
			if (pageFormat.getOrientation() == PageFormat.LANDSCAPE) {
				t.rotate(-Math.PI / 2);
			}
			System.out.println(t);
			int imW = image.getWidth();
			int imH = image.getHeight();

			int width = (int) (pageFormat.getImageableWidth() * t.getScaleX());
			int height = (int) (pageFormat.getImageableHeight() * t.getScaleY());

			double sx = (double) width / imW;
			double sy = (double) height / imH;

			double scale = Math.min(sx, sy);

			int b = (int) (imW * scale);
			int h = (int) (imH * scale);

			int x = (width - b) / 2;
			int y = (height - h) / 2;

			if (pageFormat.getOrientation() == PageFormat.LANDSCAPE) {
				g2d.translate(t.getTranslateX(), t.getTranslateY());
				g2d.rotate(Math.PI / 2);
			}
			setDoubleBuffered(false);
			g2d.drawImage(image, x, y, b, h, null);

			setDoubleBuffered(true);

			return (PAGE_EXISTS);
		}

	}
}
