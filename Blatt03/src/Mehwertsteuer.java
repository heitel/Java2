
public class Mehwertsteuer {
	private static final double UST_ZYPERN = 15.0; // 15% 
	private static final double UST_DAENEMARK = 25.0; // 25% 
	private static final double UST_DEUTSCHLAND = 19.0; // 19% 
	private static final double UST = UST_DEUTSCHLAND;  
	
	public static void main(String[] args) {
		Console con = new Console();
		double preis = con.readDoubleLoc("Grundpreis: ");
		double ust = (preis * UST)/100;
		System.out.println("MwSt.:" + Console.Double2String("0.00", ust));
		System.out.println("--------------------");
		double endPreis = preis + ust;
		System.out.println("Gesamt: " + Console.Double2String("0.00 €", endPreis));
	}

}
