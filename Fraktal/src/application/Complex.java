package application;
/**
 * @author jh
 *
 * Complex
 * Erstellt am: 16.04.2004 um 10:52:46
 */
public class Complex {
	public final double x; // Realteil
	public final double y; // Imagin�rteil
	/**
	 * Standard Constructor erzeugt 0 + i0
	 */
	public Complex() {
		x = 0.0;
		y = 0.0;
	}
	/**
	 * Erzeugt x + iy
	 * 
	 * @param x
	 *            Realteil
	 * @param y
	 *            Imagin�rteil
	 */
	public Complex(double x, double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Copy Constructor
	 * 
	 * @param z
	 *            Komplexe Zahl
	 */
	public Complex(Complex z) {
		x = z.getX();
		y = z.getY();
	}
	/**
	 * Realteil einer komplexen Zahl
	 * 
	 * @return Returns the x.
	 */
	public double getX() {
		return x;
	}
	/**
	 * Imagin�rteil einer komplexen Zahl
	 * 
	 * @return Returns the y.
	 */
	public double getY() {
		return y;
	}
	/**
	 * Quadrat des Betrages einer komplexen Zahl
	 * 
	 * @return x^2+y^2.
	 */
	public double normSquared() {
		return x * x + y * y;
	}
	/**
	 * Betrag einer komplexen Zahl
	 * 
	 * @return sqrt(x^2+y^2).
	 */
	public double norm() {
		return Math.sqrt(x * x + y * y);
	}
	/**
	 * Winkel einer komplexen Zahl
	 * 
	 * @return atan(y/x).
	 */
	public double phi() {
		return Math.atan2(y, x);
	}
	/**
	 * Konfugiert komplexe Zahl
	 * 
	 * @return x - iy.
	 */
	public Complex conjugate() {
		return new Complex(x, -y);
	}
	/**
	 * Negation einer komplexe Zahl
	 * 
	 * @return -x + iy.
	 */
	public Complex negation() {
		return new Complex(-x, y);
	}
	/**
	 * Kehrwert einer komplexen Zahl
	 * 
	 * @return (x - iy) / (x*x+y*y).
	 */
	public Complex reciprocal() {
		double den = normSquared();
		return new Complex(x / den, -y / den);
	}
	/**
	 * Subtraktion einer komplexen Zahl
	 * 
	 * @param w
	 * @return (x-w.x) + i(y-w.y).
	 */
	public Complex minus(Complex w) {
		return new Complex(x - w.x, y - w.y);
	}
	/**
	 * komplexe Zahl minus reele Zahl t
	 * @param t
	 * @return x-t + iy.
	 */
	public Complex minus(double t) {
		return new Complex(x - t, y);
	}
	/**
	 * Wurzel einer komplexen Zahl
	 * 
	 * @return x-t + iy.
	 */
	public Complex sqrt() {
		double r = Math.sqrt(norm());
		double alpha = phi() / 2.0;
		return new Complex(r * Math.cos(alpha), r * Math.sin(alpha));
	}
	/**
	 * Addition einer komplexen Zahl
	 * 
	 * @return (x+w.x) + i(y+w.y).
	 */
	public Complex plus(Complex w) {
		return new Complex(x + w.x, y + w.y);
	}
	/**
	 * komplexe Zahl plus reele Zahl t
	 * 
	 * @return x+t + iy.
	 */
	public Complex plus(double t) {
		return new Complex(x + t, y);
	}
	/**
	 * multiplikation Komplexe Zahl mal reele Zahl
	 * 
	 * @return .
	 */
	public Complex times(double t) {
		return new Complex(x * t, y * t);
	}
	/**
	 * multiplikation Komplexe Zahl mal komplexe Zahl
	 * 
	 * @return .
	 */
	public Complex times(Complex w) {
		return new Complex(x * w.x - y * w.y, y * w.x + x * w.y);
	}
	/**
	 * Division Komplexe Zahl durch reele Zahl
	 * 
	 * @return .
	 */
	public Complex over(double t) {
		return new Complex(x / t, y / t);
	}
}