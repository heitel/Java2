package aufgabe1a;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class FormController {
	@FXML
	private Button button;

	public FormController() {

	}

	@FXML
	public void doClick(Event e) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(button.getScene().getWindow());
		alert.setTitle("Nachricht");
		alert.setHeaderText("");
		alert.setContentText("Schaltfläche getroffen!");
		alert.showAndWait();
	}
}
