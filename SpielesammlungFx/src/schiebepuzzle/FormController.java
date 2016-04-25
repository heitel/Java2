package schiebepuzzle;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class FormController {
	@FXML
	private BorderPane root;

	private GridPane grid = new GridPane();
	private final static int DIM = 4;
	private PuzzlePart[][] part = new PuzzlePart[DIM][DIM];
	private Stack<Point> undoStack = new Stack<>();

	public FormController() {

	}

	@FXML
	private void initialize() {
		try {
			SquareRegion square = new SquareRegion();
			square.add(grid);
			grid.setEffect(new DropShadow());
			//grid.setStyle("-fx-border-size: 5; -fx-border-color:red");
			root.setCenter(square);

			BufferedImage bimg = ImageIO.read(new File("img/tgie2.jpg"));
			int w = bimg.getWidth() / DIM;
			int h = bimg.getHeight() / DIM;
			for (int i = 0; i < part.length; i++) {
				for (int j = 0; j < part[i].length; j++) {
					BufferedImage im = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
					Graphics g = im.getGraphics();
					int xPos = j;
					int yPos = i;
					g.drawImage(bimg, 0, 0, w, h, xPos * w, yPos * h, (xPos + 1) * w, (yPos + 1) * h, null);

					Image img = SwingFXUtils.toFXImage(im, null);

					part[i][j] = new PuzzlePart(img, i * DIM + j);
					if (i == DIM - 1 && j == DIM - 1) {
						part[i][j].setVisible(false);
					}
					part[i][j].setOnMouseClicked(e -> click(e));
					grid.add(part[i][j], j, i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private void click(MouseEvent e) {
		PuzzlePart p = (PuzzlePart) e.getSource();
		movePart(p.getNr());
	}

	public void movePart(int nr) {
		Point p = findPos(nr);

		if (p != null) {
			movePos(p, true);
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
		grid.getChildren().clear();
		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part[i].length; j++) {
				grid.add(part[i][j], j, i);
			}
		}
	}

	@FXML
	private void openImg() {

	}

	@FXML
	private void cleanup() {

	}

	@FXML
	private void shuffle() {
		for (int i = 0; i < 100; i++) {
			doRandomMove();
		}
	}

	@FXML
	private void showNumbers(ActionEvent e) {
		CheckMenuItem item = (CheckMenuItem) e.getSource();

		for (int i = 0; i < part.length; i++) {
			for (int j = 0; j < part.length; j++) {
				part[i][j].setShowNr(item.isSelected());
			}
		}
	}

	@FXML
	private void quit() {
		System.exit(0);
	}

}
