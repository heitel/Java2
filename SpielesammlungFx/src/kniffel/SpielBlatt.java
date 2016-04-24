package kniffel;

import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SpielBlatt {
	private TableView<String[]> table;
	private int[] punkte = new int[14];

	public SpielBlatt(TableView<String[]> table, String name) {
		this.table = table;
		
		String[][] sData = new String[14][2];
		for (int i = 0; i < FormController.FIGUREN.length; i++) {
			sData[i][0] = FormController.FIGUREN[i];
		}
		sData[13][0] = "Gesamt";

		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(sData));

		for (int i = 0; i < 2; i++) {
			TableColumn<String[], String> tc = new TableColumn<>(i == 0 ? name : "");
			final int colNo = i;
			tc.setCellValueFactory(cd -> {
				return new SimpleStringProperty(cd.getValue()[colNo]);
			});
			table.getColumns().add(tc);
			tc.setPrefWidth(90);
			tc.setSortable(false);
			if (i > 0) {
				tc.setStyle("-fx-alignment: CENTER_RIGHT; "
						+ "-fx-font-family: 'Comic Sans MS'; "
						+ "-fx-text-fill: blue; -fx-font-size: 12pt");
			}
		}
		table.setItems(data);
	}

	public void setPunkte(int rowIndex, int p) {
		punkte[rowIndex] = p;

		ObservableList<String[]> data = table.getItems();
		String[] row = data.get(rowIndex);
		row[1] = "" + p;
		data.set(rowIndex, row);
		calcBonus();
	}

	private void calcBonus() {
		int sum = 0;
		int bonus = 0;
		for (int i = 0; i < 6; i++) {
			sum += punkte[i];
		}
		if (sum >= 63) {
			bonus = 35;
			table.getColumns().get(1).setText("" + 35);
			sum += bonus;
		}
		for (int i = 6; i < 13; i++) {
			sum += punkte[i];
		}
		
		punkte[13] = sum;
		ObservableList<String[]> data = table.getItems();
		String[] row = data.get(13);
		row[1] = "" + sum;
		data.set(13, row);
	}

	public void setBorder(boolean on) {
		if (on) {
			table.setStyle("-fx-border-width: 3px; -fx-border-color: cornflowerblue;");
		} else {
			table.setStyle("");
		}
	}

	public boolean isSet(int rowIndex) {
		ObservableList<String[]> data = table.getItems();
		String[] row = data.get(rowIndex);
		if (row[1] == null) {
			return false;
		}
		
		return true;
	}

	public void setName(String name) {
		table.getColumns().get(0).setText(name);
	}

	public void clearAll() {
		ObservableList<String[]> data = table.getItems();
		for (int i = 0; i < data.size(); i++) {
			String[] row = data.get(i);
			row[1] = null;
			data.set(i, row);
		}
	}
}
