public class Brunnen {
	/**
	 * Gravitationskonstante [m/s²]
	 */
	private static final double G = 9.81;
	/**
	 * Schallgeschwindigkeit in Luft [m/s]
	 */
	private static final double V = 343;

	/**
	 * Berechnung der Tiefe eines Brunnens. <br>
	 * 1. Freier Fall im Brunnenschacht. tf Flugzeit.
	 * <p>
	 * 
	 * s(t) = 1/2 * g * tf² <br>
	 * 
	 * 2. Gleichförmige Bewegung des Schalls von unten nach oben. ts Schallzeit.
	 * <p>
	 * 
	 * s(t) = V * ts <br>
	 * 
	 * Es gilt tg Gesamtzeit
	 * <p>
	 * 
	 * tg = tf + ts <br>
	 * 
	 * @param tg
	 *            Die Zeit vom Abwerfen bis der Aufklatsch gehört wird
	 * @return die Tiefe des Brunnen in [m]
	 */
	private static double berechneBrunnen(double tg) {
		double a = 0.5 * G;
		double b = V;
		double c = -V * tg;
		double d = Math.sqrt(b * b - 4 * a * c);
		double tf = (-b + d) / (2 * a);
		double ts = tg - tf;
		double s = V * ts;
		return s;
	}

	/**
	 * Zweispaltige Ausgabe von d und tiefe (2 Nachkommastellen) <br>
	 * @param d Gesamtzeit
	 * @param tiefe Tiefe des Brunnens
	 */
	private static void ausgabe(double d, double tiefe) {
		System.out.println(Console.padStringLeft(
				Console.Double2String("0.00", d), 14)
				+ " | "
				+ Console.padStringLeft(Console.Double2String("0.00", tiefe),
						9));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Berechnung der Tiefe eines Brunnens:\n");
		System.out.println("Gesamtzeit [s] | Tiefe [m]");
		System.out.println("---------------+----------");

		for (double d = 1; d <= 10; d += 0.1) {
			double tiefe = berechneBrunnen(d);
			ausgabe(d, tiefe);
		}

	}

}
