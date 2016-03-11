package stoppuhr;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;


public class StoppuhrPane extends BorderPane {
	private Label	display = new Label("00:00:00.000");
	private Button start = new Button("Start");
	private Button stop = new Button("Stopp");
	private Button reset = new Button("Reset");
	private Timeline timer = new Timeline(new KeyFrame(Duration.millis(10), e -> timer(e)));
	private long startTime;
	
	public StoppuhrPane() {
		start.setOnAction(e -> start(e));
		stop.setOnAction(e -> stop(e));
		reset.setOnAction(e -> reset(e));
		
		timer.setCycleCount(Timeline.INDEFINITE);
		
		AnchorPane anchor = new AnchorPane(display);
		AnchorPane.setBottomAnchor(display, 0.0);
		AnchorPane.setTopAnchor(display, 0.0);
		AnchorPane.setLeftAnchor(display, 0.0);
		AnchorPane.setRightAnchor(display, 0.0);
		setCenter(anchor);
		
		HBox box = new HBox(start, stop, reset);
		setBottom(box);	
	}

	
	private void timer(ActionEvent e) {
		long now = System.nanoTime();
		long diff = now - startTime;
		
		LocalTime lt = LocalTime.ofNanoOfDay(diff);
		
		String txt = lt.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
		display.setText(txt);
	}

	private void start(ActionEvent e) {
		startTime = System.nanoTime();
		timer.play();
	}

	private void stop(ActionEvent e) {
		timer.stop();
	}
	
	private void reset(ActionEvent e) {
		timer.stop();
		display.setText("00:00:00.000");
	}
}
