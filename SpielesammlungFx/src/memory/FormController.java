package memory;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class FormController {
	private final static String[] IMG = { "Alert.png", "AmcSafeflop.png", "ball.png", "BeEntertainment.png",
			"BeHome.png", "Circium-Japonicum-icon.gif", "MacOSX.png", "Messenger.png", "Morpho-Absoloni.png",
			"N5-icon.gif", "sea-horse-icon.gif", "swirl.png", "Time.png", "tree.png", "X-mid.png" };
	@FXML
	private GridPane spielFeld;
	@FXML
	private TextField spieler1Pkt;
	@FXML
	private TextField spieler2Pkt;
	@FXML
	private Label zug1;
	@FXML
	private Label zug2;
	@FXML
	private TextField meldung;

	private MemoryButton[] button = new MemoryButton[30];
	private Timeline timer = new Timeline(new KeyFrame(Duration.millis(3000), e -> timer(e)));
	private int clickCount = 0;
	private MemoryButton b1, b2;
	private int current = 0;
	private int pkt1, pkt2;
	private Media media1, media2;

	public FormController() {
		media1 = new Media(getClass().getResource("/snd/Brett.mp3").toExternalForm());
		media2 = new Media(getClass().getResource("/snd/TOOT.mp3").toExternalForm());
	}

	@FXML
	private void initialize() {
		spielFeld.setEffect(new DropShadow());
		neuesSpiel();
	}

	private void shuffle() {
		int len = button.length;
		for (int i = 0; i < len; i++) {
			int zz1 = getRandom(len);
			int zz2 = getRandom(len);
			MemoryButton tmp = button[zz1];
			button[zz1] = button[zz2];
			button[zz2] = tmp;
		}
	}

	private int getRandom(int max) {
		return (int) (Math.random() * max);
	}

	@FXML
	private void neuesSpiel() {
		pkt1 = 0;
		pkt2 = 0;

		spielFeld.getChildren().clear();
		for (int i = 0; i < IMG.length; i++) {
			MemoryButton b = new MemoryButton("/img/" + IMG[i]);
			b.setOnAction(e -> click(e));
			button[2 * i] = b;
			b = new MemoryButton("/img/" + IMG[i]);
			b.setOnAction(e -> click(e));
			button[2 * i + 1] = b;
		}
		shuffle();

		int k = 0;
		for (int i = 0; i < spielFeld.getRowConstraints().size(); i++) {
			for (int j = 0; j < spielFeld.getColumnConstraints().size(); j++) {
				spielFeld.add(button[k], j, i);
				k++;
			}
		}
		spieler1Pkt.setText("" + pkt1);
		spieler2Pkt.setText("" + pkt2);
	}

	@FXML
	private void beenden() {
		System.exit(0);
	}

	@FXML
	private void click(ActionEvent e) {
		MemoryButton button = (MemoryButton) e.getSource();
		if (clickCount == 0) {
			b1 = button;
			b1.showImage();
			timer.play();
			clickCount = 1;
			return;
		}
		
		if (clickCount == 1) {
			b2 = button;
			b2.showImage();
			if (b1.equals(b2)) {
				timer.stop();
				clickCount = 0;
				b1.setVisible(false);
				b2.setVisible(false);
				meldung.setText("Zwei gleiche Karten aufgedeckt");
				if (current == 0) {
					pkt1++;
				} else {
					pkt2++;
				}
				if (pkt1 + pkt2 == 15) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("");
					alert.setTitle("Spielende");
					if (pkt1 > pkt2) {
						alert.setContentText("Spieler 1 hat gewonnen");
					} else {
						alert.setContentText("Spieler 2 hat gewonnen");
					}
					alert.showAndWait();
				}
			} else {
				clickCount = 2;
				meldung.setText("Die Karten waren ungleich");
			}
		}

		spieler1Pkt.setText("" + pkt1);
		spieler2Pkt.setText("" + pkt2);
	}

	private void timer(ActionEvent e) {
		clickCount = 0;
		if (b1 != null) {
			b1.hideImage();
		}
		if (b2 != null) {
			b2.hideImage();
		}
		if (current == 0) {
			zug1.setText("");
			zug2.setText(">");
			current = 1;
			new MediaPlayer(media1).play();
		} else {
			zug1.setText(">");
			zug2.setText("");
			current = 0;
			new MediaPlayer(media2).play();
		}
		b1 = null;
		b2 = null;
		meldung.setText("Zeit zum Umdrehen ist abgelaufen");
		
	}
}
