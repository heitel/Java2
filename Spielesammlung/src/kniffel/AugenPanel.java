package kniffel;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;

@SuppressWarnings("serial")
public class AugenPanel extends JPanel {
	private final static boolean CODE[][][] = {
			{ { false, false, false }, { false, true, false },
					{ false, false, false } },
			{ { true, false, false }, { false, false, false },
					{ false, false, true } },
			{ { true, false, false }, { false, true, false },
					{ false, false, true } },
			{ { true, false, true }, { false, false, false },
					{ true, false, true } },
			{ { true, false, true }, { false, true, false },
					{ true, false, true } },
			{ { true, false, true }, { true, false, true },
					{ true, false, true } } };

	private int augenzahl = 0;


	public AugenPanel() {
		setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width = getWidth();
		int height = getHeight();
		int a = Math.min(width, height);
		int x;
		int y;

		if (width > height) {
			x = (width - a) / 2;
			y = 0;
		} else {
			x = 0;
			y = (height - a) / 2;
		}
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, a, a);
		
		if (augenzahl >= 0) {
			int small = a / 6;
			int offset = 2;
			g.setColor(Color.BLACK);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (CODE[augenzahl][i][j]) {
						g.fillOval(x + small / 2 + j * 2 * small +offset, y + small / 2
								+ i * 2 * small+offset, small, small);
					}
				}
			}
		}
	}

	public int getAugenzahl() {
		return augenzahl;
	}

	public void setAugenzahl(int anz) {
		this.augenzahl = anz;
	}

}
