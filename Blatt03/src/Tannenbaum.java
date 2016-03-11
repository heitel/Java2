
public class Tannenbaum {

	private static void baum(int size) {
		// Oberteil des Baums
		for (int i = 0; i < size; i++) { // Zeilen
			for (int j = 0; j < size-i-1; j++) { // Leerzeichen
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i+1; j++) { // Sterne
				System.out.print("*");
			}
			System.out.println();// Zeilenumbruch
		}
		
		// Stamm
		for (int i = 0; i < size-1; i++) {
			System.out.print(" ");
		}
		System.out.println("*");
		for (int i = 0; i < size-2; i++) {
			System.out.print(" ");
		}
		System.out.println("***");
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		baum(6);
	
	}

}
