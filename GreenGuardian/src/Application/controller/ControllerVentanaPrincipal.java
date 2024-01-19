package Application.controller;

import java.io.IOException;

import Application.model.Session;
import Application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerVentanaPrincipal {
	@FXML
	private Label lblNomPagPrincipal;
	
	@FXML
	private Button cantidadAgua;

	@FXML
	private Button offRiegoAutomatico;

	@FXML
	private Button humedadTierra;

	@FXML
	private Button riegoManual;

	@FXML
	private Button temperatura;

	@FXML
	private Button btnLogOutVP;
	
	Usuario usuarioActual = Session.getUsuarioActual();
	
	public void setLabelText(String text) {
		lblNomPagPrincipal.setText("Bienvenid@ "+usuarioActual.getNombre());
    }
	
	 @FXML
	    void cerrarSesionVP(ActionEvent event) {
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
	 void abrirVentanaTemperatura(ActionEvent event) {
	    	try {
	    		Node source = (Node) event.getSource();
		    	Stage stage = (Stage) source.getScene().getWindow();    
		    	stage.close();
	    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/temperatura.fxml"));
	    		ControllerTemperatura control = new ControllerTemperatura();
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
	 @FXML
	 void irCantidadAgua(ActionEvent event) {
	    	try {
	    		Node source = (Node) event.getSource();
		    	Stage stage = (Stage) source.getScene().getWindow();    
		    	stage.close();
	    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/CantidadAgua.fxml"));
	    		ControllerCantidadAgua control = new ControllerCantidadAgua();
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
	 @FXML
	 void irHumedadTierra(ActionEvent event) {
	    	try {
	    		Node source = (Node) event.getSource();
		    	Stage stage = (Stage) source.getScene().getWindow();    
		    	stage.close();
	    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/humedad.fxml"));
	    		ControllerHumedad control = new ControllerHumedad();
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
	 
	 @FXML
	    void apagarRiego(ActionEvent event) {
		 if(offRiegoAutomatico.getText().equals("Apagar riego automatico")) {
			 	Alert dialogo1 = new Alert(AlertType.INFORMATION);
		    	dialogo1.setTitle("Riego Automatico");
		    	dialogo1.setHeaderText(null);
		    	dialogo1.setContentText("Riego automatico apagado");
		    	dialogo1.initStyle(StageStyle.UTILITY);
		    	dialogo1.showAndWait();
		    	offRiegoAutomatico.setText("Encender Riego automatico");
		 }else {
			 Alert dialogo1 = new Alert(AlertType.INFORMATION);
		    	dialogo1.setTitle("Riego Automatico");
		    	dialogo1.setHeaderText(null);
		    	dialogo1.setContentText("Riego automatico encendido");
		    	dialogo1.initStyle(StageStyle.UTILITY);
		    	dialogo1.showAndWait();
		    	offRiegoAutomatico.setText("Apagar riego automatico");
		 }
		 
	    }
	 
	 }


