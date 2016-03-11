package puzzle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

@SuppressWarnings("serial")
public class PuzzlePart extends JPanel implements MouseListener {
	private int nr;
	private BufferedImage img;

	public PuzzlePart(int nr, BufferedImage img) {
		this.nr = nr;
		this.img = img;
		setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.BLACK);
		g.drawString("" + nr, 12, 15);
		g.setColor(Color.WHITE);
		g.drawString("" + nr, 11, 14);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	public int getNr() {
		return nr;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		PuzzlePanel pp = (PuzzlePanel)getParent();
		pp.movePart(nr);
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
