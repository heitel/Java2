public class Sternenfels {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text1[] = { "01000101", "01011000", "01010000", "01001111" };
		String text2[] = { "00110010", "00110000", "00110000", "00110000" };
		String text3[] = { "01010011", "01010100", "01000101", "01010010",
				"01001110", "01000101", "01001110", "01000110", "01000101",
				"01001100", "01010011"};

		anzeige(text1);
		System.out.println();
		anzeige(text2);
		System.out.println();
		anzeige(text3);
	}

	private static int entziffern(String text) {
			char array[] = text.toCharArray();
			int wert = 0;
			for (int j = 0; j < array.length; j++) {
				wert *= 2;
				wert += array[j] - '0';
			}
			return wert;
	}

	private static void anzeige(String[] text) {
		for (int i = 0; i < text.length; i++) {
			int code = entziffern(text[i]);
			char zeichen = (char)code;
			System.out.println(text[i] + " " + code + " " + zeichen);
		}
	}

}
