package Application.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Application.db.Connection;
import Application.model.Session;
import Application.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerListClientesTecnico {
	@FXML
    private TableView<Usuario> tblClientes;

    @FXML
    private TableColumn<Usuario, String> ColNom;

    @FXML
    private TableColumn<Usuario, String> ColApe;

    @FXML
    private TableColumn<Usuario, String> ColDNI;

    @FXML
    private TableColumn<Usuario, String> ColTelf;

    @FXML
    private Button btnVolver;
    
    Connection bbdd = new Connection("SQLite/PRUEBA.db");
    
    Usuario usuarioActual = Session.getUsuarioActual();

    @FXML
    void initialize() {
        ColNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColApe.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        ColTelf.setCellValueFactory(new PropertyValueFactory<>("telf"));

        if (usuarioActual != null && usuarioActual.getRol().equals("Técnico")) {
            List<Usuario> clientesAsignados = obtenerClientesAsignados(usuarioActual.getId());

            if (clientesAsignados != null) {
                tblClientes.setItems(FXCollections.observableArrayList(clientesAsignados));
            } else {
                // Si el técnico no tiene clientes asignados, mostrar una alerta
                mostrarAlerta("Información", "El técnico no tiene clientes asignados.");
            }
        } else {
            // Manejar el caso en que no se pueda obtener la información del técnico
            mostrarAlerta("Error", "No se pudo obtener la información del técnico.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void volverMenuPrincipal(ActionEvent event) {
    	try {
    		Node source = (Node) event.getSource();
	    	Stage stage = (Stage) source.getScene().getWindow();    
	    	stage.close();
    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/menuTecnico.fxml"));
    		ControllerVTecnico control = new ControllerVTecnico();
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
    
    private List<Usuario> obtenerClientesAsignados(int idTecnico) {
        // Consulta SQL para obtener los IDs de los clientes asignados al técnico
        String sql = "SELECT u.* " +
                     "FROM CLIENTES_TECNICOS ct " +
                     "JOIN USUARIOS u ON ct.ID_CLIENTE = u.ID " +
                     "WHERE ct.ID_TECNICO = ?";

        try (PreparedStatement pstmt = bbdd.prepareStatement(sql)) {
            pstmt.setInt(1, idTecnico);
            ResultSet rs = pstmt.executeQuery();

            ObservableList<Usuario> clientes = FXCollections.observableArrayList();

            while (rs.next()) {
                String nombre = rs.getString("NOMBRE");
                String apellido = rs.getString("APELLIDO");
                String dni = rs.getString("DNI");
                String telefono = rs.getString("TELEFONO");

                Usuario cliente = new Usuario(nombre, apellido, dni, telefono);
                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
