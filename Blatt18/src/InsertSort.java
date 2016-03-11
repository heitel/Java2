

public class InsertSort {
	private int[] a;

	public InsertSort(int[] a) {
		this.a = a;
	}

	public void sort() {
		showArray();
		for (int i = 1; i < a.length; i++) {
			int next = a[i];
			int j = i;
			while(j>0&&a[j-1]>next){
				a[j] = a[j-1]; // Schieben nach rechts
				j--;
			}
			a[j] = next;
			showArray();
		}
	
	}

	


	private void showArray() {
		System.out.print("[");
		for (int i = 0; i < a.length-1; i++) {
			System.out.print(a[i] + ",");
		}
		System.out.println(a[a.length-1] + "]");
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 int a[] = { 5, 3, 2, 6, 4, 1, 3, 7 };
		//int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		InsertSort qs = new InsertSort(a);
		qs.sort();
	}

}
