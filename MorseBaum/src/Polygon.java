
public class Polygon {

	public static void main(String[] args) {
		double[] x = { 10, 25, 25, 10 };
		double[] y = { 10, 10, 30, 30 };

		System.out.println("Fläche: " + calcArea2(x, y));
		System.out.println("Umfang: " + circumference2(x, y));
	}

	private static double circumference(double[] x, double[] y) {
		double sum = 0;
		int n = x.length;
		for (int i = 0; i < n; i++) {
			double dx = x[(i + 1) % n] - x[i];
			double dy = y[(i + 1) % n] - y[i];
			sum += Math.sqrt(dx * dx + dy * dy);
		}
		return sum;
	}

	private static double circumference2(double[] x, double[] y) {
		return cRek(x, y, x.length - 1);
	}

	private static double cRek(double[] x, double[] y, int i) {
		if (i < 0)
			return 0;
		int n = x.length;
		double dx = x[(i + 1) % n] - x[i];
		double dy = y[(i + 1) % n] - y[i];
		return cRek(x, y, i - 1) + Math.sqrt(dx * dx + dy * dy);
	}

	private static double calcArea(double[] x, double[] y) {
		double sum = 0;
		int n = x.length;
		for (int i = 0; i < n; i++) {
			sum += x[i] * y[(i + 1) % n] - y[i] * x[(i + 1) % n];
		}
		return Math.abs(sum / 2);
	}

	private static double calcArea2(double[] x, double[] y) {
		return Math.abs(rek(x, y, x.length - 1) / 2);
	}

	private static double rek(double[] x, double[] y, int i) {
		if (i < 0)
			return 0;
		int n = x.length;
		return rek(x, y, i - 1) + x[i] * y[(i + 1) % n] - y[i] * x[(i + 1) % n];
	}
}
