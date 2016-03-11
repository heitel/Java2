package bruchRechner;

import static org.junit.Assert.*;

import org.junit.Test;


public class BruchUnit {
	private final static long data[][] = {{3,7}, {2,9}, 
		{-11,21}, {2,-3},
		{7,24}, {3, 21}};
	
	@Test
	public void testAdd() {
		for (int i = 0; i < data.length; i+=2) {
			double dec;
			
			Bruch b1 = new Bruch(data[i][0], data[i][1]);
			System.out.println(b1);
			Bruch b2 = new Bruch(data[i+1][0], data[i+1][1]);
			System.out.println(b2);
			
			dec = b1.getDecimalValue() + b2.getDecimalValue();
			Bruch erg = b1.add(b2);
			erg.kuerzen();
			System.out.println(erg + " dec " + dec + " diff " + (erg.getDecimalValue()-dec));
			if (Math.abs(erg.getDecimalValue() - dec)>1e-13) {
				fail("+++");
			}
		}
		System.out.println("+++++++++++++ End ADD ++++++++++++");
	}

	@Test
	public void testSub() {	
		for (int i = 0; i < data.length; i+=2) {
			double dec;
			
			Bruch b1 = new Bruch(data[i][0], data[i][1]);
			System.out.println(b1);
			Bruch b2 = new Bruch(data[i+1][0], data[i+1][1]);
			System.out.println(b2);
			
			dec = b1.getDecimalValue() - b2.getDecimalValue();
			Bruch erg = b1.sub(b2);
			erg.kuerzen();
			System.out.println(erg + " dec " + dec + " diff " + (erg.getDecimalValue()-dec));
			if (Math.abs(erg.getDecimalValue() - dec)>1e-13) {
				fail("+++");
			}
		}
		System.out.println("------------- End SUB --------------");

	}

	@Test
	public void testMul() {
		for (int i = 0; i < data.length; i+=2) {
			double dec;
			
			Bruch b1 = new Bruch(data[i][0], data[i][1]);
			System.out.println(b1);
			Bruch b2 = new Bruch(data[i+1][0], data[i+1][1]);
			System.out.println(b2);
			
			dec = b1.getDecimalValue() * b2.getDecimalValue();
			Bruch erg = b1.mul(b2);
			erg.kuerzen();
			System.out.println(erg + " dec " + dec + " diff " + (erg.getDecimalValue()-dec));
			if (Math.abs(erg.getDecimalValue() - dec)>1e-13) {
				fail("+++");
			}
		}
		System.out.println("------------- End MUL --------------");

	}

	@Test
	public void testDiv() {
		for (int i = 0; i < data.length; i+=2) {
			double dec;
			
			Bruch b1 = new Bruch(data[i][0], data[i][1]);
			System.out.println(b1);
			Bruch b2 = new Bruch(data[i+1][0], data[i+1][1]);
			System.out.println(b2);
			
			dec = b1.getDecimalValue() / b2.getDecimalValue();
			Bruch erg = b1.div(b2);
			erg.kuerzen();
			System.out.println(erg + " dec " + dec + " diff " + (erg.getDecimalValue()-dec));
			if (Math.abs(erg.getDecimalValue() - dec)>1e-13) {
				fail("+++");
			}
		}
		System.out.println("------------- End DIV --------------");
	}

}
