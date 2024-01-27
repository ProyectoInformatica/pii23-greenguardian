package Application.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;

import Application.model.RegistroFeedback;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerVentanaPrincipal {
	@FXML
	private Label lblNomPagPrincipal;
	
	@FXML
    private Label lblContactar;
	
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
	 	@FXML
	    void onRiegoManualClickedPrin(ActionEvent event) {
	 		if(riegoManual.getText().equals("Apagar riego manual")) {
			 	Alert dialogo1 = new Alert(AlertType.INFORMATION);
		    	dialogo1.setTitle("Riego manual");
		    	dialogo1.setHeaderText(null);
		    	dialogo1.setContentText("Riego manual apagado");
		    	dialogo1.initStyle(StageStyle.UTILITY);
		    	dialogo1.showAndWait();
		    	riegoManual.setText("Encender Riego manual");
		 }else {
			 Alert dialogo1 = new Alert(AlertType.INFORMATION);
		    	dialogo1.setTitle("Riego manual");
		    	dialogo1.setHeaderText(null);
		    	dialogo1.setContentText("Riego manual encendido");
		    	dialogo1.initStyle(StageStyle.UTILITY);
		    	dialogo1.showAndWait();
		    	riegoManual.setText("Apagar riego manual");
		 }
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = now.format(formatter);

	        Usuario usuarioActual = Session.getUsuarioActual();
	        String userName = usuarioActual.getNombre();

	        guardarFeedback(formattedDateTime, userName);
	        Alert alert = new Alert(AlertType.WARNING);
         alert.setTitle("Advertencia");
         alert.setHeaderText(null);
         alert.setContentText("Se ha guardado correctamente");
         alert.showAndWait();
	    }
	    

	    private void guardarFeedback(String fechaHora, String usuario) {
	        Gson gson = new Gson();
	        
	        // Crear un nuevo registro con la fecha y hora actual y el nombre del usuario
	        RegistroFeedback nuevoRegistro = new RegistroFeedback(fechaHora, usuario);

	        // Sobrescribir el archivo Feedback.json con el nuevo registro
	        try (FileWriter writer = new FileWriter("Data/Feedback.json")) {
	            gson.toJson(nuevoRegistro, writer);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    @FXML
	    void abrirVentanaContact(MouseEvent event) {
	    	try {
	    		Node source = (Node) event.getSource();
		    	Stage stage = (Stage) source.getScene().getWindow();    
		    	stage.close();
	    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/VentanaContactTecnico.fxml"));
	    		ControllerVentanaContactTec control = new ControllerVentanaContactTec();
	        	loader1.setController(control);
				Parent root1 = loader1.load();
				Stage stage1 = new Stage();
				stage1.setScene(new Scene(root1));
				stage1.initModality(Modality.WINDOW_MODAL);
				stage1.initOwner(((Node) (event.getSource())).getScene().getWindow());
				stage1.show();
				control.setLabelNombre(usuarioActual.getTecnicoAsignado().getNombre(), usuarioActual.getTecnicoAsignado().getApellido());
				control.setLabelTelf(usuarioActual.getTecnicoAsignado().getTelf());
					  
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	 
	 }


