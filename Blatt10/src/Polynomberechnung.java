public class Polynomberechnung {
	// Als Klassenvariable/konstante erklären -> vereinfacht den Funktionsaufruf
	private final static double a[] = { 5.0, 4.0, 3.0, 2.0, 1.0 };

	public static void main(String[] args) {
		// Überschrift
		System.out.println("Berechnung einer Wertetabelle der Funktion: ");
		System.out.print("f(x) = ");

		for (int i = 0; i < a.length; i++) {
			int exp = (a.length - i - 1);
			if (exp > 0) {
				System.out.print(a[i] + " * x^" + exp + " + ");
			} else {
				System.out.print(a[i]);
			}
		}

		// Berechnungen
		double y[] = new double[100001];
		int i = 0;
		long start = System.nanoTime();
		for (double x = 0; x < 1000; x += 0.01, i++) {
			y[i] = f(x); // oder f2(x) einsetzen 
		}
		long ende = System.nanoTime();
		
		// Ausgabe
		i = 0;
		for (double x = 0; x < 1000; x += 0.01, i++) {
			System.out.println(x + "\t" + y[i]);
		}
		System.out.println("Dauer: " + (ende-start));
	}

	private static double f(double x) {
		double y = 0.0;

		for (int i = 0; i < a.length; i++) {
			y += a[i] * Math.pow(x, a.length - 1 - i);
		}

		return y;
	}

	// Berechnung mit Hornerschema
	private static double f2(double x) {
		double y = 0;

		for (int i = 0; i < a.length-1; i++) {
			y = (y + a[i]) * x;
		}
		y += a[a.length-1];

		return y;
	}
}
