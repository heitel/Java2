package hanoi;

public class DiskMover {
	private static final int BEFORE_LARGEST = 0;
	private static final int LARGEST = 1;
	private static final int AFTER_LARGEST = 2;
	
	private int src;
	private int dst;
	private int other;
	private int n;
	private int state;
	private int movesLeft;
	private DiskMover helper;
	
	public DiskMover(int src, int dst, int n) {
		this.src = src;
		this.dst = dst;
		this.n = n;
		other = 3 - (src+dst);
		movesLeft = (1<<n) - 1;
		state = BEFORE_LARGEST;
	}
	
	public Move nextMove() {
		movesLeft--;
		if (n == 1) {
			return new Move(src, dst);
		}
		
		if (state == BEFORE_LARGEST) {
			if (helper == null) {
				helper = new DiskMover(src, other, n-1);
			}
			if (helper.hasMoreMoves()) {
				return helper.nextMove();
			}
			state = LARGEST;
			helper = null;
		}
		
		if (state == LARGEST) {
			state = AFTER_LARGEST;
			return new Move(src, dst);
		}
		
		if (state == AFTER_LARGEST) {
			if (helper == null) {
				helper = new DiskMover(other, dst, n-1);
			}
			if (helper.hasMoreMoves()) {
				return helper.nextMove();
			}
		}
		
		return null;
	}
	
	public boolean hasMoreMoves() {
		if (movesLeft<=0) {
			return false;
		}
		return true;
	}

//	public static void main(String[] arg) {
//		DiskMover dm = new DiskMover(0, 2, 4);
//		while (dm.hasMoreMoves()) {
//			System.out.println(dm.nextMove());
//		}
//	}
}
