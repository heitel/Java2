package aufgabe1c;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class FormController {
	@FXML
	private Button button;
	@FXML
	private CheckBox sahne;
	@FXML
	private RadioButton schwarzwaelder;
	@FXML
	private RadioButton linzer;
	@FXML
	private RadioButton kaese;

	private ToggleGroup tg;

	public FormController() {

	}

	@FXML
	private void initialize() {
		tg = schwarzwaelder.getToggleGroup();
	}

	@FXML
	public void doClick(Event e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(button.getScene().getWindow());
		alert.setTitle("Nachricht");
		alert.setHeaderText("");
		String txt = "Bestellung ";

		RadioButton rb = (RadioButton)tg.getSelectedToggle();
		txt += rb.getText() + " ";

		if (sahne.isSelected()) {
			txt += "mit";
		}
		else {
			txt += "ohne";
		}
		txt += " Sahne!";
		alert.setContentText(txt);
		alert.showAndWait();
	}

}
