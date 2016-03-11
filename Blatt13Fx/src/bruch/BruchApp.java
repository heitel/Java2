package bruch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BruchApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MainPane root = new MainPane();
			Scene scene = new Scene(root, 650, 250);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image img = new Image(getClass().getResource(("op.png")).toExternalForm());
			primaryStage.getIcons().add(img);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bruchrechner");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
