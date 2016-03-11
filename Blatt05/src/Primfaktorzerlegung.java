public class Primfaktorzerlegung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();

		System.out
				.println("Dieses Programm findet die Primfaktorzerlegung einer Zahl.");
		System.out
				.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		int zahl = con.readInt("Bitte geben Sie eine ganze Zahl ein: ");

		System.out.print("Die Primfaktoren von " + zahl + " sind: ");
		boolean firstTime = true;
		for (int i = 2; i <= zahl; i++) {
			while (zahl % i == 0) {
				if (firstTime) {
					System.out.print(i);
					firstTime = false;
				} else {
					System.out.print(" * " + i);
				}
				zahl /= i;
			}
		}
	}
}
