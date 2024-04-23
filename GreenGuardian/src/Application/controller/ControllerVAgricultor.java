package Application.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;

import Application.db.DatabaseConnection;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerVAgricultor {
    @FXML
    private Label lblNomVA;

    @FXML
    private Button historialSob;

    @FXML
    private Button riegoManualSob;
    
    @FXML
    private Button BtnVAgri;

    @FXML
    private Button manualsob;

    @FXML
    private Button riegoA; 

    @FXML
    private Button LogOutSob;

    private boolean riegoAutomaticoActivo = false;
    
    DatabaseConnection bbdd = new DatabaseConnection("jdbc:mariadb://195.235.211.197/piigreenguardian","piigreenguardian","gr33nguard1an","piigreenguardian");
    
	Usuario usuarioActual = Session.getUsuarioActual();


    public void setLabelText(String text) {
        lblNomVA.setText("Bienvenid@ " + text);
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
    void manualVentana(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Manual del Agricultor");
        alert.setHeaderText("Instrucciones de Uso");

        TextArea textArea = new TextArea("1. Verifica los datos de los sensores.\n" +
                "2. Programa el riego automático si es necesario.\n" +
                "3. Usa la opción de riego manual si lo deseas.\n" +
                "4. Revisa las alertas y el historial.\n" +
                "5. Ajusta configuraciones según tus preferencias.\n" +
                "6. Contacta al soporte si necesitas ayuda.");
        textArea.setEditable(false);
        textArea.setWrapText(true);

        VBox vbox = new VBox(textArea);
        alert.getDialogPane().setContent(vbox);

        alert.showAndWait();
    }
    @FXML
    void riegoAutomatico(ActionEvent event) {
        riegoAutomaticoActivo = !riegoAutomaticoActivo; // Esta línea debería funcionar si la variable está declarada correctamente
        String mensaje = riegoAutomaticoActivo ? "Riego automático encendido" : "Riego automático apagado";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Estado del Riego Automático");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    @FXML
    void abrirVentanaContactAgri(ActionEvent event) {
    	
    	String consulta = "SELECT NOMBRE, APELLIDO, TELEFONO FROM USUARIOS u INNER JOIN AGRICULTORES_TECNICOS at ON u.ID =at.ID_TECNICO WHERE at.ID_AGRICULTOR = ?";
    	try (PreparedStatement statement = bbdd.prepareStatement(consulta)){
    		statement.setInt(1, usuarioActual.getId());
    	    ResultSet resultado = statement.executeQuery();
    	    
    	    if (resultado.next()) {
	    		Node source = (Node) event.getSource();
		    	Stage stage = (Stage) source.getScene().getWindow();    
		    	stage.close();
	    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/VentanaContactTecnicoAgri.fxml"));
	    		ControllerVentanaContactTecAgri control = new ControllerVentanaContactTecAgri();
	        	loader1.setController(control);
				Parent root1 = loader1.load();
				Stage stage1 = new Stage();
				stage1.setScene(new Scene(root1));
				stage1.initModality(Modality.WINDOW_MODAL);
				stage1.initOwner(((Node) (event.getSource())).getScene().getWindow());
				control.setConnection(bbdd);
    	        control.setLabelNombre(resultado.getString("NOMBRE"), resultado.getString("APELLIDO"));
    	        control.setLabelTelf(resultado.getString("TELEFONO"));
				stage1.show();
				
    	    }else {
    	        //System.out.println("No se encontró ningún técnico asignado para el cliente actual.");
    	    	Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("No existen técnicos asociados al agricultor");
                alert.showAndWait();
    	    }
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}finally {
            try {
                bbdd.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onRiegoManualClickedAgri(ActionEvent event) {
        if (riegoManualSob.getText().equals("Apagar riego manual")) {
            Alert dialogo1 = new Alert(Alert.AlertType.INFORMATION);
            dialogo1.setTitle("Riego manual");
            dialogo1.setHeaderText(null);
            dialogo1.setContentText("Riego manual apagado");
            dialogo1.initStyle(StageStyle.UTILITY);
            dialogo1.showAndWait();
            riegoManualSob.setText("Encender Riego manual");
        } else {
            Alert dialogo1 = new Alert(Alert.AlertType.INFORMATION);
            dialogo1.setTitle("Riego manual");
            dialogo1.setHeaderText(null);
            dialogo1.setContentText("Riego manual encendido");
            dialogo1.initStyle(StageStyle.UTILITY);
            dialogo1.showAndWait();
            riegoManualSob.setText("Apagar riego manual");
        }

        // Realizar otras operaciones relacionadas con el riego manual
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
