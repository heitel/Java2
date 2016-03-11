package bruch;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BruchPane extends Pane {
	private TextField zaehler = new TextField();
	private TextField nenner = new TextField();
	private Button kuerzen = new Button("Kürzen");
	private Bruch bruch = new Bruch();

	public BruchPane(String title) {
		super();
		zaehler.setAlignment(Pos.CENTER_RIGHT);
		nenner.setAlignment(Pos.CENTER_RIGHT);
		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);

		gp.add(zaehler, 0, 0);
		gp.add(nenner, 0, 1);
		AnchorPane anchor = new AnchorPane(kuerzen);
		AnchorPane.setLeftAnchor(kuerzen, 0.0);
		AnchorPane.setRightAnchor(kuerzen, 0.0);
		gp.add(anchor, 0, 2);

		TitledPane tp = new TitledPane(title, gp);
		tp.setCollapsible(false);
		getChildren().add(tp);

		kuerzen.setOnAction(e -> kuerzen(e));
	}

	private void kuerzen(ActionEvent e) {
		einlesen();
		bruch.kuerzen();
		ausgeben();
	}

	private void ausgeben() {
		zaehler.setText("" + bruch.getZaehler());
		nenner.setText("" + bruch.getNenner());
	}

	private void einlesen() {
		try {
			long z = Long.parseLong(zaehler.getText());
			long n = Long.parseLong(nenner.getText());
			bruch.setZaehler(z);
			bruch.setNenner(n);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("");
			alert.setContentText("Zähler und Nenner eines Bruchs dürfen nur aus ganzen Zahlen bestehen!");
			alert.initOwner(getScene().getWindow());
			alert.show();
		}
	}

	public Bruch getBruch() {
		einlesen();
		return (Bruch)bruch.clone();
	}

	public void setBruch(Bruch bruch) {
		this.bruch = bruch;
		ausgeben();
	}

}
