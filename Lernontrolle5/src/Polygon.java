
public class Polygon {
	private double[] x;
	private double[] y;
	private final int len;

	public Polygon(double[] x, double[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("x und y müssen gleichlang sein!");
		}
		this.x = x;
		this.y = y;
		len = x.length;
	}

	public double calcAreaIterativ() {
		double sum = 0;

		for (int i = 0; i < len; i++) {
			sum += x[i] * y[(i + 1) % len];
			sum -= y[i] * x[(i + 1) % len];
		}

		return Math.abs(sum / 2);
	}

	public double calcCircumferenceIterativ() {
		double sum = 0;

		for (int i = 0; i < len; i++) {
			double dx = x[(i + 1) % len] - x[i];
			double dy = y[(i + 1) % len] - y[i];
			sum += Math.sqrt(dx * dx + dy * dy);
		}

		return sum;
	}

	public double calcAreaRecursiv() {
		return Math.abs(calcAreaRek(len - 1) / 2);
	}

	private double calcAreaRek(int n) {
		if (n < 0) {
			return 0;
		}
		double tmp = x[n] * y[(n + 1) % len] - y[n] * x[(n + 1) % len];
		return calcAreaRek(n - 1) + tmp;
	}

	public double calcCircumferenceRecursiv() {
		return calcCircumferenceRek(len - 1);
	}

	private double calcCircumferenceRek(int n) {
		if (n < 0) {
			return 0;
		}
		double dx = x[(n + 1) % len] - x[n];
		double dy = y[(n + 1) % len] - y[n];
		return calcCircumferenceRek(n-1) + Math.sqrt(dx*dx+dy*dy);
	}

	public static void main(String[] args) {
		double[] x = { 10, 30, 30, 20,10 };
		double[] y = { 10, 10, 20, 40, 20 };

		Polygon poly = new Polygon(x, y);
		System.out.println("Iterativ: ");
		System.out.println("Fläche: " + poly.calcAreaIterativ());
		System.out.println("Umfang: " + poly.calcCircumferenceIterativ());

		System.out.println("\nRekursiv: ");
		System.out.println("Fläche: " + poly.calcAreaRecursiv());
		System.out.println("Umfang: " + poly.calcCircumferenceRecursiv());
	}
}
