public class Geraden {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();

		System.out
				.println("Berechnung des Schnittpunktes zweier Geraden in der Ebene");
		System.out
				.println("---------------------------------------------------------");
		System.out.println("Gerade 1:");
		double m1 = con.readDouble("Bitte geben Sie die Steigung ein m1 = ");
		double b1 = con.readDouble("Bitte geben Sie den y-Abschnitt ein b1 = ");

		System.out
				.println("---------------------------------------------------------");
		System.out.println("Gerade 2:");
		double m2 = con.readDouble("Bitte geben Sie die Steigung ein m2 = ");
		double b2 = con.readDouble("Bitte geben Sie den y-Abschnitt ein b2 = ");

		if (m1 != m2) {
			// Berechnung x-Koordinate des Schnittpunktes
			double x = (b2 - b1) / (m1 - m2);

			// Berechnung y-Koordinate des Schnittpunktes
			double y = m1 * x + b1;
			System.out.println("Der Schnittpunkt ist (" +x+"|"+y+")");
		} else {
			System.out.println("Die Geraden sind parallel.");
		}
	}
}
