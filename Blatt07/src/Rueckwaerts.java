
public class Rueckwaerts {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "DHBW Mannheim";
		for (int i = 0; i < 7; i++) {
			text += text;
		}
		System.out.println(text.length() + "|" + text);
		
		long start = System.nanoTime();
		String rueck = reverse1(text);
		long end = System.nanoTime();
		System.out.println("reverse1:" + (end-start));
		System.out.println(rueck);
		
		start = System.nanoTime();
		rueck = reverse2(text);
		end = System.nanoTime();
		System.out.println("reverse2:" + (end-start));
		System.out.println(rueck);
	
		start = System.nanoTime();
		rueck = reverse3(text);
		end = System.nanoTime();
		System.out.println("reverse3:" + (end-start));
		System.out.println(rueck);
		
		start = System.nanoTime();
		char a[] = text.toCharArray();
		reverse4(a, a.length/2);
		rueck = new String(a);
		end = System.nanoTime();
		System.out.println("reverse4:" + (end-start));
		System.out.println(rueck);
	}

	// Neuen String zur Hilfe (langsam)
	private static String reverse1(String text) {
		String erg = "";
		
		for (int i = 0; i < text.length(); i++) {
			erg = text.charAt(i) + erg;
		}
		return erg;
	}

	// Verwende Array mit Dreieckstausch
	private static String reverse2(String text) {
		char a[] = text.toCharArray();
		int len = a.length;
		
		for (int i = 0; i < len/2; i++) {	// nur bis zur Hälfte!
			char tmp = a[i];	// Dreieckstausch
			a[i] = a[len-i-1];
			a[len-i-1] = tmp;
		}
		return new String(a);
	}

	// Verwende Java Bibliothek StringBuffer
	private static String reverse3(String text) {
		StringBuffer sb = new StringBuffer(text);
		return sb.reverse().toString();
	}

	// Übung zur Rekursion
	private static void reverse4(char a[], int pos) {
		if (pos>=0) {
			int len = a.length;
			char tmp = a[pos];
			a[pos] = a[len-pos-1];
			a[len-pos-1] = tmp;
			
			reverse4(a, pos-1);
		}
	}

}
