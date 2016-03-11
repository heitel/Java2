

public class MergeSorter {
	private char a[];

	public MergeSorter(char[] a) {
		this.a = a;
	}

	public void sort() {
		System.out.println(a);
		if (a.length == 1)
			return;

		char first[] = new char[a.length / 2];
		char second[] = new char[a.length - first.length];
		System.arraycopy(a, 0, first, 0, first.length);
		System.arraycopy(a, first.length, second, 0, second.length);

		MergeSorter firstSorter = new MergeSorter(first);
		MergeSorter secondSorter = new MergeSorter(second);
		firstSorter.sort();
		secondSorter.sort();

		merge(first, second);
	}

	private void merge(char[] first, char[] second) {
		int iFirst = 0;
		int iSecond = 0;
		int i = 0;

		while (iFirst < first.length && iSecond < second.length) {
			if (first[iFirst] < second[iSecond]) {
				a[i] = first[iFirst];
				iFirst++;
			} else {
				a[i] = second[iSecond];
				iSecond++;
			}
			i++;
		}

		while (iFirst < first.length) {
			a[i] = first[iFirst];
			i++;
			iFirst++;
		}

		while (iSecond < second.length) {
			a[i] = second[iSecond];
			i++;
			iSecond++;
		}
		System.out.print("merge: ");
		System.out.println(a);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char a[] = "DHBWMANNHEIM".toCharArray();

		MergeSorter s = new MergeSorter(a);
		s.sort();
		System.out.println(a);
	}

}
