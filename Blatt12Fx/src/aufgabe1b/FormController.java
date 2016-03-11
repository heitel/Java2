package aufgabe1b;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class FormController {
	@FXML
	private Button button;
	@FXML
	private CheckBox sahne;

	public FormController() {

	}


	@FXML
	public void doClick(Event e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(button.getScene().getWindow());
		alert.setTitle("Nachricht");
		alert.setHeaderText("");
		String txt = "Bestellung ";
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
