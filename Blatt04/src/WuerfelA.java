public class WuerfelA {
	private static int getRandom(int low, int high) {
		return (int) (Math.random() * (high - low + 1)) + low;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Console con = new Console();
		while (true) {
			System.out.print(getRandom(1, 6));
			con.readString("");
		}
	}

}
