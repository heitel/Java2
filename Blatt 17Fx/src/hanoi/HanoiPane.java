package hanoi;

import java.util.Stack;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HanoiPane extends ResizeableCanvas {
	private final static Color[] COLOR = { Color.THISTLE, Color.CRIMSON, Color.STEELBLUE, Color.CORAL, Color.DARKCYAN,
			Color.GOLD, Color.OLIVEDRAB, Color.LIGHTGREEN, Color.DARKGRAY, Color.CADETBLUE };
	private Point2D startPt = null;
	private int anz = 4;

	@SuppressWarnings("unchecked")
	private Stack<Scheibe> stab[] = new Stack[3];
	private Rectangle2D cr = null;
	private Scheibe current = null;
	private int quelle;
	private int count = 0;
	private Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), e-> timer(e)));
	private DiskMover dm = null;
	
	public HanoiPane() {
		timer.setCycleCount(Timeline.INDEFINITE);
		for (int i = 0; i < stab.length; i++) {
			stab[i] = new Stack<Scheibe>();
		}
		setAnz(anz);
		setOnMousePressed(e -> press(e));
		setOnMouseDragged(e -> drag(e));
		setOnMouseReleased(e -> release(e));
	}

	public int getAnz() {
		return anz;
	}

	public void setAnz(int anz) {
		timer.stop();
		this.anz = anz;
		
		for (int i = 0; i < stab.length; i++) {
			stab[i].clear();
		}
		
		for (int i = 0; i < anz; i++) {
			stab[0].add(new Scheibe(COLOR[i % COLOR.length], anz-1-i));
		}
		adjust();
		count = 0;
	}

	@Override
	public void resize(double width, double height) {
		super.resize(width, height);
	
		double dx = width / (6 * anz);
		double dy = height / anz;
		for (int i = 0; i < stab.length; i++) {
			for (int j = 0; j < stab[i].size(); j++) {
				Scheibe s = stab[i].get(j);
				int nr = anz-1 - s.getNr();
				Rectangle2D r = new Rectangle2D(nr * dx + i * 2 * anz * dx, height - (j + 1) * dy, 2 * (anz - nr) * dx,
						dy);
				s.setR(r);
			}
		}
		draw();
	}

	public void animate() {
		setAnz(getAnz());
	
		dm = new DiskMover(0, 2, anz);
		timer.play();
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < stab.length; i++) {
			for (int j = 0; j < stab[i].size(); j++) {
				Scheibe s = stab[i].get(j);
				s.draw(gc);
			}
		}
	}

	private void timer(ActionEvent e) {
		if (dm != null) {
			if (dm.hasMoreMoves()) {
				Move m = dm.nextMove();
				moveDisk(m.from, m.to);
			} else {
				timer.stop();
			}
			adjust();
		}
	}

	private void adjust() {
		resize(getWidth(), getHeight());
	}

	private void press(MouseEvent e) {
		startPt = new Point2D(e.getX(), e.getY());

		for (int i = 0; i < stab.length; i++) {
			if (!stab[i].isEmpty()) {
				Scheibe s = stab[i].peek();
				if (s.contains(startPt)) {
					cr = s.getR();
					current = s;
					quelle = i;
					break;
				}
			}
		}
	}

	private void drag(MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		double offX = x - startPt.getX();
		double offY = y - startPt.getY();
		if (current != null) {
			current.setR(new Rectangle2D(cr.getMinX() + offX, cr.getMinY() + offY, cr.getWidth(), cr.getHeight()));
		}

		draw();

		double width = getWidth() / 3;
		double height = getHeight();
		double x1 = (int) x / (int) (width) * width;
		GraphicsContext gc = getGraphicsContext2D();
		gc.setStroke(Color.CORNFLOWERBLUE);
		gc.strokeRect(x1, 0, width, height);
	}

	private void release(MouseEvent e) {
		startPt = null;
		double width = getWidth() / 3;
		int x1 = (int) e.getX() / (int) (width);
		if (quelle != x1) {
			int zielNr = 1000;
			if (!stab[x1].isEmpty()) {
				zielNr = stab[x1].peek().getNr();
			}
			if (zielNr > current.getNr()) {
				moveDisk(quelle, x1);
			}
		}

		current = null;
		adjust();		
	}

	private void moveDisk(int from, int to) {
		stab[to].push(stab[from].pop());
		count++;
		System.out.println(count +". " + from + " -> " + to);
	}
}
