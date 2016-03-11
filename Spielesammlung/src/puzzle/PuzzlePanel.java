package puzzle;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

@SuppressWarnings("serial")
public class PuzzlePanel extends JPanel {
	private final static String[] NAMES = {"dhbw.jpg"};
	private final static File IMAGE_FILE = new File("imgPuzzle/" + NAMES[0]);
	private final static int DIM = 4;
	private PuzzlePart[][] part = new PuzzlePart[DIM][DIM];
	private BufferedImage img;
	private Stack<Point> undoStack = new Stack<>();

	public PuzzlePanel() {
		try {
			setLayout(new GridLayout(DIM, DIM));
			setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
			img = ImageIO.read(IMAGE_FILE);
			int w = img.getWidth() / DIM;
			int h = img.getHeight() / DIM;
			for (int i = 0; i < part.length; i++) {
				for (int j = 0; j < part[i].length; j++) {
					BufferedImage im = new BufferedImage(w, h,
							BufferedImage.TYPE_INT_RGB);
					Graphics g = im.getGraphics();
					int xPos = j;
					int yPos = i;
					g.drawImage(img, 0, 0, w, h, xPos * w, yPos * h, (xPos + 1)
							* w, (yPos + 1) * h, null);

					part[i][j] = new PuzzlePart(i * DIM + j, im);
					add(part[i][j]);
				}
			}
			part[DIM - 1][DIM - 1].setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void movePart(int nr) {
		Point p = findPos(nr);
	
		if (p != null) {
			movePos(p, true);
		}
	}

	public void doRandomMove() {
		Point p = findEmpty();
	
		if (p != null) {
			boolean done = false;
			do {
				int rnd = getRandom();
				switch (rnd) {
				case 0:
					if (p.y > DIM - 2) {
						break;
					}
					p.y++;
					System.out.println("unten" + p);
					movePos(p, true);
					done = true;
					break;
				case 1:
					if (p.y == 0) {
						break;
					}
					p.y--;
					System.out.println("oben" + p);
					movePos(p, true);
					done = true;
					break;
				case 2:
					if (p.x == 0) {
						break;
					}
					p.x--;
					System.out.println("links" + p);
					movePos(p, true);
					done = true;
					break;
				case 3:
					if (p.x > DIM - 2) {
						break;
					}
					p.x++;
					System.out.println("rechts" + p);
					movePos(p, true);
					done = true;
					break;
				}
			} while (!done);
		}
	}

	public void doUndo() {
		if (!undoStack.isEmpty()) {
			Point p = undoStack.pop();
			movePos(p, false);
		}
	}

	private Point findPos(int nr) {
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				if (part[i][j].getNr() == nr) {
					return new Point(j, i);
				}
			}
		}
		return null;
	}

	private void movePos(Point p, boolean b) {
		PuzzlePart tmp;
		if (p.y < DIM - 1 && !part[p.y + 1][p.x].isVisible()) { // UNTEN
			tmp = part[p.y][p.x];
			part[p.y][p.x] = part[p.y + 1][p.x];
			part[p.y + 1][p.x] = tmp;
			if (b) {
				p.y++;
				undoStack.push(p);
			}
			moved();
			return;
		}
		if (p.y > 0 && !part[p.y - 1][p.x].isVisible()) { // OBEN
			tmp = part[p.y][p.x];
			part[p.y][p.x] = part[p.y - 1][p.x];
			part[p.y - 1][p.x] = tmp;
			if (b) {
				p.y--;
				undoStack.push(p);
			}
			moved();
			return;
		}
		if (p.x > 0 && !part[p.y][p.x - 1].isVisible()) { // LINKS
			tmp = part[p.y][p.x];
			part[p.y][p.x] = part[p.y][p.x - 1];
			part[p.y][p.x - 1] = tmp;
			if (b) {
				p.x--;
				undoStack.push(p);
			}
			moved();
			return;
		}
		if (p.x < DIM - 1 && !part[p.y][p.x + 1].isVisible()) { // RECHTS
			tmp = part[p.y][p.x];
			part[p.y][p.x] = part[p.y][p.x + 1];
			part[p.y][p.x + 1] = tmp;
			if (b) {
				p.x++;
				undoStack.push(p);
			}
			moved();
			return;
		}
	}

	private void moved() {
		removeAll();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				add(part[i][j]);
			}
		}
		revalidate();
	}

	private Point findEmpty() {
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				if (!part[i][j].isVisible()) {
					return new Point(j, i);
				}
			}
		}
		return null;
	}

	private int getRandom() {
		return (int) (Math.random() * 4);
	}
}
