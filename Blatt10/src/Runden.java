public class Runden {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double x = Math.PI;//123456.654321;

		x = runden(x, 5);//-3
		System.out.println(x);
	}

	private static double runden(double x, int stellen) {
		double faktor = 1;

		if (stellen > 0) {
			for (int i = 0; i < stellen; i++) {
				faktor *= 10;
			}
		} else {
			stellen = -stellen;
			for (int i = 0; i < stellen; i++) {
				faktor /= 10;
			}
		}

		x = Math.floor(x * faktor + 0.5); // Kaufmännisches Runden
		
		x = x / faktor;
		return x;
	}

}
