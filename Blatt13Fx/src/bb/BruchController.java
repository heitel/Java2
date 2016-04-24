package bb;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class BruchController {
	@FXML
	private VBox root;
	@FXML
	private TitledPane title;
	@FXML
	private TextField zaehler;
	@FXML
	private TextField nenner;
	
	private Bruch bruch = new Bruch();
	
	public BruchController() {
		
	}

	@FXML
	private void initialize() {
		root.setUserData(this);
	}
	
	@FXML
	private void kuerzen() {
		readData();
		bruch.kuerzen();
		writeData();
	}
	
	private void readData() {
		int z = Integer.parseInt(zaehler.getText());
		int n = Integer.parseInt(nenner.getText());
		bruch.setZaehler(z);
		bruch.setNenner(n);
	}
	
	private void writeData() {
		zaehler.setText(""+bruch.getZaehler());
		nenner.setText(""+bruch.getNenner());
	}
	
	public void setTitle(String s) {
		title.setText(s);
	}

	public Bruch getBruch() {
		readData();
		return bruch;
	}

	public void setBruch(Bruch bruch) {
		this.bruch = bruch;
		writeData();
	}
	
	public void setFocus() {
		zaehler.requestFocus();
	}
}
