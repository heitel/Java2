public class Zweierpotenzen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String line = " --+-----+----------------------------";
		final String text[] = { "Kilobyte", "Megabyte", "Gigabyte" };
		System.out.println("Tabelle für Zweierpotenzen");
		System.out.println("==========================");

		System.out.println("2 hoch i     |   i |                2 hoch -1 ");
		System.out.println("-------------+-----+--------------------------");
		int links = 1;
		double rechts = 1;

		for (int j = 0; j < text.length; j++) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Console.padStringLeft("" + links, 12)
						+ " | "
						+ Console.padStringLeft("" + (j * 10 + i), 3)
						+ " | "
						+ Console.Double2String("0.#########################",
								rechts));
				links *= 2;
				rechts /= 2;
			}
			System.out.println(1 + " " + text[j] + line);
		}
		System.out.println(Console.padStringLeft("" + links, 12) + " | "
				+ Console.padStringLeft("" + 30, 3) + " | "
				+ Console.Double2String("0.#########################", rechts));
	}
}
