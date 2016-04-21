

public class Palindrom {
	private String satz;

	public Palindrom(String satz) {
		this.satz = satz;
	}

	public boolean isPalindrom() {
		if (satz.length() < 0 || satz.length() == 1)
			return true;

		char vorne = 0;
		int i = 0;
		while (i < satz.length() / 2) {
			vorne = satz.charAt(i);
			if (Character.isLetter(vorne)) {
				break;
			}
			i++;
		}

		char hinten = 0;
		int j = satz.length() - 1;
		while ( j > satz.length() / 2) {
			hinten = satz.charAt(j);
			if (Character.isLetter(hinten)) {
				break;
			}
			j--;
		}

		if (Character.toUpperCase(vorne) == Character.toUpperCase(hinten)) {
			Palindrom p = new Palindrom(satz.substring(i+1, j));
			return p.isPalindrom();
		}

		if (satz.length()==2) return true;
		return false;
	}

	public static void main(String[] args) {
		String[] test = { "Die Liebe ist Sieger, stets rege ist sie bei Leid.",
				"Die Niere bot Komik: nass sank im Oktober ein Eid.", "Ein Examen? Ne Maxe, nie!",
				"Es eilt, immer ahnend Nebel, reger der Flegel Fred, reg' erlebend nen Harem mit Liese",
				"Trug Tim eine so helle Hose nie mit Gurt?" };

		for (String s : test) {
			Palindrom pali = new Palindrom(s);
			System.out.println(s + " | " + pali.isPalindrom());
		}
	}

}
