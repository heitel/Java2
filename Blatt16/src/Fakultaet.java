import java.math.BigDecimal;
import java.math.BigInteger;


public class Fakultaet {
	private static long fakultaet(int n) {
		long erg = 1;
		for (int i = 1; i <= n; i++) {
			erg *= i;
		}
		return erg;
	}
	
	private static long fakultaetR(int n) {
		if (n <= 1) return 1;
		return n * fakultaetR(n-1);
	}
	
	private static BigInteger pot(int x, int y) {
		if (y<=1) return BigInteger.ONE;
		return pot(x, y-1).multiply(new BigInteger(""+x));
	}
	private static BigDecimal fak(int n) {
		BigDecimal erg = BigDecimal.ONE;
		for (int i = 1; i <= n; i++) {
			erg = erg.multiply(new BigDecimal(i));
		}
		return erg;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		for (int i = 0; i < 100; i++) {
//			System.out.println(i + "   | " + fak(i) );//+ "   | " + fakultaetR(i));
//		}

		for (int i = 0; i < 128; i++) {
			System.out.println(i + " | " + pot(2, i));
		}
	}

}
