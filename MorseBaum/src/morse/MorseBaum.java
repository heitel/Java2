package morse;

public class MorseBaum {
	private Knoten wurzel = new Knoten(null);

	public MorseBaum() {
		String[] line = FileInput.readTextFile("morse.txt");

		for (int i = 0; i < line.length; i++) {
			String[] tmp = line[i].split("~~~");
			Code c = new Code(tmp[0], tmp[1]);
			add(c);
		}
	}

	private void add(Code c) {
		wurzel.add(c, c.getMorse());
	}

	public String find(String morse) {
		return wurzel.find(morse);
	}

	@Override
	public String toString() {
		return "MorseBaum []\n" + wurzel.toString();
	}

	public static void main(String[] args) {
		MorseBaum baum = new MorseBaum();

		System.out.println(baum);
		
		System.out.println(baum.find("-..."));
	}

}
