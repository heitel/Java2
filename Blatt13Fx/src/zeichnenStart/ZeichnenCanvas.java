package zeichnenStart;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import zeichnenStart.ZeichnenPane.Tool;

public class ZeichnenCanvas extends ResizeableCanvas {
	private static final Color[] COLOR = { Color.RED, Color.AQUA, Color.YELLOW, Color.BLUEVIOLET };
	private Tool tool = Tool.ARROW;
	private Rectangle2D rR = null;
	private Point2D startPt = null;
	private ArrayList<Shape> data = new ArrayList<Shape>();
	private Shape current = null;
	private Rectangle2D cr = null;
	private ContextMenu cm = new ContextMenu();

	public ZeichnenCanvas() {
		setOnMousePressed(e -> press(e));
		setOnMouseDragged(e -> drag(e));
		setOnMouseReleased(e -> release(e));

		MenuItem front = new MenuItem("Nach vorne");
		front.setOnAction(e -> front(e));
		MenuItem back = new MenuItem("Nach hinten");
		back.setOnAction(e -> back(e));
		MenuItem clear = new MenuItem("Löschen");
		clear.setOnAction(e -> clear(e));
		MenuItem info = new MenuItem("Infodialog...");
		info.setOnAction(e -> info(e));
		cm.getItems().addAll(front, back, new SeparatorMenuItem(), clear, new SeparatorMenuItem(), info);
	}

	private void front(ActionEvent e) {
		if (current != null) {
			data.remove(current);
			data.add(current);
			current = null;
		}
		draw();
	}

	private void back(ActionEvent e) {
		if (current != null) {
			data.remove(current);
			data.add(0, current);
			current = null;
		}
		draw();
	}

	private void clear(ActionEvent e) {
		if (current != null) {
			data.remove(current);
			current = null;
		}
		draw();
	}

	private void info(ActionEvent e) {
		if (current != null) {
			current.showInfoDialog(this);
		}
	}

	private Color getRandomColor() {
		int zz = (int) (Math.random() * COLOR.length);
		return COLOR[zz];
	}

	private void press(MouseEvent e) {
		Point2D here = new Point2D(e.getX(), e.getY());
		current = null;
		cr = null;
		if (tool == Tool.ARROW) {
			for (int i = data.size() - 1; i >= 0; i--) {
				Shape s = data.get(i);
				if (s.contains(here)) {
					current = s;
					cr = s.getR();
					if (e.getButton() == MouseButton.SECONDARY) {
						cm.show(this, e.getScreenX(), e.getScreenY());
					}
					break;
				}
			}
		}
		startPt = here;
	}

	private void drag(MouseEvent e) {
		switch (tool) {
		case ARROW:
			double offX = e.getX() - startPt.getX();
			double offY = e.getY() - startPt.getY();
			if (current != null) {
				current.setR(new Rectangle2D(cr.getMinX() + offX, cr.getMinY() + offY, cr.getWidth(), cr.getHeight()));
			}
			break;
		case SQUARE:
		case CIRCLE:
		case POLY:
			double left = Math.min(startPt.getX(), e.getX());
			double top = Math.min(startPt.getY(), e.getY());
			double width = Math.abs(startPt.getX() - e.getX());
			double height = Math.abs(startPt.getY() - e.getY());
			rR = new Rectangle2D(left, top, width, height);
			break;
		}

		draw();
	}

	private void release(MouseEvent e) {
		if (rR != null) {
			switch (tool) {
			case ARROW:

				break;
			case SQUARE:
				data.add(new RectShape(getRandomColor(), rR.getMinX(), rR.getMinY(), rR.getWidth(), rR.getHeight()));
				break;
			case CIRCLE:
				data.add(new OvalShape(getRandomColor(), rR.getMinX(), rR.getMinY(), rR.getWidth(), rR.getHeight()));
				break;
			case POLY:
				data.add(
						new TriangleShape(getRandomColor(), rR.getMinX(), rR.getMinY(), rR.getWidth(), rR.getHeight()));
				break;
			}
		}
		startPt = null;
		rR = null;

		draw();
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());

		// Ergänze Code zum Zeichnen der Objekte

		if (rR != null) {
			gc.setLineDashes(1, 2, 3);
			gc.strokeRect(rR.getMinX(), rR.getMinY(), rR.getWidth(), rR.getHeight());
		}
	}

	public void setTool(Tool t) {
		tool = t;
	}

	public ArrayList<Shape> getData() {
		return data;
	}

	public void setData(ArrayList<Shape> data) {
		this.data = data;
		draw();
	}

	public void clearData() {
		data.clear();
		draw();
	}
}
