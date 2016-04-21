

public class Quersumme {
	private int zahl;

	public Quersumme(int zahl) {
		this.zahl = zahl;
	}

	public int calc() {
		if (zahl / 10 == 0)
			return zahl;
		Quersumme qs = new Quersumme(zahl / 10);
		return zahl % 10 + qs.calc();
	}

	public static void main(String[] args) {
		Quersumme qs = new Quersumme(9876);
		System.out.println(qs.calc());
	}

}
