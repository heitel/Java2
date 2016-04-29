
public class Palindrom {
	private String satz;

	public Palindrom(String satz) {
		this.satz = satz;
	}

	public boolean isPalindrom() {
		int len = satz.length();
		if (len <= 1)
			return true;

		char vorne = 0;
		int i = 0;
		while (i < len) {
			char tv = satz.charAt(i);
			if ((tv >= 'A' && tv <= 'Z') || (tv >= 'a' && tv <= 'z')) {
				vorne = tv;
				break;
			}
			i++;
		}

		char hinten = 0;
		int j = len - 1;
		while (j >= 0) {
			char th = satz.charAt(j);
			if ((th >= 'A' && th <= 'Z') || (th >= 'a' && th <= 'z')) {
				hinten = th;
				break;
			}
			j--;
		}
		if (j <= i)
			return true;
		if (Character.toUpperCase(vorne) == Character.toUpperCase(hinten)) {
			Palindrom p = new Palindrom(satz.substring(i + 1, j));
			return p.isPalindrom();
		}

		return false;
	}

	public static void main(String[] args) {
		String[] test = { "Die Liebe ist Sieger, stets rege ist sie bei Leid.",
				"Die Niere bot Komik: nass sank im Oktober ein Eid.", "Ein Examen? Ne Maxe, nie!",
				"Es eilt, immer ahnend Nebel, reger der Flegel Fred, reg' erlebend nen Harem mit Liese",
				"Trug Tim eine so helle Hose nie mit Gurt?"};

		for (String s : test) {
			Palindrom pali = new Palindrom(s);
			System.out.println(s + " | " + pali.isPalindrom());
		}
		
	}

}
