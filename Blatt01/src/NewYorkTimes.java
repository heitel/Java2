
public class NewYorkTimes {
	public static void main(String[] args) {
		int auflage = 2000000; // 2 Mio
		double masse = 2.0;    // 2kg
		double gesamtPapier = auflage * masse;
		double anzahlBaeume = gesamtPapier / 1000;
		System.out.println("Die NY Times kostet " + anzahlBaeume + " Bäume das Leben.");
	}
}
