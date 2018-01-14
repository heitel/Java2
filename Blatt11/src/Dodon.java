public class Dodon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int anz = 10;// 10;
		boolean tor[] = new boolean[anz];

		intro();
		System.out.println("Anzahl der Türen: " + anz);

		for (int i = 1; i <= tor.length; i++) {
			printBlack(i+".\t| ");
			durchgang(tor, i);
			show(tor);
		}
		zeigeOffene(tor);
	}

	private static void zeigeOffene(boolean[] tor) {
		System.out.println("Die offenen Türen sind:");
		int count = 0;
		for (int i = 0; i < tor.length; i++) {
			if (tor[i]) {
				System.out.print((i + 1) + " ");
				count++;
			}
		}
		System.out.println("\nInsgesamt " + count + " offene Zelltüren.");
	}

	private static void durchgang(boolean[] t, int sw) {
		for (int i = sw - 1; i < t.length; i += sw) {
			t[i] = !t[i];
		}
	}

	private static void intro() {
		System.out.println("Amnestie bei Dodon, dem Märchenkönig");
		printBlack("Offene Tore: ");
		printRed("rot\n");
		printBlack("Geschlossene Tore: schwarz\n");
		System.out.println();
	}

	private static void show(boolean[] t) {
		for (int i = 0; i < t.length; i++) {
			if (t[i]) {
				printRed((i + 1) + " ");
			} else {
				printBlack((i + 1) + " ");
			}
		}
		System.out.println();
	}

	private static void printRed(String txt) {
		try {
			System.err.print(txt);
			System.err.flush();
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void printBlack(String txt) {
		try {
			System.out.print(txt);
			System.out.flush();
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
