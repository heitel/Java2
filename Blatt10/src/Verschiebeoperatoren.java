
public class Verschiebeoperatoren {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int x = 123;
		
		System.out.println("x = " + x + " 0b" + Integer.toBinaryString(x));
		int tmp1 = x>>3; // Ganzzahldivision durch 8
		System.out.println("tmp1 = x>>3 : " + tmp1 + "=0b" + Integer.toBinaryString(tmp1)+" // Ganzzahldiv 8");
		int tmp2 = tmp1<<3; // Multiplikation mit 8
		System.out.println("tmp2 = tmp1<<3 : " + tmp2 + "=0b" + Integer.toBinaryString(tmp2)+" // Mul mit 8");
		int tmp3 = x - tmp2; // entspricht dem Rest der Ganzzahldiv durch 8
		System.out.println("tmp3 = x - tmp2 : " + tmp3 + "=0b" + Integer.toBinaryString(tmp3)+" // Rest");
		int tmp4 = tmp3 << 2; // Multiplikation mit 4
		System.out.println("tmp4 = tmp3 << 2 : " + tmp4+"=0b" + Integer.toBinaryString(tmp4));
		
		int tmp5 = (x%8*4);
		System.out.println("x%8 * 4 = " +tmp5+ " 0b" + Integer.toBinaryString(tmp5));

	}

}
