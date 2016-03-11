package stoppuhr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StoppuhrApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			StoppuhrPane root = new StoppuhrPane();
			Scene scene = new Scene(root, 600, 200);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Stoppuhr");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
