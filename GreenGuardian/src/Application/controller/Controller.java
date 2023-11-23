package Application.controller;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	@FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtConstra;

    @FXML
    private Label lblOlvideContra;

    @FXML
    private Button btnInciarSesion;

    @FXML
    private Button btnRegistrar;
    
    @FXML
    private Button btnVolverR;
    
    @FXML
    void abrirPantalla(MouseEvent event) {
    	
    }

    
    @FXML
    void abrirRegistro(ActionEvent event) {
    	try {
    		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/Registro.fxml"));
        	Controller control = new Controller();
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
    void cerrarRegsitro(ActionEvent event) {
    	Node source = (Node) event.getSource();
    	Stage stage = (Stage) source.getScene().getWindow();    
    	stage.close();                           
    	}
}
