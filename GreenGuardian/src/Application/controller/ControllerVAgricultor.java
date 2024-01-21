package Application.controller;
import Application.model.RegistroFeedback;
import Application.model.Session;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

public class ControllerVAgricultor{
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
	
	 public void recibirUsuario(Usuario usuario) {
	        setLabelText(usuario.getNombre());
	       
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
	    @FXML
	    void onRiegoManualClickedAgri(ActionEvent event) {
	    	if(riegoManualSob.getText().equals("Apagar riego manual")) {
			 	Alert dialogo1 = new Alert(AlertType.INFORMATION);
		    	dialogo1.setTitle("Riego manual");
		    	dialogo1.setHeaderText(null);
		    	dialogo1.setContentText("Riego manual apagado");
		    	dialogo1.initStyle(StageStyle.UTILITY);
		    	dialogo1.showAndWait();
		    	riegoManualSob.setText("Encender Riego manual");
		 }else {
			 Alert dialogo1 = new Alert(AlertType.INFORMATION);
		    	dialogo1.setTitle("Riego manual");
		    	dialogo1.setHeaderText(null);
		    	dialogo1.setContentText("Riego manual encendido");
		    	dialogo1.initStyle(StageStyle.UTILITY);
		    	dialogo1.showAndWait();
		    	riegoManualSob.setText("Apagar riego manual");
		 }
	    	
	    	
	        LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        String formattedDateTime = now.format(formatter);

	        Usuario usuarioActual = Session.getUsuarioActual();
	        String userName = usuarioActual.getNombre();

	        guardarFeedback(formattedDateTime, userName);
	        
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
}