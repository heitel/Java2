package fahrkarte;

import java.text.ParseException;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FormController {
	private static final String FORMAT = "0.00 €";
	@FXML
	private Label display;
	@FXML
	private ListView<Ziel> list;
	@FXML
	private CheckBox first;
	@FXML
	private CheckBox bahnCard;
	@FXML
	private Button clear;
	@FXML
	private TextField input;
	@FXML
	private Button kaufen;
	@FXML
	private TextArea output;

	public FormController() {

	}

	public void initialize() {
		display.setId("display");
		list.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> doListSelection(o, ov, nv));
		leseZiele();
	}

	private void doListSelection(ObservableValue<? extends Ziel> o, Ziel ov, Ziel nv) {
		updatePreis();
	}

	private void leseZiele() {
		String txt[] = FileInput.readTextFile("ziele.txt");

		ObservableList<Ziel> data = FXCollections.observableArrayList();
		for (int i = 0; i < txt.length; i++) {
			String tmp[] = txt[i].split("~~~");
			data.add(new Ziel(tmp[0], Double.parseDouble(tmp[1])));
		}

		list.setItems(data);
	}

	@FXML
	public void doCheckBox(ActionEvent e) {
		updatePreis();
	}

	private double updatePreis() {
		double preis = 0;
		Ziel z = list.getSelectionModel().getSelectedItem();
		if (z != null) {
			preis = z.getPreis();
			if (first.isSelected()) {
				preis *= 2;
			}
			if (bahnCard.isSelected()) {
				preis /= 2;
			}
			display.setText(Console.Double2String(FORMAT, preis));
		}
		return preis;
	}

	@FXML
	public void doClear(ActionEvent e) {
		display.setText(Console.Double2String(FORMAT, 0));
		list.getSelectionModel().clearSelection();
		output.setText("");
		input.setText("");
		first.setSelected(false);
		bahnCard.setSelected(false);
	}

	@FXML
	public void doKaufen(ActionEvent e) {
		try {
			double preis = updatePreis();
			double gegeben = Console.String2Double(input.getText());

			if (preis > gegeben) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText("");
				alert.setContentText("Der gegebene Geldbetrag: " + input.getText() + " reicht nicht.");
				alert.showAndWait();
			} else {
				zeigeRausgeld(gegeben - preis);
			}

		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

	private void zeigeRausgeld(double d) {
		final int[] S = { 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1 };
		int betrag = (int)Math.round(d * 100);
		String txt = "Das Rausgeld " + Console.Double2String(FORMAT, d) + " ergibt folgende St�ckelung: \n";

		for (int i = 0; i < S.length; i++) {
			int anz = betrag / S[i];
			if (anz>0) {
				txt += "\t" + anz + "mal " + Console.Double2String(FORMAT, S[i]/100.0) + "\n";
			}
			betrag %= S[i];
		}
		txt += "Vielen Dank - Gute Reise!";
		output.setText(txt);
	}

}
