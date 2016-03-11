public class Kreis {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out
				.println("Programm zur Berechnung des Schnittpunktes zweier Kreise in der Ebene");

		double x1 = 3.0;
		double y1 = 5.0;
		double r1 = 5.0;

		double x2 = -1.0;
		double y2 = 1.0;
		double r2 = 1.0;

		// Berechne Verbindungslinie der Mittelpunkte
		double dx = (x1 - x2);
		double dy = (y1 - y2);

		// Betrag davon
		double d = Math.sqrt(dx * dx + dy * dy);
		System.out.println("d = " + d);
		// Pythagoras linkes und rechtes Dreieck
		double a = (r2 * r2 - r1 * r1 + d * d) / (2 * d);
		System.out.println("a = " + a);
		// Höhe des Dreiecks
		double radikant = r2 * r2 - a * a;
		if (radikant >= 0) {
			double h = Math.sqrt(radikant);
			System.out.println("h = " + h);
			// Fußpunkt
			double fx = x2 + a * dx / d;
			double fy = y2 + a * dy / d;

			double s1x = fx + h / d * dy;
			double s1y = fy - h / d * dx;
			double s2x = fx - h / d * dy;
			double s2y = fy + h / d * dx;

			System.out.println("S1 (" + s1x + "|" + s1y + ")");
			System.out.println("S2 (" + s2x + "|" + s2y + ")");
		} else {
			System.out.println("Die Kreise schneiden sich nicht!");
		}

	}
}
