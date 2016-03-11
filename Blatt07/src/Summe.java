
public class Summe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 50;
		int e = 100;
		
		int sum = summe1(a, e);
		System.out.println(sum);

		sum = summe2(a, e);
		System.out.println(sum);
	}

	private static int summe2(int a, int e) {
		int erg = (e*(e+1))/2 - (a*(a-1)/2); 
		return erg;
	}

	private static int summe1(int a, int e) {
		int erg = 0;
		
		for (int i = a; i <= e; i++) {
			erg += i;
		}
		
		return erg;
	}

}
