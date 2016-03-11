package digitaleBildverarbeitung;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import filter.Filter;

public class ImageDisplay extends JPanel implements Printable {
	private BufferedImage image;
	private Stack<BufferedImage> undoStack = new Stack<BufferedImage>();
	private File file;

	public ImageDisplay() {

	}

	public void setFile(File file) {
		image = null;
		resetUndoStack();

		this.file = file;

		try {
			image = ImageIO.read(file);
			repaint();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image != null) {
			int width = getWidth();
			int height = getHeight();
			int imW = image.getWidth();
			int imH = image.getHeight();
			double scale = Math
					.max((double) imW / width, (double) imH / height);

			imW /= scale;
			imH /= scale;
			int x = (width - imW) / 2;
			int y = (height - imH) / 2;

			g.drawImage(image, x, y, imW, imH, null);
		}
	}

	public void doUndo() {
		if (!undoStack.empty()) {
			image = undoStack.pop();
			repaint();
		} else {
			JOptionPane.showMessageDialog(this, "Undo Stack is empty!");
		}
	}

	public void doFilter(String filterName) {
		try {
			Class<?> c = Class.forName(filterName);
			Filter filter = (Filter) c.newInstance();
			filter.setData(undoStack, image);
			image = filter.doFilter();
			repaint();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {

			resetUndoStack();
			JOptionPane.showMessageDialog(this, "Out Of Memory");
		}
	}

	private void resetUndoStack() {
		undoStack.removeAllElements();
	}

	public void doSave() {
		if (file != null) {
			try {
				ImageIO.write(image, "JPG", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			doSaveAs();
		}
	}

	public void doSaveAs() {
		JFileChooser chooser = new JFileChooser(".");
		if (JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(this)) {
			file = chooser.getSelectedFile();
			doSave();
		}
	}

	public void doPrint() {
		if (image != null) {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintable(this);
			if (job.printDialog()) {
				try {
					job.print();
				} catch (PrinterException pe) {
					System.out.println("Error printing: " + pe);
				}
			}
		}
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex > 0) {
			return NO_SUCH_PAGE;
		} else {
			double w = pageFormat.getImageableWidth();
			double h = pageFormat.getImageableHeight();
			int imW = image.getWidth();
			int imH = image.getHeight();
			double scale = Math.min(w/imW, h/imH);
			imW *= scale;
			imH *= scale;
			int x = (int)((w - imW) / 2);
			int y = (int)((h - imH) / 2);
			graphics.drawImage(image, x, y, imW, imH, null);
		}
		return PAGE_EXISTS;
	}
}
