package bruch;

public class Bruch {
	private long zaehler = 0;
	private long nenner = 1;

	public Bruch() {

	}

	public Bruch(long z, long n) {
		zaehler = z;
		nenner = n;
	}

	public void kuerzen() {
		// System.out.println("Vorher" + this);
		long teilen = ggT(zaehler, nenner);
		zaehler /= teilen;
		nenner /= teilen;
		// System.out.println("Nacher" + this);
	}

	public void erweitern(long z) {
		zaehler *= z;
		nenner *= z;
	}

	public long getZaehler() {
		return zaehler;
	}

	public long getNenner() {
		return nenner;
	}

	public double getDecimalValue() {
		return (double) zaehler / nenner;
	}

	public void setZaehler(long zaehler) {
		this.zaehler = zaehler;
	}

	public void setNenner(long nenner) {
		this.nenner = nenner;
	}

	public Bruch add(Bruch b) {
		kuerzen();
		b.kuerzen();

		long multi = kgV(nenner, b.getNenner());

		erweitern(multi / nenner);
		b.erweitern(multi / b.getNenner());

		Bruch erg = new Bruch(zaehler + b.getZaehler(), multi);
		erg.kuerzen();

		return erg;
	}

	public Bruch sub(Bruch b) {
		kuerzen();
		b.kuerzen();

		long multi = kgV(nenner, b.getNenner());

		erweitern(multi / nenner);
		b.erweitern(multi / b.getNenner());

		Bruch erg = new Bruch(zaehler - b.getZaehler(), multi);
		erg.kuerzen();

		return erg;
	}

	public Bruch mul(Bruch b) {
		kuerzen();
		b.kuerzen();

		Bruch erg = new Bruch(zaehler * b.getZaehler(), nenner
				* b.getNenner());
		erg.kuerzen();

		return erg;
	}

	public Bruch div(Bruch b) {
		kuerzen();
		b.kuerzen();

		Bruch erg = new Bruch(zaehler * b.getNenner(), nenner
				* b.getZaehler());
		erg.kuerzen();

		return erg;
	}

	@Override
	public String toString() {
		return zaehler + " / " + nenner + " = " + getDecimalValue();
	}

	
	@Override
	protected Object clone() {
		return new Bruch(zaehler, nenner);
	}

	private long ggT(long z, long n) {
		z = Math.abs(z);
		n = Math.abs(n);

		long min = Math.min(z, n);
		long max = Math.max(z, n);

		for (long i = min; i > 1; i--) {
			if (max % i == 0 && min % i == 0) {
				return i;
			}
		}

		return 1;
	}

	private long kgV(long n1, long n2) {
		// System.out.print("kgV " + n1 + " und " + n2);
		n1 = Math.abs(n1);
		n2 = Math.abs(n2);

		long min = Math.min(n1, n2);
		long max = Math.max(n1, n2);
		long erg = min * max;

		for (long i = max; i <= min * max; i++) {
			if (i % max == 0 && i % min == 0) {
				erg = i;
				break;
			}
		}
		// System.out.println(" = " + erg);
		return erg;
	}

}
