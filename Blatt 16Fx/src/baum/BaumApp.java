package baum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaumApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BaumPane root = new BaumPane();
			ShowSceneGraph.showSceneGraph(null, root);
			Scene scene = new Scene(root, 650, 550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Baumstrukturen");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
