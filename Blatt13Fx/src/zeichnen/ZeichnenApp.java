package zeichnen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ZeichnenApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ZeichnenPane root = new ZeichnenPane(primaryStage);
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			Image img = new Image(getClass().getResource(("fx.png")).toExternalForm());
			primaryStage.getIcons().add(img);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
