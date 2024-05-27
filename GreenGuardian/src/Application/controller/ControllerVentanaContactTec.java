package Application.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Application.db.DatabaseConnection;
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

public class ControllerVentanaContactTec {
	@FXML
    private Label lblNombre;

    @FXML
    private Label lblTelefono;

    @FXML
    private Button btnVolver;
    
    @FXML
    private Button btnChat;
    
    private DatabaseConnection bbdd = new DatabaseConnection("jdbc:mariadb://195.235.211.197/piigreenguardian","piigreenguardian","gr33nguard1an","piigreenguardian");
    
    Usuario usuarioActual = Session.getUsuarioActual();
    
    /*public void setConnection(DatabaseConnection connection) {
		this.bbdd = connection;
		
	}*/

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
		}finally {
            try {
                bbdd.closeConnection();
                //System.out.println("Base de datos cerrada para volver a la ventana principal");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    	
    }
    
    @FXML
    void abrirChat(ActionEvent event) {
    	try {
   		 if (bbdd == null|| bbdd.conn == null || bbdd.conn.isClosed()) {
                System.err.println("Error: La conexión a la base de datos es nula.");
                return;
            }
            
            String consulta = "SELECT ID_TECNICO FROM CLIENTES_TECNICOS WHERE ID_CLIENTE = ?";

            try (PreparedStatement statement = bbdd.prepareStatement(consulta)) {
           	 statement.setInt(1, usuarioActual.getId());
           	    ResultSet resultado = statement.executeQuery();

           	    
           	    if (resultado.next()) {
           	    	
           	    	int idRec = resultado.getInt("ID_TECNICO");
           	    	DatabaseConnection newBbdd = new DatabaseConnection("jdbc:mariadb://195.235.211.197/piigreenguardian", "piigreenguardian", "gr33nguard1an", "piigreenguardian");
           	        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/Chat.fxml"));
           	        ControllerChat control = new ControllerChat();
           	        loader1.setController(control);
           	        Parent root1 = loader1.load();
           	        Stage stage1 = new Stage();
           	        stage1.setScene(new Scene(root1));
           	        stage1.initModality(Modality.WINDOW_MODAL);
           	        stage1.initOwner(((Node) (event.getSource())).getScene().getWindow());   
           	        control.setConnection(newBbdd);
           	        control.setId(idRec);
           	        stage1.setOnCloseRequest(e -> control.close());
           	        stage1.show();
           	    } else {
           	        //System.out.println("No se encontró ningún técnico asignado para el cliente actual.");
           	    	Alert alert = new Alert(AlertType.INFORMATION);
                       alert.setTitle("Advertencia");
                       alert.setHeaderText(null);
                       alert.setContentText("No existen técnicos asociados al cliente");
                       alert.showAndWait();
           	    }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }/*finally {
            try {
                bbdd.closeConnection();
                System.out.println("Base de datos cerrada despues de abrir controllerChat");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
    }

	public void setLabelNombre(String nombre, String apellido) {
		lblNombre.setText("Nombre Técnico Asignado: " + nombre + " " + apellido);
		
	}

	public void setLabelTelf(String telf) {
		lblTelefono.setText("Num telfono: " + telf);
		
	}
	
	public void close() {
        try {
            if (bbdd != null) {
                bbdd.closeConnection();
                //System.out.println("Conexión a la base de datos cerrada en ControllerVentanaContactTec");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
