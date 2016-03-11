
public class Buchstaben {

	public static void main(String[] args) {
		// a
		String text = "Mississippi";
		// i->! s->$
		char a[] = text.toCharArray();
		for (int i = 0; i < a.length; i++) {
			if (a[i]=='i') {
				a[i] = '!';
			}
			if (a[i]=='s') {
				a[i] = '$';
			}
		}
		System.out.println(a);
		
		// b
		// Hello World e<->o
		text = "Hello World";
		a = text.toCharArray();
		for (int i = 0; i < a.length; i++) {
			if (a[i]=='e') {
				a[i] = 'o';
				continue;// Schleifendurchlauf beenden damit nicht wieder zurückgetauscht wird
			}
			if (a[i]=='o') {
				a[i] = 'e';
			}
		}
		System.out.println(a);
	}

}
