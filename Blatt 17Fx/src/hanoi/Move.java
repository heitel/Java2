package hanoi;

public class Move {
	public int from;
	public int to;
	
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return "Move [from=" + from + ", to=" + to + "]";
	}
}