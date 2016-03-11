package selectionSort;
public class SelectionSort {
	private char a[];
	private int step = 0;

	public SelectionSort(char[] a) {
		this.a = a;
	}

	public boolean sortOne() {
		if (step < a.length) {
			int i = step;
			// min suchen
			int minPos = i;
			char min = a[i];
			for (int j = i; j < a.length; j++) {
				if (a[j] < min) {
					minPos = j;
					min = a[j];
				}
			}

			// tauschen
			char tmp = a[i];
			a[i] = a[minPos];
			a[minPos] = tmp;
			step++;
			return true;
		}
		return false;
	}
	
	public static void main(String args[]) {
		char[] a = "DHBWMANNHEIM".toCharArray();
		SelectionSort s = new SelectionSort(a);
		System.out.println(a);
		while (s.sortOne()) {
			System.out.println(a);
		}
	}
}
