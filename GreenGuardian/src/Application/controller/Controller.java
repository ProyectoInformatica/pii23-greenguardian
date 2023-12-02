package Application.controller;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private Button btnRegistrarmeR;
    
    @FXML
    private Button btnVolverR;
    
    @FXML
    private TextField txtIntroNombre;

    @FXML
    private TextField txtIntroApellido;

    @FXML
    private TextField txtIntroDni;

    @FXML
    private TextField txtIntroTelf;

    @FXML
    private TextField txtIntroContr;
    
    @FXML
    private TextField txtReintroContr;
    
    @FXML
    void abrirPantalla(MouseEvent event) {
    	
    }

    //Abrir registro y cerrar ventan login
    @FXML
    void abrirRegistro(ActionEvent event) {
    	try {
    		Node source = (Node) event.getSource();
	    	Stage stage = (Stage) source.getScene().getWindow();    
	    	stage.close();
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
    
    //Cerrar registro y abrir ventana login
    @FXML
    void cerrarRegsitro(ActionEvent event) {
    	
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
    
    //Registrar usuario
    @FXML
    void registrarUsuario(ActionEvent event) {
    	
    	String nombre;
    	String apellido;
    	String dni;
    	String telf;
    	String contra;
    	String repeatContra;
    	
    	//Recoger datos de los texfield 
    	nombre= txtIntroNombre.getText();
    	apellido = txtIntroApellido.getText();
    	dni = txtIntroDni.getText();
    	telf = txtIntroTelf.getText();
    	contra = txtIntroContr.getText();
    	repeatContra = txtReintroContr.getText();
    	
    	//Creo lista y leo Json
    	ArrayList<Usuario> listaUsuarios = leerJson();
    	
    	//Creo el usuario
    	Usuario u = new Usuario(nombre, apellido, dni, telf, contra);
    	
    	u.toString();
    	
    	//Añado el usuario al la lista
    	listaUsuarios.add(u);
    	
    	//Añado lista de usuarios al Json
    	
    	escribirJson(listaUsuarios);
    	
    	//Cerrar Ventana Registro y abrir Inicio
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
    	
    	//Ventana Info
    	Alert dialogo = new Alert(AlertType.INFORMATION);
    	dialogo.setTitle("Usuario Registrado");
    	dialogo.setHeaderText(null);
    	dialogo.setContentText("Usuario registrado correctamente");
    	dialogo.initStyle(StageStyle.UTILITY);
    	dialogo.showAndWait();
    }

	private void escribirJson(ArrayList<Usuario> listaUsuarios) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();
		
		try(FileWriter w = new FileWriter("Data/Usuarios.json")){
			g.toJson(listaUsuarios,w);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Usuario> leerJson() {
		Gson g = new Gson();
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
		try (FileReader r = new FileReader("Data/Usuarios.json")){
			Type lista = new TypeToken<ArrayList<Usuario>>() {}.getType();
			listaUsuarios = g.fromJson(r, lista);
			
			if(listaUsuarios == null) {
				listaUsuarios = new ArrayList<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuarios;
	}
    
    
}
