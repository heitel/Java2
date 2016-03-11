
public class Autonummer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 4 stellige Zahl 1000 <= i < 10000
		for (int i = 1000; i < 10000; i++) {
			int z1 = (i / 1000) % 10;
			int z2 = (i / 100) % 10;
			int z3 = (i / 10) % 10;
			int z4 = i % 10;
			if (z1 == z2 && z3 == z4) { // Zeuge 1 und Zeuge 2
				int wurzel = (int)Math.sqrt(i);
				if (wurzel * wurzel == i) { // Zeuge 3
					System.out.println("Gesuchte Autonummer: " + i);
				}
			}
		}
		
		
	}

}
