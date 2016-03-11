public class Dez2Dual {
	public static void main(String[] args) {
		int zahl = 68;
		String erg = "";

		System.out.print(zahl);
		while (zahl > 0) {
			int rest = zahl % 2;
			zahl = zahl / 2;
			erg = rest + erg;
		}
		System.out.println(" im Dualsystem " + erg);
	}
}
