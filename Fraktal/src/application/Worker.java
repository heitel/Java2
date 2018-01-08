package application;

public abstract class Worker extends Thread {
	protected FraktalEngine	engine;
	protected int tNr;

	public Worker(FraktalEngine	engine, int tNr) {
		this.engine = engine;
		this.tNr = tNr;
	}
}
