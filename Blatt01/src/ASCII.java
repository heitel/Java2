
public class ASCII {

	public static void main(String[] args) {
		System.out.println("American Standard Code for Information Interchange (ASCII) 8Bit");
		System.out.println("===============================================================\n");

		System.out.println("Unicode 16Bit");
		System.out.println("===============================================================\n");

		System.out.println("Dez | Dual             | Hex   | Zeichen");
		System.out.println("----+------------------+-------+--------");

		for (int i = 0; i < 0x10000; i++) {
			String dual = dez2Dual16Bit(i);
			String hex = dez2Hex16Bit(i);
			char c = (char) i;
			System.out.println(Console.padStringLeft("" + i, 3) + " | " + dual + " |  " + hex + " | " + c);
		}

	}

	private static String dez2Hex16Bit(int z) {
		String s = "";
		char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		for (int i = 0; i < 4; i++) {
			s = hex[(z % 16)] + s;
			z >>= 4;
		}

		return s;
	}

	private static String dez2Dual16Bit(int z) {
		String s = "";

		for (int i = 0; i < 16; i++) {
			s = (z % 2) + s;
			z >>= 1;
		}
		return s;
	}
}
