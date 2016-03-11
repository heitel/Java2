public class Kreditverlauf {
	public static void main(String arg[]) {
		double kreditBetrag = 50000;
		double jahresZins = 7.5;
		double rate = 3000;

		 verlauf(kreditBetrag, jahresZins, rate);
		 
// Wie hoch ist die Rate bei einer vorgegebenen Laufzeit?
//		int laufZeit = 24;
//		berechneRate(kreditBetrag, jahresZins, laufZeit);
	}

	private static double berechneRate(double kreditBetrag, double jahresZins,
			int laufZeit) {
		double rate = kreditBetrag-0.01;
		double max = rate;
		double min = kreditBetrag * (jahresZins / 1200);
		double diff = 1;

		while (Math.abs(diff) > 1e-10) {
			diff = verlauf2(kreditBetrag, jahresZins, rate) - laufZeit;
			if (diff < 0) {
				max = rate;				
			} else {
				min = rate;
			}
			rate = (max + min) / 2;
		}
		verlauf(kreditBetrag, jahresZins, rate);
		
		return rate;
	}

	private static void verlauf(double kreditBetrag, double jahresZins,
			double rate) {
		double kontoStand = kreditBetrag;
		double monatsZins = jahresZins / 1200;
		int monat = 0;
		double kosten = 0;

		while (kontoStand > rate) {
			double zinsBetrag = kontoStand * monatsZins;
			monat++;
			System.out.println(monat + " | " + kontoStand + " | " + zinsBetrag);
			kontoStand = kontoStand - rate + zinsBetrag;
			kosten += zinsBetrag;
		}
		System.out.println("Es werden " + monat + " Monatsraten à " + rate
				+ " fällig.");
		System.out.println("Schlussrate: " + kontoStand);
		System.out.println("Gesamtkosten: " + kosten + "");
	}

	private static double verlauf2(double kreditBetrag, double jahresZins,
			double rate) {
		double kontoStand = kreditBetrag;
		double monatsZins = jahresZins / 1200;
		int monat = 0;
		double zinsBetrag = 0;
		
		while (kontoStand > rate) {
			monat++;
			zinsBetrag = kontoStand * monatsZins;
			kontoStand = kontoStand - rate + zinsBetrag;		
		}
		
		zinsBetrag = kontoStand * monatsZins;
		return monat + (kontoStand+zinsBetrag)/rate;
	}
}
