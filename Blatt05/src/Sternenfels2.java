public class Sternenfels2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "DHBW-MANNHEIM";

		codieren(text);
	}

	private static void codieren(String text) {
		char array[] = text.toCharArray();
		for (int j = 0; j < array.length; j++) {
			int code = array[j];
			System.out.println(array[j] + " " + code + " " + toDual(code, 8));
		}
	}

	private static String toDual(int code, int stellen) {
		String erg = "";
		
		for (int i = 0; i < stellen; i++) {
			int rest = code%2;
			erg = rest + erg;
			code /= 2;
		}
		return erg;
	}
}
