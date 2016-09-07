package insertionSort;

public class InsertionSort {
	private char a[];
	private int step = 1;

	public InsertionSort(char[] a) {
		this.a = a;
	}

	public boolean sortOne() {
		if (step < a.length) {
			char tmp = a[step];
			// find insertion location
			int j = step;
			while (j>0 && a[j-1]>tmp) {
				a[j] = a[j-1];
				j--;
			}
			// insert element
			a[j] = tmp;
			
			step++;
			return true;
		}
		return false;
	}
	
	public static void main(String args[]) {
		char[] a = "KÖNIGSTUHL".toCharArray();
		InsertionSort s = new InsertionSort(a);
		System.out.println(a);
		while (s.sortOne()) {
			System.out.println(a);
		}
	}
}
