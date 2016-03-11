public class LinearSearch {
	private int[] a;

	public LinearSearch(int[] a) {
		this.a = a;
	}

	public int search(int x) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == x) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Console con = new Console();
		final int len = 20;
		int[] a = new int[len];

		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 100);
			System.out.print(a[i] + " ");
		}
		System.out.println();

		int suche;
		do {
			suche = con.readInt("Suche (oder -1 für Ende): ");
			LinearSearch ls = new LinearSearch(a);
			int pos = ls.search(suche);
			System.out.println("Gefunden: " + pos);
		} while (suche != -1);
	}
}
