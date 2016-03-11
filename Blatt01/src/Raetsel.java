
public class Raetsel {

	public static void main(String[] args) {
		String[] input = { "01000101", "01011000", "01010000", "01001111", "00100000",
				"00110010", "00110000",
				"00110000", "00110000", "00100000",
				"01010011", "01010100", "01000101", "01010010", "01001110",
				"01000101", "01001110", "01000110", "01000101", "01001100", "01010011" };
		for (int i = 0; i < input.length; i++) {
			System.out.print((char) Dual2Dez.dual2Dez(input[i]));
		}
	}

}
