package hanoiStart;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;

public class MainPane extends BorderPane {
	private HanoiPane hanoiPane = new HanoiPane();
	
	public MainPane() {
		MenuBar bar = buildMenu();
		setTop(bar);
		setCenter(hanoiPane);
	}

	private MenuBar buildMenu() {
		MenuItem newItem = new MenuItem("Neues Spiel");
		newItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCodeCombination.SHORTCUT_DOWN));
		newItem.setOnAction(e -> doNew(e));
		
		MenuItem animate = new MenuItem("Animieren");
		animate.setOnAction(e -> hanoiPane.animate());
		
		MenuItem quit = new MenuItem("Beenden");
		quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.SHORTCUT_DOWN));
		quit.setOnAction(e -> System.exit(0));
		
		Menu menu = new Menu("Datei");
		menu.getItems().addAll(newItem, animate, new SeparatorMenuItem(), quit);
		return new MenuBar(menu);
	}

	private void doNew(ActionEvent e) {
		TextInputDialog dlg = new TextInputDialog(""+hanoiPane.getAnz());
		dlg.setHeaderText("");
		dlg.setContentText("Anzahl der Scheiben?");
		Optional<String> res = dlg.showAndWait();
		if (res.isPresent()) {
			hanoiPane.setAnz(Integer.parseInt(res.get()));
		}
	}
}
