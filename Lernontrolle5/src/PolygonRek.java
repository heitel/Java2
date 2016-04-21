
public class PolygonRek {
	private double[] x;
	private double[] y;
	private final int len;

	public PolygonRek(double[] x, double[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException("x und y müssen gleichlang sein!");
		}
		this.x = x;
		this.y = y;
		len = x.length;
	}


	public double calcArea() {
		double dreieck = Math.abs(x[0]*y[1]+x[1]*y[2]+x[2]*y[0] - y[0]*x[1]-y[1]*x[2]-y[2]*x[0])/2;
		if (len == 3) {
			return dreieck;
		}
		
		double[] xs = new double[len-1];
		double[] ys = new double[len-1];
		int j = 0;
		for (int i = 0; i < len; i++) {
			if (i!=1) {
				xs[j] = x[i];
				ys[j] = y[i];
				j++;
			}
		}
		PolygonRek ps = new PolygonRek(xs, ys);
		return dreieck + ps.calcArea();
	}


	public static void main(String[] args) {
		double[] x = { 10, 30, 30, 10};
		double[] y = { 10, 10, 20, 20 };

		PolygonRek poly = new PolygonRek(x, y);

		System.out.println("\nRekursiv: ");
		System.out.println("Fläche: " + poly.calcArea());
	}
}
