package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Hello World!");
//			primaryStage.show(); 
			Parent root = FXMLLoader.load(getClass().getResource("Frame.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(Controller.class.getResource("application.css").toExternalForm());
//			primaryStage.setScene(new Scene(root));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Calculation Program");
			primaryStage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}