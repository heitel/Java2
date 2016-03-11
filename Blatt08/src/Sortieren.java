public class Sortieren {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();
		// Daten aus Datei einlesen
		String line[] = FileInput.readTextFile("addr.txt");
		String data[][] = new String[line.length][];

		// Auftrennen der Daten in Bestandteile: Trennzeichen Tab=\t
		for (int i = 0; i < line.length; i++) {
			data[i] = line[i].split("\t");
		}

		do {
			// Benutzermenü anzeigen
			System.out.println("Wie soll sortiert werden?");
			System.out.println("0 - Name");
			System.out.println("1 - Vorname");
			System.out.println("X - Ende");
			char input = con.readChar("");
			if (input == 'X' || input == 'x') {
				break; // Programmende
			}
			sort(data, input);
			show(data);
		} while (true);
	}

	private static void sort(String[][] data, char input) {
		int sp = input - '0'; // ASCII -> Spaltennummer
		boolean tausch;
		int max = data.length - 1;

		do {
			tausch = false;
			for (int i = 0; i < max; i++) {
				if (data[i][sp].compareTo(data[i + 1][sp]) > 0) {
					String tmp[] = data[i];// tausche zeilenweise
					data[i] = data[i + 1];
					data[i + 1] = tmp;
					tausch = true;
				}
			}
			max--;
		} while (tausch);

	}

	private static void show(String[][] data) {
		System.out.println("| Name            | Vorname         |");
		System.out.println("+-----------------+-----------------+");

		for (int i = 0; i < data.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(Console.padStringRight(data[i][j], 16));
				System.out.print("| ");
			}
			System.out.println();
		}
	}
}
