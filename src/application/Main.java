package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private static VBox root;
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = (VBox) FXMLLoader.load(getClass().getResource("Sample.fxml"));
			mainScene = new Scene(root);
			primaryStage.setScene(mainScene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static VBox getRoot() {
		return root;
	}
	
}
