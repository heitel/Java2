package wuerfel;

import java.util.Arrays;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class MainPane extends BorderPane {
	private WuerfelCanvas canvas = new WuerfelCanvas();
	private TextField tf = new TextField();
	private Button wuerfeln = new Button("Würfeln");
	private int[] stat = new int[8];
	private TableView<String[]> table = new TableView<String[]>();
	private String[] head = { "Augenzahl", "1", "2", "3", "4", "5", "6", "Gesamt" };
	private String[][] initial = new String[][] { { "Absolut", "0", "0", "0", "0", "0", "0", "0" },
			{ "Relativ", "0,0%", "0,0%", "0,0%", "0,0%", "0,0%", "0,0%", "0,0%" } };

	public MainPane() {
		ObservableList<String[]> data = FXCollections.observableArrayList();
		data.addAll(Arrays.asList(initial));

		for (int i = 0; i < head.length; i++) {
			TableColumn<String[], String> tc = new TableColumn<>(head[i]);
			final int colNo = i;
			tc.setCellValueFactory(cd -> {
				return new SimpleStringProperty(cd.getValue()[colNo]);
			});
			table.getColumns().add(tc);
			tc.setPrefWidth(70);
			if (i > 0) {
				tc.setStyle("-fx-alignment: CENTER_RIGHT");
			}
		}
		table.setPrefHeight(75);
		table.setItems(data);

		wuerfeln.setOnAction(e -> wuerfeln(e));
		tf.setAlignment(Pos.CENTER_RIGHT);
		tf.setEditable(false);

		HBox hb = new HBox(10, new Label("Augenzahl"), tf, wuerfeln);
		hb.setAlignment(Pos.CENTER);
		hb.setPadding(new Insets(10));
		hb.getStyleClass().add("buttonBar");
		setTop(hb);
		setCenter(canvas);
		setBottom(table);
		wuerfeln(null);
	}

	private int getRandom() {
		return (int) (Math.random() * 6 + 1);
	}

	private void wuerfeln(ActionEvent e) {
		int zz = getRandom();
		tf.setText("" + zz);
		stat[zz]++;
		stat[7]++;
		updateTable();
		canvas.setZahl(zz);
	}

	private void updateTable() {
		ObservableList<String[]> list = table.getItems();
		String[] row = list.get(0);
		for (int i = 1; i < row.length; i++) {
			row[i] = "" + stat[i];
		}
		list.set(0, row);

		row = list.get(1);
		for (int i = 1; i < row.length; i++) {
			row[i] = Console.Double2String("0.0%", ((double) stat[i]) / stat[7]);
		}
		list.set(1, row);
	}
}
