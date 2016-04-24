package kniffel;

import java.util.Arrays;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

public class FormController {
	@FXML
	private GridPane grid;
	@FXML
	private ComboBox<String> combo;
	@FXML
	private TableView<String[]> sp1Tab;
	@FXML
	private TableView<String[]> sp2Tab;
	@FXML
	private Button wuerfeln;
	@FXML
	private Button auswahl;

	public static final String[] FIGUREN = { "1er", "2er", "3er", "4er", "5er", "6er", "3er-Pasch", "4er-Pasch",
			"Full-House", "Kleine Straße", "Große Straße", "Kniffel", "Chance" };

	private WuerfelCanvas wc[] = new WuerfelCanvas[5];
	private CheckBox cb[] = new CheckBox[5];
	private SpielBlatt blatt[] = new SpielBlatt[2];
	private int current = 0;
	private int wurfCount = 0;

	public FormController() {

	}

	@FXML
	private void initialize() {
		for (int i = 0; i < FIGUREN.length; i++) {
			combo.getItems().add(FIGUREN[i]);
		}
		combo.getSelectionModel().select(0);
		combo.setVisibleRowCount(20);

		for (int i = 0; i < wc.length; i++) {
			wc[i] = new WuerfelCanvas();
			grid.add(wc[i], i, 0);
			cb[i] = new CheckBox();
			grid.add(cb[i], i, 1);
		}

		blatt[0] = new SpielBlatt(sp1Tab, "Spieler 1");
		blatt[1] = new SpielBlatt(sp2Tab, "Spieler 2");
		setzeSpieler();
	}

	private void setzeSpieler() {
		int other = 1 - current;
		blatt[current].setBorder(true);
		blatt[other].setBorder(false);
		wurfCount = 0;

		combo.getItems().clear();
		for (int i = 0; i < FIGUREN.length; i++) {
			if (!blatt[current].isSet(i)) {
				combo.getItems().add(FIGUREN[i]);
			}
		}
		combo.getSelectionModel().select(0);

		for (int i = 0; i < wc.length; i++) {
			wc[i].setVisible(false);
		}
		auswahl.setDisable(true);
	}

	@FXML
	private void neuesSpiel() {
		blatt[0].clearAll();
		blatt[1].clearAll();
		current = 0;
		setzeSpieler();	
	}

	@FXML
	private void setSpieler1() {
		String name = abfrageSpielerName(1);
		if (name != null) {
			blatt[0].setName(name);
		}
	}

	@FXML
	private void setSpieler2() {
		String name = abfrageSpielerName(2);
		if (name != null) {
			blatt[1].setName(name);
		}
	}

	private String abfrageSpielerName(int nr) {
		TextInputDialog dlg = new TextInputDialog();
		dlg.setContentText("Name Spieler " + nr);
		dlg.setHeaderText("");
		dlg.setTitle("Abfrage");
		Optional<String> res = dlg.showAndWait();
		if (res.isPresent()) {
			return res.get();
		}
		return null;
	}

	@FXML
	private void beenden() {
		System.exit(0);
	}

	@FXML
	private void wuerfeln() {
		for (int i = 0; i < wc.length; i++) {
			wc[i].setVisible(true);
			if (!cb[i].isSelected()) {
				wc[i].setZahl(getRandom());
			}
		}
		wurfCount++;
		if (wurfCount == 3) {
			wuerfeln.setDisable(true);
		}
		auswahl.setDisable(false);
	}

	private int getRandom() {
		return (int) (Math.random() * 6 + 1);
	}

	@FXML
	private void auswaehlen() {
		String txt = combo.getSelectionModel().getSelectedItem();

		int auswahl = 0;
		for (int i = 0; i < FIGUREN.length; i++) {
			if (txt.equals(FIGUREN[i])) {
				auswahl = i;
				break;
			}
		}

		if (auswahl >= 0 && auswahl <= 5) {
			int punkte = 0;
			for (int i = 0; i < wc.length; i++) {
				int w = wc[i].getZahl() - 1;
				if (w == auswahl) {
					punkte += (w + 1);
				}
			}
			blatt[current].setPunkte(auswahl, punkte);
		}

		if (auswahl == 6) { // 3er Pasch
			if (isPasch(3)) {
				blatt[current].setPunkte(auswahl, summeAlle());
			} else {
				blatt[current].setPunkte(auswahl, 0);
			}
		}

		if (auswahl == 7) { // 4er Pasch
			if (isPasch(4)) {
				blatt[current].setPunkte(auswahl, summeAlle());
			} else {
				blatt[current].setPunkte(auswahl, 0);
			}
		}

		if (auswahl == 8) { // Full House
			if (isPasch(2) && isPasch(3)) {
				blatt[current].setPunkte(auswahl, 25);
			} else {
				blatt[current].setPunkte(auswahl, 0);
			}
		}

		if (auswahl == 9) { // Kleine Straße
			if (isStrasse(4)) {
				blatt[current].setPunkte(auswahl, 30);
			} else {
				blatt[current].setPunkte(auswahl, 0);
			}
		}

		if (auswahl == 10) {// Große Straße
			if (isStrasse(5)) {
				blatt[current].setPunkte(auswahl, 40);
			} else {
				blatt[current].setPunkte(auswahl, 0);
			}
		}

		if (auswahl == 11) {// Kniffel
			if (isPasch(5)) {
				blatt[current].setPunkte(auswahl, 50);
			} else {
				blatt[current].setPunkte(auswahl, 0);
			}
		}

		if (auswahl == 12) {// Chance
			blatt[current].setPunkte(auswahl, summeAlle());
		}
		
		// Spielerwechsel
		current = (current + 1) % 2;
		setzeSpieler();
		wuerfeln.setDisable(false);
		for (int i = 0; i < cb.length; i++) {
			cb[i].setSelected(false);
		}
	}

	private boolean isStrasse(int max) {
		int[] z = new int[wc.length];
		for (int i = 0; i < z.length; i++) {
			z[i] = wc[i].getZahl();
		}
		Arrays.sort(z);
		int len = 1;
		for (int i = 0; i < z.length - 1; i++) {
			if (z[i] + 1 == z[i + 1]) {
				len++;
				if (len >= max) {
					return true;
				}
			}
		}
		return false;
	}

	private int summeAlle() {
		int summe = 0;
		for (int i = 0; i < wc.length; i++) {
			summe += wc[i].getZahl();
		}
		return summe;
	}

	private boolean isPasch(int p) {
		int[] anz = new int[7];
		for (int i = 0; i < 5; i++) {
			anz[wc[i].getZahl()]++;
		}

		for (int i = 1; i < anz.length; i++) {
			if (anz[i] >= p) {
				return true;
			}
		}
		return false;
	}
}
