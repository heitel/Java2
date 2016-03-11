package paint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PaintApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			PaintPane root = new PaintPane();
			Scene scene = new Scene(root, 900, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image img = new Image(getClass().getResourceAsStream(("paint.jpg")));
			primaryStage.getIcons().add(img);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ein Malprogramm");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
