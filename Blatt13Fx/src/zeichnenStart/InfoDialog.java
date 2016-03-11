package zeichnenStart;

import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InfoDialog extends Stage {
	private static final String FORMAT = "0";
	private Shape s;
	private ZeichnenCanvas canvas;
	private TextField xpos;
	private TextField ypos;
	private ColorPicker picker;

	public InfoDialog(Shape s, ZeichnenCanvas canvas) {
		this.s = s;
		this.canvas = canvas;

		BorderPane root = new BorderPane();
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10));

		Rectangle2D r = s.getR();
		xpos = new TextField(Console.Double2String(FORMAT, r.getMinX()));
		ypos = new TextField(Console.Double2String(FORMAT, r.getMinY()));
		picker = new ColorPicker(s.getColor());

		grid.add(new Label("x:"), 0, 0);
		grid.add(xpos, 1, 0);
		grid.add(new Label("y:"), 0, 1);
		grid.add(ypos, 1, 1);
		grid.add(new Label("Fläche:"), 0, 2);
		grid.add(new Label(Console.Double2String(FORMAT, s.calcArea())), 1, 2);
		grid.add(new Label("Umfang:"), 0, 3);
		grid.add(new Label(Console.Double2String(FORMAT, s.calcUmfang())), 1, 3);
		grid.add(new Label("Farbe:"), 0, 4);
		AnchorPane anchor = new AnchorPane(picker);
		AnchorPane.setLeftAnchor(picker, 0.0);
		AnchorPane.setRightAnchor(picker, 0.0);
		grid.add(anchor, 1, 4);

		Button close = new Button("Schließen");
		close.setOnAction(e -> close(e));
		Button apply = new Button("Übernehmen");
		apply.setOnAction(e -> apply(e));
		HBox hbox = new HBox(10, close, apply);
		hbox.setPadding(new Insets(10));
		hbox.setAlignment(Pos.CENTER_RIGHT);
		hbox.getStyleClass().add("dialogBar");
		root.setCenter(grid);
		root.setBottom(hbox);

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		setScene(scene);

		Point2D off = canvas.localToScreen(0, 0);
		setResizable(false);
		setX(r.getMaxX() + off.getX());
		setY(r.getMinY() + off.getY());

		setTitle(s.getTitle());
		setAlwaysOnTop(true);
		setOnCloseRequest(e -> close(e));
	}

	public void update() {
		Rectangle2D r = s.getR();
		xpos.setText(Console.Double2String(FORMAT, r.getMinX()));
		ypos.setText(Console.Double2String(FORMAT, r.getMinY()));
	}

	private void close(Event e) {
		s.closeInfoDialog();
		close();
	}

	private void apply(ActionEvent e) {
		try {
			double x = Console.String2Double(xpos.getText());
			double y = Console.String2Double(ypos.getText());

			Rectangle2D oldR = s.getR();
			Rectangle2D newR = new Rectangle2D(x, y, oldR.getWidth(), oldR.getHeight());
			s.setR(newR);
			s.setColor(picker.getValue());
			canvas.draw();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}
}
