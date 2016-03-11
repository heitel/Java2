
public class Convert2Dez {

	public static void main(String[] args) {
		String dual = "400";

		int zahl = convert2Dez(dual, 16);
		System.out.println(zahl);
	}

	private static int convert2Dez(String s, int radix) {
		int zahl = 0;

		for (int i = 0; i < s.length(); i++) {
			zahl *= radix;
			char c = s.charAt(i);
			int ziffer = convertChar2Ziffer(c);
			zahl += ziffer;
		}
		return zahl;
	}

	private static int convertChar2Ziffer(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		c = Character.toLowerCase(c);
		if (c>='a' && c<='z') {
			return c-'a'+10;
		}
		return 0;
	}

}
