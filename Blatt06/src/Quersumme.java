
public class Quersumme {
	public static void main(String args[]) {
		Console con = new Console();

		System.out
				.println("Programm zur Berechnung der Quersumme einer Zahl.");
		int zahl = con.readInt("Bitte geben Sie eine ganze positive Zahl ein: ");

		int summe = 0;
		while (zahl>0) {
			summe += (zahl%10);
			zahl /= 10;
		}
		
		System.out.print("Die lautet " + summe);

	}
	
}
