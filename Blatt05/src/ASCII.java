public class ASCII {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showTable();
	}


	private static void showTable() {
		System.out
				.println("American Standard Code for Information Interchange");

		System.out.println("Dez | Dual     | Hex | Zeichen");
		System.out.println("----+----------+-----+--------");
		for (int i = 0; i < 256; i++) {
			show(i);
		}
	}
	
	
	private static String toDual(int code, int stellen) {
		String erg = "";

		for (int i = 0; i < stellen; i++) {
			erg = code % 2 + erg;
			code /= 2;
		}
		return erg;
	}
	
	private static void show(int i) {
		char zeichen = (char)i;
		System.out.printf("%1$3d%2$s", i, " | ");
		System.out.print(toDual(i, 8) + " | ");
		System.out.print(toHex(i, 2) + "  | ");		
		System.out.println(zeichen);
	}

	private static String toHex(int zahl, int stellen) {
		String erg = "";
		char clut[] = {'0', '1', '2', '3', '4', '5', '6', '7', 
				'8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};  
		
		for (int i = 0; i < stellen; i++) {
			int rest = zahl % 16;
			erg = clut[rest] + erg;
			zahl /= 16;
		}
		
		return erg;
	}

}
