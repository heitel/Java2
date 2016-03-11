package monteCarlo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MonteCarloPanel extends JPanel implements MouseListener {
	private int anz = 1000;

	public MonteCarloPanel() {
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int count = 0;
		int cx = getWidth() / 2;
		int cy = getHeight() / 2;
		int max = Math.min(cx, cy);
		int max2 = max*2;
		int maxmax = max * max;

		g.setColor(Color.RED);
		g.fillRect(0, 0, max2, max2);

		g.setColor(Color.BLUE);
		g.fillOval(0, 0, max2, max2);

		g.setColor(Color.BLACK);
		for (int i = 0; i < anz; i++) {
			int x = getRnd(max2);
			int y = getRnd(max2);

			if ((x - max) * (x - max) + (y - max) * (y - max) <= maxmax) {
				g.drawLine(x, y, x, y);
				count++;
			}
		}
		double pi = (double) count * 4 / anz;
		g.setColor(Color.WHITE);
		g.drawString("Pi = " + pi + " Anzahl der Punkte = " + anz, 10, 20);
	}

	private int getRnd(int max) {
		return (int)(Math.random() * max);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String erg = JOptionPane.showInputDialog(this, "Anzahl der Punkte: ",
				"" + anz);
		anz = Integer.parseInt(erg);
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
}
