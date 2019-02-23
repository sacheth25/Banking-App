package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane mainPane = (Pane)FXMLLoader.load(Main.class.getResource("/Users/Sacheth/eclipse-workspace/Banking/srcMainPanel.fxml"));
			//Scene mainScene = new Scene(mainPane);
			primaryStage.setScene(new Scene(mainPane));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
