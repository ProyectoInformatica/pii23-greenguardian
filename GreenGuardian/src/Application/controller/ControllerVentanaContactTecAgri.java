package Application.controller;

import java.io.IOException;

import Application.db.Connection;
import Application.model.Session;
import Application.model.Usuario;
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

public class ControllerVentanaContactTecAgri {
	@FXML
    private Label lblNombreAgri;

    @FXML
    private Label lblTelefonoAgri;

    @FXML
    private Button btnVolverAgri;
    
    private Connection bbdd ;

    
    Usuario usuarioActual = Session.getUsuarioActual();
    
    public void setConnection(Connection connection) {
		this.bbdd = connection;
		
	}

    @FXML
    void volverMenuAgri(ActionEvent event) {
    	try {
    		Node source = (Node) event.getSource();
	    	Stage stage = (Stage) source.getScene().getWindow();    
	    	stage.close();
    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/menuAgricultor.fxml"));
    		ControllerVAgricultor control = new ControllerVAgricultor();
        	loader1.setController(control);
			Parent root1 = loader1.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(root1));
			stage1.initModality(Modality.WINDOW_MODAL);
			stage1.initOwner(((Node) (event.getSource())).getScene().getWindow());
			stage1.show();
			control.setLabelText(usuarioActual.getNombre());
				  
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    public void setLabelNombre(String nombre, String apellido) {
        lblNombreAgri.setText("Nombre Técnico Asignado: " + nombre + " " + apellido);
    }


	public void setLabelTelf(String telf) {
		lblTelefonoAgri.setText("Teléfono: "+telf);
		
	}
	
}
