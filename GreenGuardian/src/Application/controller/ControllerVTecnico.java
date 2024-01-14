package Application.controller;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	    public void guardarDatosEnJson() {
	        Gson gson = new Gson();
	        String json = gson.toJson(this);

	        try (FileWriter file = new FileWriter("Data/Sensores.json")) {
	            file.write(json);
	            file.flush();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
