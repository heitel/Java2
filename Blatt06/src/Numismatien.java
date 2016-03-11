public class Numismatien {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int preis = 357;
		int anz = 67;
		int count = 0;

		for (int drei = 1; drei <= (preis-12) / 3; drei++) {
			for (int fuenf = 1; fuenf <= (preis-10) / 5; fuenf++) {
				for (int sieben = 1; sieben <= (preis-8) / 7; sieben++) {
					if (drei + fuenf + sieben == anz
							&& drei * 3 + fuenf * 5 + sieben * 7 == preis) {
						count++;
						System.out.println(count+": " 
						+ drei +"*3Mork + " +
							fuenf+"*5Mork + " +
								sieben+"*7Mork = " + preis);
					}
				}
			}
		}

	}

}
