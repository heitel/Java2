
public class Dual2Dez {

	public static void main(String[] args) {
		String dual = "0001001";

		int zahl = dual2Dez(dual);
		System.out.println(zahl);
	}

	public static int dual2Dez(String s) {
		int zahl = 0;

		for (int i = 0; i < s.length(); i++) {
			zahl *= 2;
			zahl += s.charAt(i) - '0';
		}
		return zahl;
	}
}
