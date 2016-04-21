package quickSort;

public class QSortChar {
	private  char a[];

	public QSortChar(char a[]) {
		this.a = a;
	}

	public void sort() {
		showArray();
		sort(0, a.length-1);
	}
	
	private void sort(int from, int to) {
		if (from >= to) {
			return;
		}
		int p = partition(from, to);
		System.out.println("p="+p);
		sort(from, p);
		sort(p + 1, to);
	}

	private int partition(int from, int to) {
		char pivot = a[from];
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
			}
		}
		return j;
	}

	private void swap(int i, int j) {
		if (i == j)
			return;

		char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
		
		showArray();
	}
	
	private  void showArray() {
		System.out.print("[ ");
		for (int i = 0; i < a.length - 1; i++) {
			System.out.print(a[i] + ", ");
		}
		System.out.println(a[a.length - 1] + "]");
	}
	
	public static void main(String[] args) {
		String s = "DHBWMANNHEIM";
		QSortChar qs = new QSortChar(s.toCharArray());
		qs.sort();
	}
}
