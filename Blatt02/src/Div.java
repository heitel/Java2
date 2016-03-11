
public class Div {
	public static void main(String[] args) {
		System.out.println("Division mit beliebiger Genauigkeit");
		
		int z1 = 10;
		int z2 = 897;
		
		int s = z1/z2;
		
		System.out.print(s);
		System.out.print(",");
		
		for (int i = 0; i < 60; i++) {
			z1 = z1 * 10;
			s = z1/z2;
			System.out.print(s);
			z1 = z1 % z2;
		}
	}
}
