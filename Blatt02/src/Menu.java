public class Menu {

	public static void main(String[] args) {
		while (true) {
			char auswahl = showMenu();

			clearScreen();
			switch (auswahl) {
			case 'A':
				System.out.println("Sie haben 'Ändern' gewählt.");
				break;
			case 'B':
				System.exit(0);
				break;
			case 'E':
				System.out.println("Sie haben 'Eingeben' gewählt.");
				break;
			case 'L':
				System.out.println("Sie haben 'Löschen' gewählt.");
				break;
			default:
				System.out.println("Unbekannter Befehl");
				break;
			}
			System.out.println();
		}
	}

	private static void clearScreen() {
		for (int i = 0; i < 24; i++) {
			System.out.println();
		}
	}

	private static char showMenu() {
		Console con = new Console();

		System.out.println("        Hauptmenü");
		System.out.println("        =========");

		System.out.println();
		System.out.println("(A) Ändern");
		System.out.println("(B) Beenden");
		System.out.println("(E) Eingeben");
		System.out.println("(L) Löschen");
		System.out.println();

		char in = con.readChar("Was wünschen Sie zu tun?");
		return in;
	}

}
