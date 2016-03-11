public class GGT {
	public static void main(String[] args) {
		int z1 = 564;//567432;
		int z2 = 428;//134268;

		System.out.println(euklid1(z1, z2));
		System.out.println(euklid2(z1, z2));
		System.out.println(euklid3(z1, z2));
		System.out.println(euklid4(z1, z2));
		
		int erg = ggT(z1, z2);
		System.out.println("Der ggT von " + z1 + " und " + z2 + " lautet: "
				+ erg);
	}

	private static int ggT(int z1, int z2) {
		z1 = Math.abs(z1);
		z2 = Math.abs(z2);
		int min = Math.min(z1, z2);

		for (int i = min; i > 1; i--) {
			if (z1 % i == 0 && z2 % i == 0) {
				return i;
			}
		}

		return 1;
	}

	private static int euklid1(int z1, int z2) {
		z1 = Math.abs(z1);
		z2 = Math.abs(z2);

		while (z2 != 0) {
			if (z1 > z2) {
				z1 = z1 - z2;
			} else {
				z2 = z2 - z1;
			}
		}
		return z1;
	}
	
	private static int euklid2(int z1, int z2) {
		z1 = Math.abs(z1);
		z2 = Math.abs(z2);
		
		// falls eine Zahl 0 ist, gebe andere zurück
		if (z1 == 0) return z2;
		if (z2 == 0) return z1;
		
		if (z1>z2) {
			return euklid2(z1-z2, z2);
		}
		else {
			return euklid2(z1, z2-z1);
		}
	}
	
	private static int euklid3(int z1, int z2) {
		z1 = Math.abs(z1);
		z2 = Math.abs(z2);
		
		while (z2!=0) {
			int h = z1%z2;
			z1 = z2;
			z2 = h;
		}
		return z1;
	}
	
	private static int euklid4(int z1, int z2) {
		z1 = Math.abs(z1);
		z2 = Math.abs(z2);
		
		// falls eine Zahl 0 ist, gebe andere zurück
		if (z1 == 0) return z2;
		if (z2 == 0) return z1;
		
		if (z1>z2) {
			return euklid4(z1%z2, z2);
		}
		else {
			return euklid4(z1, z2%z1);
		}

	}
}
