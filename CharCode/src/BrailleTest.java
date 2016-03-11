
public class BrailleTest {

	private static final char DOT = '•';// '\u2022' '\u25CF';
	private static final char EMPTY = '·'; // '\u00b7' '\u25cb';

	public static void main(String[] args) {
		String txt = "abcdefg";

		printBraille(txt);
		printNormal(txt);
		System.out.println("\n");

		txt = "hijklmn";
		printBraille(txt);
		printNormal(txt);
		System.out.println("\n");

		txt = "opqrstu";
		printBraille(txt);
		printNormal(txt);
		System.out.println("\n");

		txt = "vwxyz";
		printBraille(txt);
		printNormal(txt);
		System.out.println("\n");

		txt = "dhbw mannheim";
		printBraille(txt);
		printNormal(txt);
		System.out.println("\n");

	}

	private static void printNormal(String txt) {
		for (int i = 0; i < txt.length(); i++) {
			System.out.print(txt.charAt(i) + "   ");
		}
	}

	private static void printBraille(String txt) {
		byte[] b = BrailleROM.B;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < txt.length(); j++) {
				int c = txt.charAt(j) - 'a';
				if (c >= 0 && c <= 26) {
					for (int k = 0; k < 2; k++) {
						int bit = getBit(b[c], 2 * i + k);
						if (bit == 1) {
							System.out.print(DOT);
						} else {
							System.out.print(EMPTY);
						}
					}
					System.out.print("  ");
				} else {
					System.out.print("    "); // Nicht druckbar
				}
			}
			System.out.println();
		}
	}

	private static int getBit(int z, int nr) {
		return z >> nr & 0x01;
	}
}
