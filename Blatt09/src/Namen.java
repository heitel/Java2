public class Namen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();
		String name = con.readString("Bitte geben Sie ihren Namen ein: ");

		anzeigeMitRahmen(name);
	}

	private static void anzeigeMitRahmen(String name) {
		strich(name.length());
		System.out.println("|" + name + "|");
		strich(name.length());
	}

	private static void strich(int len) {
		System.out.print("+");
		for (int i = 0; i < len; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}

}
