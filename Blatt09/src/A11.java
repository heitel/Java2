import java.awt.Point;
import java.awt.Rectangle;


public class A11 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Overloads der Klasse String
		String s0 = new String(); // Leere Zeichenkette
		char a[] = {'H', 'a', 'l', 'l', 'o' };
		String s1 = new String(a); // Erzeuge Zeichenkette aus char array
		
		System.out.println("Harbison".substring(3));
		System.out.println("smiles".substring(1, 5));
		
		// Overloads der Rectangle
		Rectangle	r0 = new Rectangle(); // Rechteck 0,0, 0,0
		Rectangle	r1 = new Rectangle(30,40); // Höhe/breite
		
		r1.setLocation(10, 20);
		r1.setLocation(new Point(100, 20));

	}

}
