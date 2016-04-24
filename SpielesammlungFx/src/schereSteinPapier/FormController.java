package schereSteinPapier;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FormController {
	@FXML
	private Label spielerName;
	@FXML
	private Label spielerStand;
	@FXML
	private Label runde;
	@FXML
	private Label spielerAuswahl;
	@FXML
	private Label computerAuswahl;
	@FXML
	private Button schereButton;
	@FXML
	private Button steinButton;
	@FXML
	private Button papierButton;

	private Image[] imgKlein = new Image[3];
	private Image[] imgGross = new Image[3];
	private String[] imgNameG = {"schere.png", "stein.png", "papier.png"};
	private String[] imgNameK = {"schere1.png", "stein1.png", "papier1.png"};

	private int punkteSpieler = 0;
	private int punkteComputer = 0;
	private int rz = 0;

	private static final int[][] tab = {{0, 0, 0, 0},
			{0, 1, 0, 1},
			{0, 2, 1, 0},
			{1, 0, 1, 0},
			{1, 1, 0, 0},
			{1, 2, 0, 1},
			{2, 0, 0, 1},
			{2, 1, 1, 0},
			{2, 2, 0, 0}};

	public FormController() {
		for (int i = 0; i < imgGross.length; i++) {
			imgKlein[i] = new Image(getClass().getResource("/imgSSP/" + imgNameK[i]).toExternalForm());
			imgGross[i] = new Image(getClass().getResource("/imgSSP/" + imgNameG[i]).toExternalForm());
		}
	}

	@FXML
	private void initialize() {
		TextInputDialog dlg = new TextInputDialog();
		dlg.setHeaderText("");
		dlg.setContentText("Wie heißen Sie?");
		Optional<String> res = dlg.showAndWait();
		if (res.isPresent()) {
			spielerName.setText(res.get());
		}

		schereButton.setGraphic(new ImageView(imgGross[0]));
		steinButton.setGraphic(new ImageView(imgGross[1]));
		papierButton.setGraphic(new ImageView(imgGross[2]));
		spielerAuswahl.setGraphic(new ImageView(imgKlein[0]));
		int comp = getRandom();
		computerAuswahl.setGraphic(new ImageView(imgKlein[comp]));

	}

	@FXML
	private void clickSchere() {
		next(0);
	}

	@FXML
	private void clickStein() {
		next(1);
	}

	@FXML
	private void clickPapier() {
		next(2);
	}

	@FXML
	private void neuesSpiel() {
		rz = 0;
		runde.setText("Runde: " + rz);
		punkteSpieler = 0;
		punkteComputer = 0;
		spielerStand.setText(punkteSpieler+":"+punkteComputer);
		schereButton.setDisable(false);
		steinButton.setDisable(false);
		papierButton.setDisable(false);
	}

	@FXML
	private void beenden() {
		System.exit(0);
	}

	private void next(int z) {
		rz++;
		runde.setText("Runde: " + rz);
		spielerAuswahl.setGraphic(new ImageView(imgKlein[z]));
		int comp = getRandom();
		computerAuswahl.setGraphic(new ImageView(imgKlein[comp]));
		punkteSpieler+=tab[z*3+comp][2];
		punkteComputer+=tab[z*3+comp][3];
		spielerStand.setText(punkteSpieler+":"+punkteComputer);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("");
		if (punkteSpieler == 3) {
			alert.setContentText("Bravo, Du hast gewonnen!");
			alert.showAndWait();
			schereButton.setDisable(true);
			steinButton.setDisable(true);
			papierButton.setDisable(true);

		}
		if (punkteComputer==3) {
			alert.setContentText("Schade Du hast verloren!");
			alert.showAndWait();
			schereButton.setDisable(true);
			steinButton.setDisable(true);
			papierButton.setDisable(true);
		}
	}

	private int getRandom() {
		return (int)(Math.random()*3);
	}
}
