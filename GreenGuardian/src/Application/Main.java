package Application;

import java.io.IOException;

import Application.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/view/Inicio.fxml"));
	        Controller control = new Controller();
	        loader.setController(control);
	        Parent root = loader.load();

	        Scene scene = new Scene(root);

	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}