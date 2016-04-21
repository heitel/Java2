import java.util.Arrays;

public class MagicSquare {

	public static void main(String[] args) {
		int n = 7;
		int[][] m = createMagic(n);
		show(m);
		System.out.println(isMagic(m));
	}

	private static boolean isMagic(int[][] m) {
		int sum = sumLine(m, 0);
		
		for (int i = 1; i < m.length; i++) {
			if (sum != sumLine(m, i)) {
				return false;
			}
		}
		
		for (int i = 0; i < m.length; i++) {
			if (sum != sumCol(m, i)) {
				return false;
			}
		}
		
		if (sum != sumHD(m)) {
			return false;
		}
		if (sum != sumND(m)) {
			return false;
		}
		return true;
	}

	private static int sumND(int[][] m) {
		int sum = 0;
		
		for (int i = 0; i < m.length; i++) {
			sum += m[i][m.length-1-i];
		}
		
		return sum;
	}

	private static int sumHD(int[][] m) {
		int sum = 0;
		
		for (int i = 0; i < m.length; i++) {
			sum += m[i][i];
		}
		
		return sum;
	}

	private static int sumLine(int[][] m, int n) {
		int sum = 0;
		
		for (int i = 0; i < m[n].length; i++) {
			sum += m[n][i];
		}
		
		return sum;
	}
	
	private static int sumCol(int[][] m, int n) {
		int sum = 0;
		
		for (int i = 0; i < m.length; i++) {
			sum += m[i][n];
		}
		
		return sum;
	}

	private static void show(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			System.out.println(Arrays.toString(m[i]));
		}
	}

	private static int[][] createMagic(int n) {
		int[][] m = new int[n][n];
		int i = n-1;
		int j = n/2;
		for (int x = 1; x <= n*n; x++) {
			if (m[i][j]!=0) {
				i =(i-2+n)%n;
				j=(j-1+n)%n;
			}
			m[i][j] = x;
			i = (i+1)%n;
			j = (j+1)%n;
		}
		return m;
	}

}
