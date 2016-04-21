
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
		if (len == 3) {
			return Math.abs(x[0]*y[1]+x[1]*y[2]+x[2]*y[0] - y[0]*x[1]-y[1]*x[2]-y[2]*x[0])/2;
		}
		double[] x3 = new double[3];
		double[] y3 = new double[3];
		System.arraycopy(x, 0, x3, 0, 3);
		System.arraycopy(y, 0, y3, 0, 3);
		PolygonRek p3 = new PolygonRek(x3, y3);
		
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
		return p3.calcArea() + ps.calcArea();
	}


	public static void main(String[] args) {
		double[] x = { 10, 30, 30, 20,10 };
		double[] y = { 10, 10, 20, 40, 20 };

		PolygonRek poly = new PolygonRek(x, y);

		System.out.println("\nRekursiv: ");
		System.out.println("Fläche: " + poly.calcArea());
	}
}
