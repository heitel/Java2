
public class Multiplikationstabelle {
	public static void main(String[] args) {
		Console con = new Console();
		
		System.out.println("Programm zur Ausgabe einer Multiplikationstabelle");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		int size = con.readInt("Wie groß soll die Tabelle werden?");
		
		for (int i = 1; i <= size; i++) {
			for (int j = 1; j <= size; j++) {
				System.out.print((i*j) + "\t");
			}
			System.out.println();
		}
		
	}

}
