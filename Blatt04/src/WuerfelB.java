public class WuerfelB {
	private static int getRandom(int low, int high) {
		return (int) (Math.random() * (high - low + 1)) + low;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int stat[] = new int[7];
		int count = 10000;
		
		for (int i = 0; i < count; i++) {
			int zz = getRandom(1, 6);
			stat[zz]++;	// Statistik hochzählen
		}
			
		System.out.println("Softwarewürfel");
		System.out.println("Anzahl der Würfe: " + count);
		System.out.println("Augenzahl | Absolute Häufigkeit | Relative Häufigkeit");
		System.out.println("----------+---------------------+--------------------");
		
		for (int i = 1; i < stat.length; i++) {
			double rel = stat[i]*100.0/count;
			System.out.println(i + " : " + stat[i] + " | " + rel + "%");
		}
		
	}

}
