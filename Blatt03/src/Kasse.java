public class Kasse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double eingabe;
		double summe = 0.0;
		double min = 1e308;
		double max = 1e-308;
		Console con = new Console();

		System.out.println("Supermarktkasse geben Sie Beträge ein! Ende mit 0");

		do {
			eingabe = con.readDouble("Betrag: ");
			summe += eingabe;
			if (eingabe != 0.0) {
				if (eingabe > max) {
					max = eingabe;
				}
				if (eingabe < min) {
					min = eingabe;
				}
			}
		} while (eingabe != 0.0);

		System.out.println("Summe: " + summe);
		System.out.println("Der kleinste Betrag:" + min);
		System.out.println("Der größte Betrag:" + max);
	}

}
