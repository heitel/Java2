package maximum;



public class MaxDC {
	private static StackPrinter sp = new StackPrinter();
	
	public static int max(int a[], int links, int rechts){
		sp.showCall("max", links, rechts);
		
		if (links == rechts) {
			sp.showReturn(a[links]);
			return a[links];
		}
		int mitte = (links + rechts) / 2;
		int maxLi = max(a, links, mitte);
		int maxR = max(a, mitte+1, rechts);
		int erg = Math.max(maxLi, maxR);
		
		sp.showReturn(erg);
		return erg;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = {15, 91, 3, 123, 5, 83, 7, 64, 69, 101};
		int max = max(a, 0, a.length-1);
		System.out.println(max);

	}

}
