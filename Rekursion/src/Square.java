

public class Square {
	private int width;

	public Square(int width) {
		this.width = width;
	}

	public int calcAreaIterativ() {
		return width * width;
	}

	public int calcArea() {
		if (width == 0)
			return 0;
		if (width == 1)
			return 1;
		Square t = new Square(width - 1);
		return 2 * (width-1) + 1 + t.calcArea();
	}

	public static void main(String[] args) {
		Square t = new Square(100);
		System.out.println(t.calcArea());
	}

}
