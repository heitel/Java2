package fahrkartenautomat;

public class Ziel {
	private String ort;
	private double preis;
	
	public Ziel(String ort, double preis) {
		this.ort = ort;
		this.preis = preis;
	}
	
	public String getOrt() {
		return ort;
	}

	public double getPreis() {
		return preis;
	}

	@Override
	public String toString() {
		return ort;
	}
}
