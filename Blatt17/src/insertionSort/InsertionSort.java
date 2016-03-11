package insertionSort;

public class InsertionSort {
	private char a[];
	private int step = 0;

	public InsertionSort(char[] a) {
		this.a = a;
	}

	public boolean sortOne() {
		if (step < a.length) {
			// min suchen
			int minPos = step;
			char min = a[step];
			for (int i = step; i < a.length; i++) {
				if (a[i] < min) {
					minPos = i;
					min = a[i];
				}
			}
			// aufrutschen
			char tmp = a[minPos];
			for (int i = minPos; i > step; i--) {
				a[i] = a[i-1];
			}
			a[step] = tmp;
			
			step++;
			return true;
		}
		return false;
	}
	
	public static void main(String args[]) {
		char[] a = "DHBWMANNHEIM".toCharArray();
		InsertionSort s = new InsertionSort(a);
		System.out.println(a);
		while (s.sortOne()) {
			System.out.println(a);
		}
	}
}
