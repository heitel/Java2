
public class Palindrome {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Programm zum Finden von Palindromen");
		Console con = new Console();
		String txt = con.readString("Eingabe Text: ");
		
		String w[] = txt.split(" ");
		
		for (int i = 0; i < w.length; i++) {
			String bw = new String(new StringBuffer(w[i]).reverse());
			if (bw.equalsIgnoreCase(w[i])) {
				System.out.println(bw + "\t" + w[i]);
			}
		}

	}

}
