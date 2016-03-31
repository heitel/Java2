package baum;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

public class BaumPane extends BorderPane {
	private BaumCanvas canvas = new BaumCanvas();
	private ComboBox<String> typeCombo = new ComboBox<String>();
	private ComboBox<String> levelCombo = new ComboBox<String>();
	private Slider sizeSlider = new Slider(50, 1000, 100);

	public BaumPane() {
		super();
		ToolBar tb = buildToolbar();
		setTop(tb);

		ScrollPane scroller = new ScrollPane(canvas);
		scroller.setPannable(true);
		setCenter(scroller);
		doToolbarCommand();
	}

	private void doToolbarCommand() {
		int level = levelCombo.getSelectionModel().getSelectedIndex() + 1;
		int scale = (int) sizeSlider.getValue();
		int type = typeCombo.getSelectionModel().getSelectedIndex();

		canvas.createTree(level, scale, type);
	}

	private ToolBar buildToolbar() {
		typeCombo.getItems().addAll("Pythagoras", "Sierpinsky", "Von Koch");
		typeCombo.getSelectionModel().select(0);
		typeCombo.setOnAction(e -> doToolbarCommand());

		levelCombo = new ComboBox<>();
		for (int i = 0; i < 18; i++) {
			levelCombo.getItems().add("" + (i + 1));
		}		
		levelCombo.setVisibleRowCount(18);
		levelCombo.getSelectionModel().select(0);
		levelCombo.setOnAction(e -> level(e));

		sizeSlider.valueProperty().addListener((o, ov, nv) -> slider(o, ov, nv));

		Label label = new Label();
		label.setPrefWidth(30);
		label.textProperty().bind(Bindings.format("%5.0f", sizeSlider.valueProperty()));
		Button saveButton = new Button("sichern...");
		saveButton.setOnAction(e->save(e));
		return new ToolBar(new Label("Typ: "), typeCombo, new Label("Level: "), levelCombo, new Label("Größe: "),
				sizeSlider, label, saveButton);
	}

	private void save(ActionEvent e) {
		try {
			FileChooser chooser = new FileChooser();
			chooser.setInitialDirectory(new File("."));
			File f = chooser.showSaveDialog(getScene().getWindow());
			if (f != null) {
				SnapshotParameters params = new SnapshotParameters();
				WritableImage wimg = canvas.snapshot(params, null);
				BufferedImage bimg = SwingFXUtils.fromFXImage(wimg, null);
				ImageIO.write(bimg, "png", f);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void level(ActionEvent e) {
		int level = levelCombo.getSelectionModel().getSelectedIndex() + 1;
		canvas.setLevel(level);
	}

	private void slider(ObservableValue<? extends Number> o, Number ov, Number nv) {
		canvas.setScale(nv.intValue());
	}

}
