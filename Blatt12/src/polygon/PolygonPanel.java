package polygon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JPanel;

public class PolygonPanel extends JPanel {
	private double alpha = 0;
	private int ecken = 0;

	public PolygonPanel() {
		setBackground(Color.BLACK);
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public void setEcken(int ecken) {
		this.ecken = ecken;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int cx = getWidth() / 2;
		int cy = getHeight() / 2;
		int size = Math.min(cx, cy);

		double phi = Math.toRadians(360.0 / ecken);
		int x = (int) (cx + Math.cos(alpha) * size);
		int y = (int) (cy + Math.sin(alpha) * size);

		// Kreis für Startpunkt zeichnen
		g.setColor(Color.RED);
		g.fillOval(x - 10, y - 10, 20, 20);

		// Umrandung zeichnen
		g.setColor(Color.WHITE);
		Polygon poly = new Polygon();
		for (int i = 0; i < ecken; i++) {
			x = (int) (cx + Math.cos(alpha + i * phi) * size);
			y = (int) (cy + Math.sin(alpha + i * phi) * size);
			poly.addPoint(x, y);
		}

		g.drawPolygon(poly);
		
		// Diagonalen zeichnen
		g.setColor(Color.RED);
		for (int i = 0; i < ecken - 2; i++) {
			for (int j = i + 2; j < ecken - 1 + i; j++) {
				g.drawLine(poly.xpoints[i], poly.ypoints[i], poly.xpoints[j
						% ecken], poly.ypoints[j % ecken]);
			}
		}

		double flaeche = berechneFlaeche(poly);
		g.drawString("Fläche: " + flaeche, 10, 10);
		
		double umfang = berechneUmfang(poly);
		g.drawString("Umfang: " + umfang, 200, 10);
	}
	
	// Abstand von Punkt zu Punkt aufsummieren
	private double berechneUmfang(Polygon poly) {
		double umfang = 0;
		for (int i = 0; i < ecken; i++) {
			umfang += Math.sqrt((poly.xpoints[i] - poly.xpoints[(i + 1) % ecken])*(poly.xpoints[i] - poly.xpoints[(i + 1) % ecken])+
					(poly.ypoints[i] - poly.ypoints[(i + 1) % ecken])*(poly.ypoints[i] - poly.ypoints[(i + 1) % ecken]));
		}
		return umfang;
	}

	// Gauss Trapezformel
	private double berechneFlaeche(Polygon poly) {
		double flaeche = 0;
		for (int i = 0; i < ecken; i++) {
			flaeche += (poly.ypoints[i] + poly.ypoints[(i + 1) % ecken])
							* (poly.xpoints[i] - poly.xpoints[(i + 1) % ecken]);
		}
		return Math.abs(flaeche/2);
	}
}
