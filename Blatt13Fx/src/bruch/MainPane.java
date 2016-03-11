package bruch;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainPane extends BorderPane {
	private BruchPane b1 = new BruchPane("Bruch 1");
	private ComboBox<String> combo = new ComboBox<>();
	private BruchPane b2 = new BruchPane("Bruch 2");
	private BruchPane erg = new BruchPane("Ergebnis");

	public MainPane() {
		combo.getItems().addAll("+", "-", "*", "/");
		combo.getSelectionModel().selectFirst();

		GridPane gp = new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		gp.add(b1, 0, 0);
		gp.add(combo, 1, 0);
		gp.add(b2, 2, 0);
		gp.add(new Label("="), 3, 0);
		gp.add(erg, 4, 0);

		gp.setAlignment(Pos.CENTER);
		setCenter(gp);

		Button rechnen = new Button("Rechnen");
		rechnen.setOnAction(e -> rechnen(e));
		HBox hb = new HBox(rechnen);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(10));
		hb.getStyleClass().add("buttonBar");

		setBottom(hb);
	}

	private void rechnen(ActionEvent e) {
		Bruch bruch1 = b1.getBruch();
		Bruch bruch2 = b2.getBruch();
		Bruch bruch3 = null;
		bruch1.kuerzen();
		bruch2.kuerzen();
		
		String op = combo.getSelectionModel().getSelectedItem();
		switch (op) {
		case "+":
			bruch3 = bruch1.add(bruch2);
			break;
		case "-":
			bruch3 = bruch1.sub(bruch2);
			break;
		case "*":
			bruch3 = bruch1.mul(bruch2);
			break;
		case "/":
			bruch3 = bruch1.div(bruch2);
			break;
		}
		bruch3.kuerzen();
		erg.setBruch(bruch3);
	}

}
