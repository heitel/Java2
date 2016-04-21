package morseStart;

public class Knoten {
	private static int blank = 0;
	private Knoten[] child = new Knoten[2];
	private Code data;

	public Knoten(Code data) {
		this.data = data;
	}

	private void setData(Code c) {
		this.data = c;
	}

	public void add(Code c, String s) {
		if (s.length() == 0)
			return;

		char first = s.charAt(0);
		int index = (first == '.') ? 0 : 1;

		if (s.length() == 1) {
			if (child[index] == null) {
				child[index] = new Knoten(c);
			} else {
				child[index].setData(c);
			}
			return;
		} else {
			if (child[index] == null) {
				child[index] = new Knoten(null);
			}
			child[index].add(c, s.substring(1));
		}
	}

	@Override
	public String toString() {
		blank++;
		String erg = "";
		if (child[0] != null) {
			erg += child[0].toString();
		}
		blank--;
		erg += someBlank();
		if (data != null) {
			erg += data.toString();
		} else {
			erg += "#";
		}
		erg += "\n";

		blank++;
		if (child[1] != null) {
			erg += child[1].toString();
		}
		blank--;
		return erg;
	}

	private String someBlank() {
		String erg = "";

		for (int i = 0; i < blank; i++) {
			erg += "           ";
		}

		return erg;
	}

	public String find(String morse) {
		if (morse.length() == 0) {
			return data.getZeichen();
		}
		int index = 0;
		if (morse.charAt(0) == '-') {
			index = 1;
		}
		if (child[index] != null) {
			return child[index].find(morse.substring(1));
		}

		return null;
	}
}
