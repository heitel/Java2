package stoppuhrFXML;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class FormController {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
	private Timeline timer = new Timeline(new KeyFrame(Duration.millis(10), e -> timer(e)));
	private long startTime = 0;
	private long diff = 0;
	@FXML
	private Label display;

	public FormController() {
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	@FXML
	private void start(ActionEvent e) {
		startTime = System.nanoTime() - diff;
		timer.play();
	}

	@FXML
	public void stop(ActionEvent e) {
		timer.stop();
	}

	@FXML
	public void reset(ActionEvent e) {
		diff = 0;
		timer.stop();
		display.setText("00:00:00.000");
	}

	@FXML
	public void timer(ActionEvent e) {
		diff = System.nanoTime() - startTime;
		LocalTime time = LocalTime.ofNanoOfDay(diff);
		display.setText(time.format(FORMATTER));
	}
}
