public class Gitterpunkte {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Gitterpunkte");
		System.out.println();

		System.out.println("n Geradenpaare | Anzahl der Schnittpunkte");
		System.out.println("---------------+-------------------------");
		for (int i = 1; i < 50; i++) {
			System.out.println(i + "             | " + A(i));
		}

	}

	public static int A(int n) {
		if (n == 1) {
			return 1;
		}
		return A(n - 1) + 2 * (n - 1) + 1;
	}

}
