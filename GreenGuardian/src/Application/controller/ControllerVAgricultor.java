package Application.controller;

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

public class ControllerVAgricultor {
	@FXML
    private Label lblNomVA;

	@FXML
    private Button historialSob;

    @FXML
    private Button riegoManualSob;

    @FXML
    private Button manualsob;

    @FXML
    private Button LogOutSob;
    
	public void setLabelText(String text) {
        lblNomVA.setText("Bienvenid@ "+text);
    }
	
	@FXML
    void cerrarSesionVA(ActionEvent event) {

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
}
