package hanoiStart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.SoftBevelBorder;

public class HanoiPanel extends JPanel implements ComponentListener {
	private final static Color COLOR[] = { new Color(255, 51, 51),
			new Color(102, 102, 255), new Color(51, 204, 0),
			new Color(255, 255, 51), new Color(153, 153, 153),
			new Color(102, 255, 255), new Color(204, 0, 204),
			new Color(204, 204, 204), new Color(255, 204, 51),
			new Color(255, 102, 255) };

	private final static Color DRAG_COLOR = new Color(153, 153, 255, 128);
	private final static BasicStroke stroke = new BasicStroke(2.0f,
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);

	private int dragID = -1;
	private int anz = 4;
	@SuppressWarnings("unchecked")
	private Stack<Scheibe> stab[] = new Stack[3];

	public HanoiPanel(int anz) {
		addComponentListener(this);
		setLayout(null);
		for (int i = 0; i < stab.length; i++) {
			stab[i] = new Stack<Scheibe>();
		}
		setAnz(anz);
		adjustSize();
	}

	public void setAnz(int anz) {
		this.anz = anz;

		for (int i = 0; i < stab.length; i++) {
			stab[i].removeAllElements();
		}

		removeAll();
		for (int i = 0; i < anz; i++) {
			Scheibe s = new Scheibe(this, anz - i);
			s.setBackground(COLOR[i%COLOR.length]);
			s.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
			add(s);
			stab[0].push(s);
		}
	}

	@Override
	public void componentResized(ComponentEvent e) {
		adjustSize();
	}

	private void adjustSize() {
		int width = getWidth();
		int height = getHeight();

		for (int i = 0; i < stab.length; i++) {
			for (int j = 0; j < stab[i].size(); j++) {
				Scheibe s = stab[i].get(j);
				int w = (width * s.getNr()) / (3 * anz);
				int h = height / anz;
				int x = (2 * i + 1) * width / 6 - w / 2;
				int y = height - (j + 1) * h;
				s.setBounds(x, y, w, h);
			}
		}
	}

	@Override
	public void componentMoved(ComponentEvent e) {
	}

	@Override
	public void componentShown(ComponentEvent e) {
	}

	@Override
	public void componentHidden(ComponentEvent e) {
	}

	public boolean canDrag(Scheibe s) {
		for (int i = 0; i < stab.length; i++) {
			if (stab[i].size() > 0 && stab[i].lastElement() == s) {
				return true;
			}
		}
		return false;
	}

	public void doDrop(Scheibe s, Point p) {
		int topNr = 10000;
		int targetIdx = p.x / (getWidth() / 3);
		for (int i = 0; i < stab.length; i++) {
			if (stab[i].size() > 0 && stab[i].lastElement() == s) {
				if (stab[targetIdx].size() > 0) {
					Scheibe top = stab[targetIdx].lastElement();
					topNr = top.getNr();
				}
				if (topNr > s.getNr()) {
					stab[i].pop();
					stab[targetIdx].push(s);
				}
			}
		}
		dragID = -1;
		adjustSize();
		repaint();
	}

	public void doNewGame() {
		String in = JOptionPane.showInputDialog(this, "Anzahl der Scheiben",
				"" + 5);
		setAnz(Integer.parseInt(in));
		adjustSize();
		repaint();
	}

	public void doAnimation() {
		// Definierten Anfangszustand herstellen
		setAnz(anz);
		adjustSize();
		
	}

	private void moveDisk(int from, int to) {
		stab[to].push(stab[from].pop());
		adjustSize();
		repaint();
	}

	public void visualizeDrag(Point p) {
		int w3 = (getWidth() / 3);
		dragID = p.x / w3;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (dragID >= 0) {
			int w3 = (getWidth() / 3);
			g.setColor(DRAG_COLOR);
			((Graphics2D) g).setStroke(stroke);
			g.drawRect(dragID * w3 + 1, 0 + 1, w3 - 2, getHeight() - 2);
		}
	}
}
