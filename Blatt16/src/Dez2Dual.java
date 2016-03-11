public class Dez2Dual {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();
		
		System.out.println("Umwandlung eine Dezimalzahl in eine Dualzahl");
		System.out.println("============================================");
		
		for(;;) {
			int zahl = con.readInt("Geben Sie bitte eine Dezimalzahl ein: ");
			String txt = dez2Dual(zahl);

			System.out.println(txt);
		}
		
	}

	private static String dez2Dual(int z) {
		if (z == 0) {
			return "";
		}
		long tmp = z & 0xFFFFFFFFL;
		return dez2Dual((int)(tmp / 2)) + tmp % 2;
	}

}
