package teilSumme;
public class Max {
	public int a;// Anfang
	public int e;// Ende
	public double value;

	public Max() {
	}

	public Max(int a, int e, double value) {
		this.a = a;
		this.e = e;
		this.value = value;
	}

	@Override
	public String toString() {
		return "Max [a=" + a + ", e=" + e + ", value=" + value + "]";
	}

	public void set(int a, int e, double value) {
		this.a = a;
		this.e = e;
		this.value = value;	
	}
}