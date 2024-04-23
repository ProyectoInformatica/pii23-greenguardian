package Application.controller;

import java.io.IOException;

import Application.db.DatabaseConnection;
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

public class ControllerVentanaContactTec {
	@FXML
    private Label lblNombre;

    @FXML
    private Label lblTelefono;

    @FXML
    private Button btnVolver;
    
    private DatabaseConnection bbdd ;
    
    Usuario usuarioActual = Session.getUsuarioActual();
    
    public void setConnection(DatabaseConnection connection) {
		this.bbdd = connection;
		
	}

    @FXML
    void volverMenu(ActionEvent event) {
    	try {
    		Node source = (Node) event.getSource();
	    	Stage stage = (Stage) source.getScene().getWindow();    
	    	stage.close();
    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/menuPrincipal.fxml"));
    		ControllerVentanaPrincipal control = new ControllerVentanaPrincipal();
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
		lblNombre.setText("Nombre Técnico Asignado: " + nombre + " " + apellido);
		
	}

	public void setLabelTelf(String telf) {
		lblTelefono.setText("Num telfono: " + telf);
		
	}

	
}
