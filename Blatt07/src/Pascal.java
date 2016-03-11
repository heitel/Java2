public class Pascal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int SIZE = 10;

		long dreiEckA[][] = pascalA(SIZE);
		show(dreiEckA);

		long dreiEckB[][] = pascalB(SIZE);
		show(dreiEckB);

		long dreiEckC[][] = pascalC(SIZE);
		show(dreiEckC);
		
		long dreiEckD[][] = pascalD(SIZE);
		show(dreiEckD);
		
		long dreiEckE[][] = pascalA(50);
		System.out.println("1/(49 über 6) = 1/" + dreiEckE[49][6] + 
				" = " + 1.0/dreiEckE[49][6]);

	}

	private static long[][] pascalD(int size) {
		long[][] p = dreieckAnlegen(size);

		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				p[i][j] = binomD(i, j);
			}
		}

		return p;
	}

	private static long binomD(int n, int k) {
		long zaehler = 1;

		for (long i = n-k+1; i <= n; i++) {
			zaehler *= i;
		}

		long nenner = 1;
		for (int i = 1; i <= k; i++) {
			nenner *= i;
		}
		return zaehler / nenner;
	}

	private static long[][] pascalC(int size) {
		long[][] p = dreieckAnlegen(size);

		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				p[i][j] = binomR(i, j);
			}
		}

		return p;
	}

	// Rekursive Berechnung
	private static long binomR(long n, long k) {
		if (k==0 || k==n) { // Ränder = 1
			return 1;
		}
		long erg = (n * binomR(n-1, k-1))/k;
		return erg;
	}

	private static long[][] pascalB(int size) {
		long[][] p = dreieckAnlegen(size);

		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				p[i][j] = binomB(i, j);
			}
		}

		return p;
	}

	private static long binomB(long n, long k) {
		long zaehler = 1;

		for (long i = 1; i <= n; i++) {
			zaehler *= i;
		}

		long nenner = 1;
		for (int i = 1; i <= k; i++) {
			nenner *= i;
		}
		
		for (int i = 1; i <= n-k; i++) {
			nenner *= i;
		}
		return zaehler / nenner;
	}

	private static void show(long[][] dreiEck) {
		for (int i = 0; i < dreiEck.length; i++) { // zeilen
			System.out.print(i + ". ");
			for (int j = 0; j < dreiEck.length - i; j++) {
				System.out.print("        ");
			}
			for (int j = 0; j < dreiEck[i].length; j++) {
				System.out
						.print(Console.padStringRight("" + dreiEck[i][j], 16));
			}
			System.out.println();
		}
		System.out.println();
	}

	private static long[][] pascalA(int size) {
		long[][] p = dreieckAnlegen(size);
		// Dreieck füllen
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				if (j == 0 || j == p[i].length - 1) { // Ränder = 1
					p[i][j] = 1;
				} else {
					p[i][j] = p[i - 1][j - 1] + p[i - 1][j];
				}
			}
		}
		return p;
	}

	private static long[][] dreieckAnlegen(int size) {
		long p[][] = new long[size][];

		// Speicher anlegen
		for (int i = 0; i < p.length; i++) {
			p[i] = new long[i + 1];
		}
		return p;
	}
}
