
public class ASCIITab {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char clut[] = {'0', '1', '2', '3', '4', '5', '6', '7', 
				'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 

		System.out.println("ASCII Tabelle");
		System.out.println("=============");
		System.out.println("American Standard Code for Imformation Interchange\n");
		
		// Kopfzeile der Tabelle
		System.out.print("HEX |");
		for (int i = 0; i < 16; i++) {
			System.out.print("  " + clut[i] + "|");
		}
		System.out.println();
		
		// Trennlinie
		System.out.print("----+");
		for (int i = 0; i < 16; i++) {
			System.out.print("---+");
		}
		System.out.println();
		
		for (int i = 2; i < 16; i++) { // Zeilen
			System.out.print("   " + clut[i] + "|");
			for (int j = 0; j < 16; j++) { // Spalten
				char c = (char)(i*16+j);
				System.out.print("  " + c +"|");
			}
			System.out.println();
		}
		
	}

}
