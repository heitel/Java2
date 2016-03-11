package baum;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BaumPanel extends JPanel {
	private Baum baum;

	public BaumPanel() {
	}

	public void createTree(int level, int scale, int type) {
		baum = new Baum(level, scale, type);

		update();
	}

	private void update() {
		Point max = baum.getMax();
		setPreferredSize(new Dimension(max.x, max.y));
		getParent().revalidate();
		repaint();
	}

	public void setLevel(int level) {
		if (baum != null) {
			baum.setLevel(level);
			update();
		}
	}

	public void setScale(int scale) {
		if (baum != null) {
			baum.setScale(scale);
			update();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (baum != null) {
			baum.draw(g);
		}
	}

	public void save(File f) {
		try {
			BufferedImage bi = new BufferedImage(getWidth(), getHeight(),
					BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			paintComponent(g);
			ImageIO.write(bi, "png", f);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
