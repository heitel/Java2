import java.awt.Rectangle;


public class A10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//a Compiler Fehler
		//Rectangle r = (5, 10, 15, 20);
	
		//b new fehlt vor Rectangle Compiler Fehler
		//double width = Rectangle(5, 10, 15, 20).getWidth();
		
		//c r ist nicht initialisert, falls r=null => NullPointerException
		//Rectangle r;
		//r.translate(15, 25);

		//d compiler r hat keinen Datentyp
		//Rectangle r = new Rectangle();
		//r.translate(("far, far away!"); // falsche Parameterübergabe an translate
	}

}
