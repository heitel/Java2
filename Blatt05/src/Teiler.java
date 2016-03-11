
public class Teiler {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();
		
		System.out.println("Dieses Programm findet alle Teiler einer Zahl.");
		System.out.println("==============================================");
		int zahl = con.readInt("Bitte geben Sie eine ganze Zahl ein: ");
		
		System.out.println("Die Teiler von " + zahl + " sind: ");
		boolean flag = false;
		for (int i = 2; i < zahl; i++) {
			if (zahl % i == 0) {
				flag = true;
				System.out.print(i+" ");
			}
		}
		if (!flag) {
			System.out.println("\t" + zahl + " ist eine Primzahl");
		}
	}

}
