public class Morse {
	private static final String CODES[] = { ".-", // A
			"-...", // B
			"-.-.", // C
			"-..", // D
			".", // E
			"..-.", // F
			"--.", // G
			"....", // H
			"..", // I
			".---", // J
			"-.-", // K
			".-..", // L
			"--", // M
			"-.", // N
			"---", // O
			".--.", // P
			"--.-", // Q
			".-.", // R
			"...", // S
			"-", // T
			"..-", // U
			"...-", // V
			".--", // W
			"-..-", // X
			"-.--", // Y
			"--.." // Z
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showAlphabet();

		String txt = "Das ist mein Beispieltext! §$%@ ü+#";
		System.out.println(txt);
		String upper = convert2Upper(txt);
		System.out.println(upper);

		String morse = convert2Morse(upper);
		System.out.println(morse);
		
		String text = convert2Text(morse);
		System.out.println(text);
	}

	private static String convert2Text(String morse) {
		String a[] = morse.split(" ");
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < a.length; i++) {
			boolean found = false;
			for (int j = 0; j < CODES.length; j++) {
				if (a[i].equals(CODES[j])) {
					sb.append((char)(j+'A'));
					found = true;
				} 
			}
			if (!found) {
				sb.append(' ');
			}
		}

		String erg = sb.toString();
		erg = erg.replaceAll("  ", " ");
		return erg;
	}

	private static String convert2Morse(String upper) {
		char a[] = upper.toCharArray();
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < a.length; i++) {
			if (a[i] == ' ') {
				sb.append("  ");
			}else{
				sb.append(CODES[a[i]-'A']);
				sb.append(' ');
			}
			
		}
		return sb.toString();
	}

	private static String convert2Upper(String txt) {
		char a[] = txt.toCharArray();
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < a.length; i++) {
			if ((a[i] >= 'A' && a[i] <= 'Z') || a[i] == ' ') {
				sb.append((char)a[i]);
			}
			if (a[i] >= 'a' && a[i] <= 'z') {
				sb.append((char)(a[i] - ('a' - 'A')));
			}
			if (a[i] == 'Ä' || a[i] == 'ä') {
				sb.append("AE");
			}
			if (a[i] == 'Ö' || a[i] == 'ö') {
				sb.append("OE");
			}
			if (a[i] == 'Ü' || a[i] == 'ü') {
				sb.append("UE");
			}
			if (a[i] == 'ß') {
				sb.append("SS");
			}
		}
		return sb.toString();
	}

	private static void showAlphabet() {
		System.out.println("Morsealphabet");
		System.out.println("=============");
		for (int i = 0; i < CODES.length; i++) {
			System.out.println((char) (i + 'A') + " | " + CODES[i]);
		}

	}

}
