

public class Triangle {
	private int width;
	
	public Triangle(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		String erg = "";
		if (width == 0) {
			return erg;
		}
		
		for (int i = 0; i < width; i++) {
			erg += "[]";
		}
		erg += "\n";

		Triangle t = new Triangle(width -1);
		return t+erg;
	}

	public int calcAreaIterativ() {
		int sum = 0;
		for (int i = 1; i <= width; i++) {
			sum += i;
		}
		return sum;
	}
	
	public int calcArea() {
		if (width == 0) return 0;
		if (width == 1) return 1;
		Triangle t = new Triangle(width-1);
		return width + t.calcArea();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 50000; i++) {
			Triangle t = new Triangle(i);
			System.out.println(i+" | " + t.calcArea());
		}
		
	}

}
