package Application.controller;

import java.io.IOException;
import java.util.List;

import Application.model.Session;
import Application.model.Usuario;
import javafx.collections.FXCollections;
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
    
    Usuario usuarioActual = Session.getUsuarioActual();
    
    @FXML
    void initialize() {
        ColNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ColApe.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        ColDNI.setCellValueFactory(new PropertyValueFactory<>("dni"));
        ColTelf.setCellValueFactory(new PropertyValueFactory<>("telf"));

        if (usuarioActual != null && usuarioActual.getRol().equals("Técnico")) {
            List<Usuario> clientesAsignados = usuarioActual.getClientesAsignados();

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
}
