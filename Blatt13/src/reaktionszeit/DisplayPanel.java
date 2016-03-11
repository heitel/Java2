package reaktionszeit;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DisplayPanel extends JPanel implements MouseListener {
	private ReaktionszeitWin win;
	private BufferedImage image[] = new BufferedImage[2];
	private String fileName[] = { "wald.jpg", "pilz.jpg" };
	private int currentImageNr = -1;

	public DisplayPanel(ReaktionszeitWin win) {
		this.win = win;

		for (int i = 0; i < fileName.length; i++) {
			try {
				image[i] = ImageIO.read(new File(fileName[i]));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (currentImageNr >= 0) {
			drawCenteredImage(g, image[currentImageNr]);
		}
	}

	public void setImState(int state) {
		currentImageNr = state;
		repaint();
	}

	private void drawCenteredImage(Graphics g, BufferedImage img) {
		int width = getWidth();
		int height = getHeight();
		int imWidth = img.getWidth();
		int imHeight = img.getHeight();
		double scaleX = (double) width / imWidth;
		double scaleY = (double) height / imHeight;
		double scale = Math.min(scaleX, scaleY);

		imWidth *= scale;
		imHeight *= scale;
		g.drawImage(img, (width - imWidth) / 2,
				(height - imHeight) / 2, imWidth, imHeight, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int state = win.getCurrentState();
		switch (state) {
		case ReaktionszeitWin.WAIT_STATE:
			win.setCurrentState(ReaktionszeitWin.FINAL_STATE);
			break;

		case ReaktionszeitWin.HOT_STATE:
			win.setCurrentState(ReaktionszeitWin.ERROR_STATE);
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}
