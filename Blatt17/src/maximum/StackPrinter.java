package maximum;

public class StackPrinter {
	private int level = 0;
	
	public StackPrinter() {
	}
	
	public void showCall(String name, int arg0, int arg1) {
		leer(level);
		level++;
		System.out.println(name + "(" + arg0 + ", " + arg1 +")");
	}
	
	public void showReturn(int arg0) {
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
