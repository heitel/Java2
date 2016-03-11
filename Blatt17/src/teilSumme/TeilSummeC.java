package teilSumme;
public class TeilSummeC {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 //double a[] = { 5, -11, 2, 4, -1 };
		double a[] = { 0, 5, -11, 2, 3, -1, 4, -2, 2, 23, -65, 1, -6, 8, 7, -13 };
		Max max = maxSub(a, 0, a.length - 1);
		System.out.println(max);

	}

	private static Max maxSub(double[] a, int links, int rechts) {
		double sum = 0;
		double max = 0;
		int ende = 0;
		int anfang = 0;
		
		// max und ende
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			if (sum < 0) {
				sum = 0;
			}
			if (sum>max) {
				max = sum;
				ende = i;
			}
		}
		
		// anfang finden
		sum = 0;
		for (int i = ende; i >= 0; i--) {
			sum += a[i];
			if (sum >= max) {
				anfang = i;
				break;
			}
		}
		
		return new Max(anfang, ende, max);
	}
}
