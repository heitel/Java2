package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class FraktalController {
	@FXML
	private BorderPane mainPane;
	@FXML
	private Canvas colorTableCanvas;
	@FXML
	private Menu parameter;

	private FraktalCanvas canvas = new FraktalCanvas();
	private Timeline timer = new Timeline(new KeyFrame(Duration.millis(200), e -> timer(e)));

	public FraktalController() {
		timer.setCycleCount(Timeline.INDEFINITE);
	}

	@FXML
	private void initialize() {
		mainPane.setCenter(canvas);
		drawColorTableCanvas();
		initParameterMenu();
		Platform.runLater(()->doCalc(null));
	}

	public void initParameterMenu() {
		ToggleGroup group = new ToggleGroup();
		for (int i = 0; i < FraktalEngine.CONSTC.length; i += 2) {
			String txt = FraktalEngine.CONSTC[i] + " ";
			if (FraktalEngine.CONSTC[i + 1] >= 0) {
				txt += "+ ";
			} else {
				txt += "- ";
			}
			txt += Math.abs(FraktalEngine.CONSTC[i + 1]) + "i";
			RadioMenuItem item = new RadioMenuItem(txt);
			parameter.getItems().add(item);
			item.setOnAction(e -> doParameter(e));
			group.getToggles().add(item);
			if (i == 14) {
				item.setSelected(true);
			}
		}
		parameter.setDisable(true);
	}

	public void drawColorTableCanvas() {
		Color[] color = canvas.getColorTable();
		GraphicsContext gc = colorTableCanvas.getGraphicsContext2D();
		for (int i = 0; i < color.length; i++) {
			gc.setFill(color[i]);
			gc.fillRect(i, 0, 1, colorTableCanvas.getHeight());
		}
	}

	private void timer(ActionEvent e) {
		canvas.animateColors();
		drawColorTableCanvas();
	}

	private void doParameter(ActionEvent e) {
		RadioMenuItem t = (RadioMenuItem) e.getSource();
		ObservableList<Toggle> list = t.getToggleGroup().getToggles();
		canvas.setParameter(list.indexOf(t));
	}

	@FXML
	private void doQuit(ActionEvent e) {
		System.exit(0);
	}
	
	@FXML
	private void doSave(ActionEvent e) {
		canvas.save();
	}

	@FXML
	private void doCalc(ActionEvent e) {
		canvas.doRecalc(true);
	}
	
	@FXML
	private void doUpdate(ActionEvent e) {
		canvas.draw();
	}

	@FXML
	private void doType(ActionEvent e) {
		RadioMenuItem t = (RadioMenuItem) e.getSource();
		ObservableList<Toggle> list = t.getToggleGroup().getToggles();
		int index = list.indexOf(t);
		canvas.setType(index);
		if (index == 0) {
			parameter.setDisable(true);
		}
		else {
			parameter.setDisable(false);
		}
	}

	@FXML
	private void doAnimate(ActionEvent e) {
		CheckMenuItem animate = (CheckMenuItem)e.getSource();
		if (animate.isSelected()) {
			timer.play();
		}else {
			timer.stop();
		}
	}
	
	@FXML
	private void doEbene(ActionEvent e) {
		RadioMenuItem t = (RadioMenuItem) e.getSource();
		ObservableList<Toggle> list = t.getToggleGroup().getToggles();
		int index = list.indexOf(t);
		canvas.setEbene(index);
	}

}
