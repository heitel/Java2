
public class TextFormatieren {
	private static final int LEN = 80;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String line[] = FileInput.readTextFile("stufen.txt");
		
		lineal();
		linksbuendig(line);
		
		lineal();
		rechtsbuendig(line);
		
		lineal();
		zentriert(line);
	}

	private static void zentriert(String[] lines) {
		for (int i = 0; i < lines.length; i++) {
			int fill = LEN - lines[i].length();
			
			for (int j = 0; j < fill/2; j++) {
				System.out.print(" ");
			}
			System.out.println(lines[i]);
		}	
	}

	private static void lineal() {
		for (int i = 0; i < LEN/10; i++) {
			System.out.print(i + "         ");
		}
		System.out.println();
		for (int i = 0; i < LEN/10; i++) {
			System.out.print("|----+----");
		}
		System.out.println();
	}

	private static void linksbuendig(String[] lines) {
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}
	}

	private static void rechtsbuendig(String[] lines) {
		for (int i = 0; i < lines.length; i++) {
			int fill = LEN - lines[i].length();
			
			for (int j = 0; j < fill; j++) {
				System.out.print(" ");
			}
			System.out.println(lines[i]);
		}
		
	}

}
