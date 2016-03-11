package regentropfen;


import java.util.Optional;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class RegentropfenCanvas extends ResizeableCanvas {
	private int anzTropfen = 10000;
	private Font font = new Font("Lucida Console" , 32);

	public RegentropfenCanvas() {
		setOnMouseClicked(e -> click(e));
	}

	private void click(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			TextInputDialog dlg = new TextInputDialog("" + anzTropfen);
			dlg.setTitle("Eingabe");
			dlg.setHeaderText("");
			dlg.setContentText("Anzahl der Regentropfen: ");
			Optional<String> res = dlg.showAndWait();
			if (res.isPresent()) {
				anzTropfen = Integer.parseInt(res.get());
				draw();
			}
		}
	}

	@Override
	protected void draw() {
		GraphicsContext gc = getGraphicsContext2D();
		double w = getWidth();
		double h = getHeight();

		double size = Math.min(w, h);
		double left = 0, top = 0;
		if (w > h) {
			left = (w - size) / 2;
		} else {
			top = (h - size) / 2;
		}
		// Alles löschen
		gc.clearRect(0, 0, w, h);

		gc.setFill(Color.RED);
		gc.fillRect(left, top, size, size);

		gc.setFill(Color.BLUE);
		gc.fillOval(left, top, size, size);

		gc.setStroke(Color.BLACK);
		int count = 0;
		for (int i = 0; i < anzTropfen; i++) {
			double x = getRandom(size);
			double y = getRandom(size);
			double size2 = size / 2;
			if (x * x + y * y <= size2 * size2) {
				count++;
				x += left + size2;
				y += top + size2;
				gc.strokeLine(x, y, x, y);
			}
		}
		double pi = (4.0 * count)/anzTropfen;
		
		String txt = "Pi = " + pi;
		gc.setLineWidth(2);
		gc.setStroke(Color.WHITE);
		gc.setFont(font);
		gc.strokeText(txt, 10, 30);
		gc.setFill(Color.BLACK);
		gc.fillText(txt, 10, 30);

	}

	private double getRandom(double size) {
		return Math.random() * size - size / 2;
	}
}
