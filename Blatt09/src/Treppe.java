
public class Treppe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int SIZE = 5;
		zeichneTreppe(SIZE);
	}

	private static void zeichneTreppe(int size) {
		for (int i = 0; i < size; i++) {
			zeichneBlank(size-i-1);
			zeichneLinie(i+1);
			zeichneBlank(size-i-1);
			zeichneMitte(i+1);
		}
		zeichneLinie(size);
	}
	
	private static void zeichneBlank(int size) {
		for (int i = 0; i < size; i++) {
			System.out.print("    ");
		}
	}
	
	private static void zeichneMitte(int size) {
		for (int i = 0; i < size; i++) {
			System.out.print("|   ");
		}
		System.out.println("|");
	}

	private static void zeichneLinie(int size) {
		for (int i = 0; i < size; i++) {
			System.out.print("+---");
		}
		System.out.println("+");		
	}
}
