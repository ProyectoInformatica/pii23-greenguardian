package Application.controller;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import Application.model.RegistroFeedback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerVTecnico {

	 @FXML
	    private Label lblNomVT;

	    @FXML
	    private Button showSensores;

	    @FXML
	    private Button reiniciarSensores;

	    @FXML
	    private Button verFeedback;

	    @FXML
	    private Button logOutTeccnico;
	    
	    public void setLabelText(String text) {
	        lblNomVT.setText("Bienvenid@ "+text);
	    }
	    
	    @FXML
	    void cerrarSesionVT(ActionEvent event) {
	    	try {
	    		Node source = (Node) event.getSource();
	        	Stage stage = (Stage) source.getScene().getWindow();    
	        	stage.close(); 
	        	
	        	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/Inicio.fxml"));
	        	Controller control = new Controller();
	        	loader1.setController(control);
				Parent root1 = loader1.load();
				Stage stage1 = new Stage();
				stage1.setScene(new Scene(root1));
				stage1.initModality(Modality.WINDOW_MODAL);
				stage1.initOwner(((Node) (event.getSource())).getScene().getWindow());
				stage1.show();
	        	
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void verSensores(ActionEvent event) {
	    	try {
	    		Node source = (Node) event.getSource();
		    	Stage stage = (Stage) source.getScene().getWindow();    
		    	stage.close();
	    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/ListaSensoresTecnico.fxml"));
	    		ControllerListaSensoresTecnico control = new ControllerListaSensoresTecnico();
	        	loader1.setController(control);
				Parent root1 = loader1.load();
				Stage stage1 = new Stage();
				stage1.setScene(new Scene(root1));
				stage1.initModality(Modality.WINDOW_MODAL);
				stage1.initOwner(((Node) (event.getSource())).getScene().getWindow());
				stage1.show();
					  
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    private RegistroFeedback leerUltimoFeedback() {
	        Gson gson = new Gson();
	        try (FileReader reader = new FileReader("Data/Feedback.json")) {
	            return gson.fromJson(reader, RegistroFeedback.class);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	    @FXML
	    void verFeedback(ActionEvent event) {
	        RegistroFeedback ultimoFeedback = leerUltimoFeedback();
	        if (ultimoFeedback != null) {
	            Alert alert = new Alert(Alert.AlertType.INFORMATION);
	            alert.setTitle("Información de Último Feedback");
	            alert.setHeaderText(null);
	            alert.setContentText("El último usuario ha sido: " + ultimoFeedback.getUsuario() +
	                                 " a las " + ultimoFeedback.getFechaHora());
	            alert.showAndWait();
	        } else {
	            // Manejar el caso en que no se encuentre información o haya un error
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText(null);
	            alert.setContentText("No se pudo recuperar la información del último feedback.");
	            alert.showAndWait();
	        }
	    }
}
