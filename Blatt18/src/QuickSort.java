

public class QuickSort {
	private int[] a;

	public QuickSort(int[] a) {
		this.a = a;
	}

	public void sort() {
		System.out.println("Start:");
		showArray(0, a.length - 1);
		sort(0, a.length - 1);
		System.out.println("Ende:");
		showArray(0, a.length - 1);
	}

	private void sort(int from, int to) {
		if (from >= to) {
			return;
		}
		int p = partition(from, to);
		sort(from, p);
		sort(p + 1, to);
	}

	private int partition(int from, int to) {
		int pivot = a[from]; // erstes Element
		int i = from - 1;
		int j = to + 1;

		while (i < j) {
			i++;
			while (a[i] < pivot) {
				i++;
			}

			j--;
			while (a[j] > pivot) {
				j--;
			}
			if (i < j) {
				swap(i, j);
				showArray(from, to);
			}
		}
		return j;
	}

	private void showArray(int from, int to) {
		System.out.print("(" + from + "," + to + ") [");
		for (int i = from; i < to; i++) {
			System.out.print(a[i] + ",");
		}
		System.out.println(a[to] + "]");
	}

	private void swap(int i, int j) {
		if (i == j)
			return;

		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 int a[] = { 5, 3, 2, 6, 4, 1, 3, 7 };
		//int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		QuickSort qs = new QuickSort(a);
		qs.sort();
	}

}
