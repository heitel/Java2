package teilSumme;

public class StackPrinter <T>{
	private int level = 0;
	
	public StackPrinter() {
	}
	
	public void showCall(String name, int arg0, int arg1) {
		leer(level);
		level++;
		System.out.println(name + "(" + arg0 + ", " + arg1 +")");
	}
	
	public void showReturn(T arg0) {
		level--;
		leer(level);
		System.out.println("return: " + arg0);
	}
	
	private void leer(int anz) {
		for (int i = 0; i < anz; i++) {
			System.out.print("  ");
		}
	}

}
