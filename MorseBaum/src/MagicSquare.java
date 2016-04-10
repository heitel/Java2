import java.util.Arrays;

public class MagicSquare {

	public static void main(String[] args) {
		int[][] m = createMagic(7);

		for (int i = 0; i < m.length; i++) {
			System.out.println(Arrays.toString(m[i]));
		}
		if (testSquare(m)) {
			System.out.println("m ist magisch");
		}
	}

	private static boolean testSquare(int[][] m) {
		final int sum = lineSum(m, 0);

		for (int i = 1; i < m.length; i++) {
			if (lineSum(m, i) != sum) {
				return false;
			}
		}

		for (int i = 0; i < m[0].length; i++) {
			if (colSum(m, i) != sum) {
				return false;
			}
		}

		if (diaSum(m) != sum) {
			return false;
		}
		
		if (diaSum2(m) != sum) {
			return false;
		}

		return true;
	}

	private static int diaSum2(int[][] m) {
		int sum = 0;

		for (int j = 0; j < m.length; j++) {
			sum += m[j][m.length - 1 - j];
		}

		return sum;
	}

	private static int diaSum(int[][] m) {
		int sum = 0;

		for (int j = 0; j < m.length; j++) {
			sum += m[j][j];
		}

		return sum;
	}

	private static int lineSum(int[][] m, int i) {
		int sum = 0;

		for (int j = 0; j < m[i].length; j++) {
			sum += m[i][j];
		}

		return sum;
	}

	private static int colSum(int[][] m, int i) {
		int sum = 0;

		for (int j = 0; j < m.length; j++) {
			sum += m[j][i];
		}

		return sum;
	}

	private static int[][] createMagic(int n) {
		int[][] m = new int[n][n];
		int x = n / 2;
		int y = n - 1;

		for (int i = 1; i <= n * n; i++) {
			m[y][x] = i;
			x = (x + 1) % n;
			y = (y + 1) % n;
			if (m[y][x] != 0) {
				x = (x - 1 + n) % n;
				y = (y - 2 + n) % n;
			}
		}

		return m;
	}

}
