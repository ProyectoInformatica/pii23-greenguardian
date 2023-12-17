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

	        // Aplica un estilo CSS a la escena
	        //scene.getStylesheets().add(getClass().getResource("/Application/styles.css").toExternalForm());

	        primaryStage.setScene(scene);
	        primaryStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    /*
	    try {
            // Cargar la interfaz gráfica desde el archivo FXML (Registro.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/view/Registro.fxml"));
            Parent root = loader.load();
            
         // Obtener el controlador asociado a la pantalla de inicio
            Controller inicioController = loader.getController();

            // Configurar la escena
            Scene scene = new Scene(root);

            // Configurar el escenario principal
            primaryStage.setScene(scene);

            // Mostrar la ventana
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}