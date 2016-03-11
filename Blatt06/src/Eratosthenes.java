
public class Eratosthenes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int SIZE = 1000000;
		boolean a[] = new boolean[SIZE];
		
		for (int i = 2; i < Math.sqrt(a.length); i++) {
			if (a[i]) {
				continue;
			}
			for (int j = i*i; j < a.length; j+=i) {
				a[j] = true; // durchstreichen
			}		 
		}
		
		//zeigeAn(a);
		findeZwillinge(a);
		
	}

	private static void findeZwillinge(boolean[] a) {
		int anz = 0;
		for (int i = 2; i < a.length; i++) {
			if (!a[i] && !a[i+2]) {
				anz++;
				System.out.println(i + " / " + (i+2));
			}
		}
		System.out.println("Anzahl der Zwillinge: " + anz);
	}

	private static void zeigeAn(boolean[] a) {
		int anz = 0;
		for (int i = 2; i < a.length; i++) {
			if (!a[i]) {
				anz++;
				System.out.println(i);
			}
		}
		System.out.println("Anzahl: " + anz);
	}

}
