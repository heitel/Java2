package polygon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PolygonApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			PolyPane root = new PolyPane(primaryStage);
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
