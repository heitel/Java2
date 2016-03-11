public class Zahlenraten {
	private static int getRandom(int low, int high) {
		return (int) (Math.random() * (high - low + 1)) + low;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int maxVers = 7; // ld 100
		int zz = getRandom(0, 100);
		Console con = new Console();
		System.out
				.println("Ich habe mir eine Zahl zwischen 0 und 100 ausgedacht.");
		System.out.println("Kannst Du sie erraten?");
		int eingabe;
		int i = 0;

		do {
			eingabe = con.readInt("Versuch " + (i + 1) + " ");
			i++;
			if (eingabe > zz) {
				System.out.println("zu groß");
			}
			if (eingabe < zz) {
				System.out.println("zu klein");
			}
		} while (zz != eingabe && i < maxVers);

		if (eingabe == zz) {
			System.out.println("Gratulation! Die gedachte Zahl lautet: " + zz);
		} else {
			if (i == maxVers) {
				System.out.println("Schlechte Ratestrategie");
			}
		}
	}

}
