public class Matrix5x7Test {

	public static void main(String[] args) {
		String txt = " !\"#$%&'()*+,-./";
		println(txt);

		txt = "0123456789:;<=>?";
		println(txt);

		txt = "ABCDEFGHIJKLMNO";
		println(txt);

		txt = "ABCDEFGHIJKLMNO";
		println(txt, true, true);
		txt = new StringBuffer("ABCDEFGHIJKLMNO").reverse().toString();
		println(txt, false, false);
		
		txt = "PQRSTUVWXYZ[¥]^_";
		println(txt);

		txt = "`abcdefghijklmno";
		println(txt);

		txt = "pqrstuvwxyz";
		println(txt);

		txt = "Joachim Heitel";
		println(txt);

		txt = new StringBuffer("Joachim Heitel").reverse().toString();
		println(txt);

		txt = new StringBuffer("Joachim Heitel").reverse().toString();
		println(txt, false, false);

	}

	private static void println(String txt) {
		println(txt, false, true);
	}
	
	private static void println(String txt, boolean buchstabe, boolean normal) {
		byte[][] m = Matrix5x7ROM.M5x7;
		int len = m[0].length;
		
		for (int j = 0; j < len; j++) {
			for (int i = 0; i < txt.length(); i++) {
				char c1 = txt.charAt(i);
				char output = '*';
				if (buchstabe) {
					output = c1;
				}
				
				if (c1 == '¥') { // Sonderbehandlung
					c1 = 60 + ' ';
				}
				int c = c1 - ' ';

				if (c >= 0 && c < m.length) {
					byte b;
					if (normal) {
						b = m[c][j];
					}else {
						b = reverse(m[c][j]);
					}
					
					for (int k = 0; k < 5; k++) {
						if (b % 2 == 1) {
							System.out.print(output);
						} else {
							System.out.print(' ');
						}
						b >>= 1;
					}
					System.out.print("  ");
				} else {
					System.out.print("       "); // nicht druckbar
				}
			}
			System.out.println();
		}

		System.out.println();
	}

	private static byte reverse(byte b) {
		byte z = 0;
		for (int i = 0; i < 5; i++) {
			z *= 2;
			int bit = b % 2;
			b /= 2;
			z += bit;
		}
		return z;
	}
}
