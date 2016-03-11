package regentropfen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegentropfenApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane(new RegentropfenCanvas());
			Scene scene = new Scene(root, 400, 500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Image img = new Image(getClass().getResourceAsStream(("java.png")));
			primaryStage.getIcons().add(img);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pi mit Regentropfen berechnen");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
