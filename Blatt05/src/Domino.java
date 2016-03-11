
public class Domino {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Dominosteine");
		System.out.println("============");
		for (int i = 0; i <= 6; i++) {
			zeigeLinie(6-i);
			zeigeNummern(i);
			zeigeLinie(6-i);
		}
		

	}

	private static void zeigeNummern(int start) {
		for (int i = start; i <= 6; i++) {
			System.out.print("| " + start + " | " + i + " | ");
		}
		System.out.println();
	}

	private static void zeigeLinie(int anz) {
		for (int i = 0; i <= anz; i++) {
			System.out.print("+---+---+ ");
		}
		System.out.println();
	}

}
