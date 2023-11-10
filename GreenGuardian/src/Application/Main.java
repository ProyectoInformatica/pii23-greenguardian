package Application;

import java.io.IOException;

import Application.controller.Controller;

public class Main extends Application{

	@Override
	@Override
	public void start(Stage primaryStage) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/view/Inicio.fxml"));
	        Controller control = new Controller();
	        loader.setController(control);
	        Parent root = loader.load();

	        Scene scene = new Scene(root);

	        // Aplica un estilo CSS a la escena
	        scene.getStylesheets().add(getClass().getResource("/Application/styles.css").toExternalForm());

	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}