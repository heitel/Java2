package teilSumme;
import teilSumme.Max;

public class TeilSummeB {
	private static StackPrinter<Max> sp = new StackPrinter<Max>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 double a[] = { 5, -11, 2, 4, -1 };
		//double a[] = { 0, 5, -11, 2, 3, -1, 4, -2, 2, 23, -65, 1, -6, 8, 7, -13 };
		Max max = maxSub(a, 0, a.length - 1);
		System.out.println(max);

	}

	private static Max maxSub(double[] a, int links, int rechts) {
		sp.showCall("maxSub", links, rechts);

		// Einfacher Fall
		if (links == rechts) {
			Max erg = new Max(links, links, a[links]);
			sp.showReturn(erg);
			return erg;
		}

		// Teilen
		int mitte = (links + rechts) / 2;
		Max maxLi = maxSub(a, links, mitte);
		Max maxRe = maxSub(a, mitte + 1, rechts);

		// Randmaxima finden
		Max randLi = new Max();
		double sum = 0;
		for (int i = mitte; i >= links; i--) {
			sum += a[i];
			if (sum > randLi.value) {
				randLi.set(i, mitte, sum);
			}
		}
		
		Max randRe = new Max();
		sum = 0;
		for (int i = mitte+1; i <= rechts; i++) {
			sum += a[i];
			if (sum > randRe.value) {
				randRe.set(mitte+1, i, sum);
			}
		}
		
		// Zusammenführen
		Max rand = new Max(randLi.a, randRe.e, randLi.value + randRe.value);
		Max erg = max(rand, maxLi, maxRe);
		sp.showReturn(erg);
		return erg;
	}

	private static Max max(Max z0, Max z1, Max z2) {
		Max erg = z0;
		if (z1.value > erg.value)
			erg = z1;
		if (z2.value > erg.value)
			erg = z2;
		return erg;
	}
}
