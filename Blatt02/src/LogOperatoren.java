
public class LogOperatoren {

	public static void main(String[] args) {
		int a = 10;
		// a
		if (a>=-20 && a<=100) {
			System.out.println("a ist im Intervall [-20, 100]");
		}
		
		int x = -1;
		int y = 20;
		// b
		if (x<0 && y>=5 && y<=30) {
			System.out.println("x ist neg. und y im Intervall [5, 30]");
		}
		
		// c
		int jahr = 2010;
		if ((jahr%100!=0 && jahr%4==0) || jahr%400==0) {
			System.out.println(jahr +" ist Schaltjahr" );
		}
		
		// e
		int b = 0x7EFF;
		int erg = a*b;
		
		if (erg > Short.MAX_VALUE) {
			System.out.println("Wert paßt nicht short Datentyp!");
		}
		
		// f
		char c = 'L';
		
		if (!(c=='J' || c=='j')) {
			System.out.println("c ist weder j noch J");
		}
		
		// g
		int zaehler = 25;
		
		if (zaehler<5 || zaehler>25) {
			System.out.println("zaehler ist nicht im Intervall [5,25]");
		}

	}

}
