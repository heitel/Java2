package polygon;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PolygonWin extends JFrame implements ChangeListener {
	private static final String TITLE = "Polygon Fenster: ";
	private JSlider anzEcken = new JSlider(JSlider.VERTICAL, 3, 100, 4);
	private JSlider rotate = new JSlider(JSlider.VERTICAL, 0, 360, 0);
	private PolygonPanel polyPanel = new PolygonPanel();

	public PolygonWin(String title) {
		super(title);

		// Verwende BorderLayout zum positionieren
		add(anzEcken, BorderLayout.WEST);
		// EventHandler für Slider ist ChangeListener
		anzEcken.addChangeListener(this);
		setTitle(TITLE + anzEcken.getValue());

		add(rotate, BorderLayout.EAST);
		rotate.addChangeListener(this);
		
		add(polyPanel, BorderLayout.CENTER);
		polyPanel.setEcken(3);
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		Object src = e.getSource();

		if (src == anzEcken) {
			int ecken = anzEcken.getValue();
			setTitle(TITLE + ecken);
			polyPanel.setEcken(ecken);
		}
		
		if (src == rotate) {
			polyPanel.setAlpha(Math.toRadians(rotate.getValue()));
		}
		repaint();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PolygonWin win = new PolygonWin(TITLE);

		// Fenster am Bildschirm positionieren
		win.setBounds(0, 0, 400, 400);

		// Fenster sichtbar machen
		win.setVisible(true);

		// beendet die JRE, wenn Fenster geschlossen wird
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
