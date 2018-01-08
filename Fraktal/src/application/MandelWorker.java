package application;

public class MandelWorker extends Worker {
	
	public MandelWorker(FraktalEngine	engine, int tNr) {
		super(engine, tNr);
	}

	@Override
	public void run() {
		engine.calcMandel(tNr);
	}
	
}
