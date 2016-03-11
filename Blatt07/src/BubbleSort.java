import java.util.Arrays;

public class BubbleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int size = 50;
		int data[] = createRandomData(size);//{ 66, 53, 60, 21, 83, 60, 95, 6, 44, 15 };//
		int cd[] = data.clone();
		int rd[] = data.clone();
		
		showData(data);
		long start = System.nanoTime();
		bubbleSort(data);
		long end = System.nanoTime();
		System.out.println("bubbleSort:" + (end-start));
		showData(data);
		System.out.println();

		showData(rd);
		start = System.nanoTime();
		rippleSort(rd);
		end = System.nanoTime();
		System.out.println("rippleSort:" + (end-start));
		showData(rd);
		System.out.println();

		
		showData(cd);
		start = System.nanoTime();
		Arrays.sort(cd);
		end = System.nanoTime();
		System.out.println("Arrays.sort:" + (end-start));
		showData(cd);
	}

	private static int[] createRandomData(int size) {
		int erg[] = new int[size];
		for (int i = 0; i < erg.length; i++) {
			erg[i] = (int)(Math.random()*size);
		}
		return erg;
	}

	private static void rippleSort(int[] data) {
		int len = data.length;

		for (int j = 0; j < len; j++) {
			boolean tausch = false;
			for (int i = j + 1; i < len; i++) {
				if (data[j] < data[i]) {
					int tmp = data[j];
					data[j] = data[i];
					data[i] = tmp;
					tausch = true;
				}
			}
//			showData(data);
			if (!tausch) {
				break;
			}
		}
	}
	private static void bubbleSort(int[] data) {
		boolean tausch;
		int max = data.length - 1;

		do {
			tausch = false;
			for (int i = 0; i < max; i++) {
				if (data[i] < data[i+1]) {
					int tmp = data[i];
					data[i] = data[i+1];
					data[i+1] = tmp;
					tausch = true;
				}
			}
			//showData(data);
			max--;
		} while (tausch);
	}
	private static void showData(int data[]) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

}
