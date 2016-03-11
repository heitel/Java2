package teilSumme;

public class TeilSummeA {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double a[] = { 5, -11, 2, 4, -1}; 
		//double a[] = { 0, 5, -11, 2, 3, -1, 4, -2, 2, 23, -65, 1, -6, 8, 7, -13 };
		
		Max max = maxSub(a);
		System.out.println(max);
	}
	
	private static Max maxSub(double[] a) {
		int anfang = 0;
		int ende = 0;
		double max = 0;
		
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {
				double sum = summe(a, i, j);
				System.out.print("("+i+","+j+")"+sum+"\t");
				if (sum > max) {
					max = sum;
					anfang = i;
					ende = j;
				}
			}
			System.out.println();
		}
		return new Max(anfang, ende, max);
	}
	
	private static double summe(double[] a, int i, int j) {
		double erg = 0;
		for (int k = i; k <= j; k++) {
			erg += a[k];
		}
		return erg;
	}

}
