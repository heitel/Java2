package fahrkarte;

public class Ziel {
	private String ort;
	private double preis;
	
	public Ziel(String ort, double preis) {
		this.ort = ort;
		this.preis = preis;
	}

	@Override
	public String toString() {
		return ort;
	}

	public String getOrt() {
		return ort;
	}

	public double getPreis() {
		return preis;
	}

	
}
