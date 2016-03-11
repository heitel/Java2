
public class Zeitberechnung {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Umrechnung einer Anzahl Sekunden in ");
		System.out.println("Tage Stunden:Minuten:Sekunden");
		
		int sekunden = 123456;
		System.out.println("Sekunden: " + sekunden + " ergibt:");
		
		int tage = sekunden / (24*3600);
		System.out.print(tage + "Tag(e) ");
		
		sekunden %= (24*3600);
		int stunden = sekunden / 3600;
		System.out.print(stunden + ":");
		
		sekunden %= 3600;
		int minuten = sekunden / 60;
		System.out.print(minuten + ":");

		sekunden %= 60;
		System.out.println(sekunden);
	}

}
