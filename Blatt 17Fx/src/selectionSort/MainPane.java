package selectionSort;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class MainPane extends BorderPane {
	private static final String TXT = "DHBWMANNHEIM";
	private Button step = new Button("Step");
	private Button run = new Button("Run");
	private Button reset = new Button("Reset");
	private SortCanvas canvas = new SortCanvas(TXT);
	private SelectionSort sorter = new SelectionSort(TXT);
	private Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), e->timer()));
	
	public MainPane() {
		HBox bar = new HBox(step, run, reset);
		setCenter(canvas);
		setBottom(bar);
		
		step.setOnAction(e -> step());
		run.setOnAction(e -> run());
		reset.setOnAction(e -> reset());
		
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	private void timer() {
		step();
	}

	private void step() {
		if (sorter.step()) {
			canvas.setData(sorter.getData());
		}
		else {
			step.setDisable(true);
			run.setDisable(true);
			timer.stop();
		}
	}
	
	private void run() {
		timer.play();
	}
	
	private void reset() {
		timer.stop();
		step.setDisable(false);
		run.setDisable(false);
		canvas.setData(TXT);
		sorter.setData(TXT);
	}
}
