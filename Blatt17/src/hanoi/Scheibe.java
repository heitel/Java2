package hanoi;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Scheibe extends JPanel implements MouseListener,
		MouseMotionListener {
	private int nr;
	private Point startPt;
	private HanoiPanel parent;

	public Scheibe(HanoiPanel parent, int nr) {
		this.parent = parent;
		this.nr = nr;
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public int getNr() {
		return nr;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(""+nr, 5, 15);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (parent.canDrag(this)) {
			Point p = e.getPoint();

			int dx = p.x - startPt.x;
			int dy = p.y - startPt.y;
			Rectangle r = getBounds();
			r.translate(dx, dy);
			setBounds(r);
			
			p = SwingUtilities.convertPoint(this, p, parent);
			parent.visualizeDrag(p);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startPt = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		startPt = null;
		Point tmp = e.getPoint();
		tmp = SwingUtilities.convertPoint(this, tmp, parent);
		parent.doDrop(this, tmp);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
