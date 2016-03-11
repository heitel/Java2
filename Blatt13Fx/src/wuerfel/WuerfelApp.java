package wuerfel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WuerfelApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MainPane root = new MainPane();
			Scene scene = new Scene(root, 580, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Computerwürfel");
			Image img = new Image(getClass().getResource("wuerfel.png").toExternalForm());
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
