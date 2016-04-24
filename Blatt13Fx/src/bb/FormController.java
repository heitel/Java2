package bb;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class FormController {
	@FXML
	private VBox v1;
	@FXML
	private VBox v2;
	@FXML
	private VBox v3;
	@FXML
	private ComboBox<String> operator;
	@FXML
	private Button rechnen;

	private BruchController b1;
	private BruchController b2;
	private BruchController b3;

	public FormController() {
	}

	@FXML
	private void initialize() {
		operator.getItems().addAll("+", "-", "*", "/");
		operator.getSelectionModel().select(0);

		b1 = (BruchController) v1.getUserData();
		b1.setTitle("Bruch 1");
		b2 = (BruchController) v2.getUserData();
		b2.setTitle("Bruch 2");
		b3 = (BruchController) v3.getUserData();
		b3.setTitle("Ergebnis");

		Platform.runLater(() -> b1.setFocus());
	}

	@FXML
	private void rechnen() {
		Bruch a = b1.getBruch();
		Bruch b = b2.getBruch();
		Bruch c = null;

		switch (operator.getSelectionModel().getSelectedItem()) {
		case "+":
			c = a.add(b);
			break;
		case "-":
			c = a.sub(b);
			break;
		case "*":
			c = a.mul(b);
			break;
		case "/":
			c = a.div(b);
			break;

		default:
			break;
		}
		b3.setBruch(c);
	}
}
