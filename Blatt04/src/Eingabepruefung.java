public class Eingabepruefung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();
		int eingabe;

		// a
		do {
			eingabe = con.readInt("3-stellige Zahl bitte: ");
		} while (!(eingabe > 99 && eingabe < 1000));

		System.out.println("Ihre Zahl: " + eingabe);

		// b
		do {
			eingabe = con.readInt("Größer als 0 und gerade: ");
		} while (eingabe < 0 || eingabe % 2 == 1);
		
		System.out.println("Erfolg: " + eingabe);
	}

}
