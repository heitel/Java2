
public class Zinsen {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double kapital = 30000;
		int t = 100;
		
		System.out.println("Kapital: " + kapital);
		System.out.println("Tage: " + t);
		System.out.println();
		System.out.println("Zins [%] | absolut ");
		System.out.println("---------+---------");
		
		for (double p = 7.5; p < 8.5; p +=0.1) {
			double zins = kapital * t * p / (100 * 360);
			System.out.print(Console.padStringLeft(Console.Double2String("0.00", p), 8));
			System.out.print(" | ");
			System.out.println(Console.padStringLeft(Console.Double2String("0.00", zins), 8));
		}		
	}
}
