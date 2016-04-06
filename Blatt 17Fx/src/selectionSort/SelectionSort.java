package selectionSort;

public class SelectionSort {
	private char[] data;
	private int pos = 0;

	public SelectionSort(String txt) {
		setData(txt);
	}

	public void setData(String txt) {
		data = txt.toCharArray();
		pos = 0;
	}

	public String getData() {
		return new String(data);
	}

	public boolean step() {
		if (pos < data.length - 1) {
			int min = findMin();

			char tmp = data[pos];
			data[pos] = data[min];
			data[min] = tmp;
			pos++;
			return true;
		}
		return false;
	}

	private int findMin() {
		int min = Integer.MAX_VALUE;
		char c = 0xFFFF;

		for (int i = pos; i < data.length; i++) {
			if (data[i] < c) {
				c = data[i];
				min = i;
			}
		}

		return min;
	}
}
