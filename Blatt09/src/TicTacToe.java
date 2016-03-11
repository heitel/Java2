
public class TicTacToe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int SIZE = 3;
		
		zeichneFeld(SIZE);

	}

	private static void zeichneFeld(int size) {
		for (int i = 0; i < size; i++) {
			zeichneLinie(size);
			zeichneMitte(size);
		}
		zeichneLinie(size);
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
