package polygon;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PolyPane extends BorderPane {
	private Stage primaryStage;
	private Slider eckenSlider = new Slider(3, 100, 5);
	private PolyCanvas canvas = new PolyCanvas();
	private Slider winkelSlider = new Slider(0, 360, 0);
	
	public PolyPane(Stage primaryStage) {
		this.primaryStage = primaryStage;

		eckenSlider.setOrientation(Orientation.VERTICAL);
		eckenSlider.setBlockIncrement(1);
		eckenSlider.valueProperty().addListener((o, ov, nv) -> doEcken(o, ov, nv));
		
		winkelSlider.setOrientation(Orientation.VERTICAL);
		winkelSlider.setBlockIncrement(1);
		winkelSlider.valueProperty().addListener((o, ov, nv) -> doWinkel(o, ov, nv));

		setLeft(eckenSlider);
		setCenter(canvas);
		setRight(winkelSlider);
		
		updateTitle();
	}

	private void doEcken(ObservableValue<? extends Number> o, Number ov, Number nv) {
		canvas.setEcken(nv.intValue());
		updateTitle();
	}

	private void doWinkel(ObservableValue<? extends Number> o, Number ov, Number nv) {
		canvas.setWinkel(nv.intValue());
		updateTitle();
	}

	private void updateTitle() {
		primaryStage
				.setTitle("Polygon: " + (int) eckenSlider.getValue() + " Ecken " + (int) winkelSlider.getValue() + "°");
	}
}
