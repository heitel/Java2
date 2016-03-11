
public class Schatzsuche {
	public static void main(String[] args) {
		Koordinate k = new Koordinate(49.2586, 8.8786);
		System.out.print("k: ");
		System.out.println(k);
		System.out.println();
		k.show();
		
		Koordinate dhbw = new Koordinate(49, 28, 27, 8, 32, 3);
		System.out.print("dhbw: ");
		System.out.println(dhbw);
		dhbw.show();
		
		Koordinate goldenGate = new Koordinate(37.820497,-122.478848);
		System.out.println("Golden Gate: " + goldenGate);
		goldenGate.show();
	}
}
