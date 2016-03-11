import java.util.ArrayList;

public class AchileaPtarmica {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Long> zahlen = calc();
		int n = 1;
		System.out.println("n | A(n)");
		System.out.println("--+-----");
		
		for (Long z : zahlen) {
			System.out.println(n + " | " + z);
			n++;
		}

		System.out.println("==========================================\n");
		System.out.println("Rekursive Lösung");
		System.out.println("n | A(n)");
		System.out.println("--+-----");
		for (int i = 1; i < 50; i++) {
			long start = System.nanoTime();
			System.out.print(i + " | " + fib(i));
			long dauer = System.nanoTime() - start;
			System.out.println(" | " + dauer/1e9);
		}
	}

	private static ArrayList<Long> calc() {
		ArrayList<Long> liste = new ArrayList<Long>(128);
		long a1 = 1;
		long a2 = 1;
		long a3 = 0;
		
		liste.add(a1);
		liste.add(a2);
		
		for (int i = 0; i < liste.size() && a3 < Long.MAX_VALUE / 2; i++) {
			a3 = a1 + a2;
			a1 = a2;
			a2 = a3;
			liste.add(a3);
		}
		
		return liste;
	}

	private static long fib(long n) {
		
		if (n==0) {
			return 0;
		}
		if (n==1) {
			return 1;
		}
		
		return fib(n-1) + fib(n-2);
	}
}
