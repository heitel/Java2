package digitaleBildverarbeitung;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BildverarbeitungsApp extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	//		setUserAgentStylesheet(STYLESHEET_CASPIAN);
	
			BildverarbeitungsPane root = new BildverarbeitungsPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Digitale Bildverarbeitung");
			primaryStage.setX(100);
			primaryStage.setY(100);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
