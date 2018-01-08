package application;

public class JuliaWorker extends Worker {
	
	public JuliaWorker(FraktalEngine 	engine, int tNr) {
		super(engine, tNr);
	}

	@Override
	public void run() {
		engine.calcJulia(tNr);
	}
	
}
