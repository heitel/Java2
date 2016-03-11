public class Chiffre {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();

		System.out.println("Programm zum verschlüsseln eines Textes.");

		int key = con.readInt("Wie viel soll verschlüsselt werden? ");
		String text = con.readString("Geben Sie eine Nachricht ein: ");
		String coded = chiffre(text, key);
		System.out.println(coded);
	}

	private static String chiffre(String text, int key) {
		char array[] = text.toCharArray();

		while (key < 0) {
			key += 26;
		}

		for (int i = 0; i < array.length; i++) {
			char c = array[i];
			if (c >= 'A' && c <= 'Z') {
				array[i] = (char) (((array[i] - 'A') + key) % 26 + 'A');
			}
			if (c >= 'a' && c <= 'z') {
				array[i] = (char) (((array[i] - 'a') + key) % 26 + 'a');
			}
		}
		return new String(array);
	}

}
