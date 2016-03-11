package fibonacci;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class FibonacciPanel extends JPanel implements MouseListener,
		MouseWheelListener {
	private ArrayList<Long> fib = calc();
	private Color color[] = { Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW };
	private BasicStroke stroke = new BasicStroke(2.0f);
	private int scale = 1;
	private boolean left = true;

	public FibonacciPanel() {
		System.out.println("Umlauf | Länge der Spirale");
		System.out.println("-------+------------------");
		for (int i = 1; i < 8; i++) {
			System.out.println(i + "      | " + calcLength(i));
		}
		
		addMouseListener(this);
		addMouseWheelListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(stroke);
		if (left) {
			spiralLeft(g2);
		} else {
			spiralRight(g2);
		}
		

	}

	private void spiralRight(Graphics2D g) {
		int cx = getWidth() / 2;
		int cy = getHeight() / 2;

		for (int i = 0; i < 5; i++) {
			int z = (int) fib.get(i * 4).longValue() * scale;
			int tmp = z;
			cy -= z;
			g.setColor(color[0]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx, cy, 2 * z, 2 * z, 90, 90);

			cx += z;
			z = (int) fib.get(i * 4 + 1).longValue() * scale;
			g.setColor(color[1]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx - z, cy, 2 * z, 2 * z, 0, 90);

			cx -= tmp;
			cy += z;
			tmp = z;
			z = (int) fib.get(i * 4 + 2).longValue() * scale;
			g.setColor(color[2]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx - z, cy - z, 2 * z, 2 * z, 270, 90);

			z = (int) fib.get(i * 4 + 3).longValue() * scale;
			cx -= z;
			cy -= tmp;
			g.setColor(color[3]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx, cy - z, 2 * z, 2 * z, 180, 90);
		}
	}

	private void spiralLeft(Graphics2D g) {
		int cx = getWidth() / 2;
		int cy = getHeight() / 2;
		int tmp = 0;

		for (int i = 0; i < 5; i++) {
			int z = (int) fib.get(i * 4).longValue() * scale;
			g.setColor(color[0]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx, cy - z, 2 * z, 2 * z, 180, 90);

			cx += z;
			cy -= tmp;
			tmp = z;
			z = (int) fib.get(i * 4 + 1).longValue() * scale;
			g.setColor(color[1]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx - z, cy - z, 2 * z, 2 * z, 270, 90);

			cx -= tmp;
			z = (int) fib.get(i * 4 + 2).longValue() * scale;
			cy -= z;
			tmp = z;
			g.setColor(color[2]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx - z, cy, 2 * z, 2 * z, 0, 90);

			z = (int) fib.get(i * 4 + 3).longValue() * scale;
			cx -= z;
			g.setColor(color[3]);
			g.drawRect(cx, cy, z, z);
			g.setColor(Color.BLACK);
			g.drawArc(cx, cy, 2 * z, 2 * z, 90, 90);
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
		}

		return liste;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scale += e.getWheelRotation();
		if (scale <= 1) {
			scale = 1;
		} else {
			if (scale > 20) {
				scale = 20;
			}
		}
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		left = !left;
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
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

	private double calcLength(int n) {
		long sum = 0;
		for (int i = 0; i < n * 4; i++) {
			sum += fib.get(i).longValue();
		}

		return sum * Math.PI/2;
	}
}
