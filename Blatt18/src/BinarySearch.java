public class BinarySearch {
	private int[] a;

	public BinarySearch(int[] a) {
		this.a = a;
	}

	public int search(int x) {
		return search(x, 0, a.length - 1);
	}

	private int search(int x, int from, int to) {
		if (from == to) {
			if (x == a[from]) {
				return from;
			} else {
				return -from - 1;
			}
		}
		int mitte = (from + to) / 2;
		if (x > a[mitte]) {
			return search(x, mitte + 1, to);
		}
		return search(x, from, mitte);
	}

	public int search2(int x) {
		int from = 0;
		int to = a.length - 1;

		while (from <= to) {
			int mitte = (from + to) / 2;
			int diff = x - a[mitte];
			if (diff == 0) {
				return mitte;
			}
			if (diff > 0) { // x>a[mitte]
				from = mitte + 1;
			} else { // x<a[mitte]
				to = mitte - 1;
			}
		}

		return -from - 1;
	}

	public static void main(String[] args) {
		Console con = new Console();
		int[] a = { 1, 4, 9, 13, 17, 21, 39, 43, 54, 68, 70 };

		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();

		int suche;
		do {
			suche = con.readInt("Suche (oder -1 für Ende): ");
			BinarySearch ls = new BinarySearch(a);
			int pos = ls.search(suche);
			System.out.println("Gefunden: " + pos);
		} while (suche != -1);
	}
}
