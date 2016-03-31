package fibonacci;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class FibonacciCanvas extends ResizeableCanvas {
	private ArrayList<Long> fib = calc();
	private Color color[] = { Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW };
	private int scale = 1;
	private boolean left = true;

	public FibonacciCanvas() {
		setOnMousePressed(e -> click(e));
		setOnScroll(e -> scroll(e));
	}

	private void scroll(ScrollEvent e) {
		double val = e.getDeltaY()/40.0;
		scale += (int)val;
		if (scale <= 1) {
			scale = 1;
		} else {
			if (scale > 40) {
				scale = 40;
			}
		}
		draw();
	}

	private void click(MouseEvent e) {
		left = !left;
		draw();
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		if (left) {
			spiralLeft(gc);
		} else {
			spiralRight(gc);
		}
	}

	private void spiralRight(GraphicsContext g) {
		int cx = (int) Math.round(getWidth() / 2);
		int cy = (int) Math.round(getHeight() / 2);

		for (int i = 0; i < 5; i++) {
			int z = (int) fib.get(i * 4).longValue() * scale;
			int tmp = z;
			cy -= z;
			g.setStroke(color[0]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx, cy, 2 * z, 2 * z, 90, 90, ArcType.OPEN);

			cx += z;
			z = (int) fib.get(i * 4 + 1).longValue() * scale;
			g.setStroke(color[1]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx - z, cy, 2 * z, 2 * z, 0, 90, ArcType.OPEN);

			cx -= tmp;
			cy += z;
			tmp = z;
			z = (int) fib.get(i * 4 + 2).longValue() * scale;
			g.setStroke(color[2]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx - z, cy - z, 2 * z, 2 * z, 270, 90, ArcType.OPEN);

			z = (int) fib.get(i * 4 + 3).longValue() * scale;
			cx -= z;
			cy -= tmp;
			g.setStroke(color[3]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx, cy - z, 2 * z, 2 * z, 180, 90, ArcType.OPEN);
		}
	}

	private void spiralLeft(GraphicsContext g) {
		int cx = (int) Math.round(getWidth() / 2);
		int cy = (int) Math.round(getHeight() / 2);
		int tmp = 0;

		for (int i = 0; i < 5; i++) {
			int z = (int) fib.get(i * 4).longValue() * scale;
			g.setStroke(color[0]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx, cy - z, 2 * z, 2 * z, 180, 90, ArcType.OPEN);

			cx += z;
			cy -= tmp;
			tmp = z;
			z = (int) fib.get(i * 4 + 1).longValue() * scale;
			g.setStroke(color[1]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx - z, cy - z, 2 * z, 2 * z, 270, 90, ArcType.OPEN);

			cx -= tmp;
			z = (int) fib.get(i * 4 + 2).longValue() * scale;
			cy -= z;
			tmp = z;
			g.setStroke(color[2]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx - z, cy, 2 * z, 2 * z, 0, 90, ArcType.OPEN);

			z = (int) fib.get(i * 4 + 3).longValue() * scale;
			cx -= z;
			g.setStroke(color[3]);
			g.strokeRect(cx, cy, z, z);
			g.setStroke(Color.BLACK);
			g.strokeArc(cx, cy, 2 * z, 2 * z, 90, 90, ArcType.OPEN);
			cy += z;
			tmp = z;
		}
	}

	private ArrayList<Long> calc() {
		ArrayList<Long> liste = new ArrayList<Long>(128);
		long a1 = 1;
		long a2 = 1;
		long a3 = 0;

		liste.add(a1);
		liste.add(a2);

		for (int i = 0; i < 128 && a3 < Long.MAX_VALUE / 2; i++) {
			a3 = a1 + a2;
			a1 = a2;
			a2 = a3;
			liste.add(a3);
//			System.out.println((i + 3) + ":" + a3);
		}

		return liste;
	}

}
