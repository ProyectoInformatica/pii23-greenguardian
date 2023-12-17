package Application.controller;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller{
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
    private Button btnLogOutVP;
    
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
    private ComboBox<String> rolesComboBox;
    
    
    
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
    	
    	// Validación del nombre
        if (nombre.isEmpty() || !nombre.matches("[a-zA-Z ]+")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El nombre está vacío o contiene caracteres no válidos.");
            alert.showAndWait();
            return; // Detiene la ejecución si la validación del nombre falla
        }
        // Validación del apellido
        if (apellido.isEmpty() || !apellido.matches("[a-zA-Z ]+")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El apellido está vacío o contiene caracteres no válidos.");
            alert.showAndWait();
            return; // Detiene la ejecución si la validación del apellido falla
        }
        // Validación del DNI
        if (!dni.matches("\\d{8}[A-Za-z]")) { // 8 dígitos seguidos de una letra
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El DNI debe contener 8 dígitos seguidos de una letra.");
            alert.showAndWait();
            return; // Detiene la ejecución si la validación del DNI falla
        }
        // Validación del Teléfono
        if (!telf.matches("\\d{9}")) { // Exactamente 9 dígitos
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("El teléfono debe contener exactamente 9 dígitos.");
            alert.showAndWait();
            return; // Detiene la ejecución si la validación del teléfono falla
        }
        // Validación de Coincidencia de Contraseñas
        if (!contra.equals(repeatContra)) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Las contraseñas no coinciden.");
            alert.showAndWait();
            return; // Detiene la ejecución si las contraseñas no coinciden
        }
    	
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
	// Para que en registar usuario solo acepte letras el NOMBRE 
	@FXML
	private void onKeyTypedEvent(KeyEvent event) {
	    if (!event.getCharacter().matches("[a-zA-Z]")) {
	        event.consume();
	    }
	}
	
	// Para que en registar usuario solo acepte letras el APELLIDO 
	@FXML
	private void onKeyTypedEvent2(KeyEvent event) {
	    if (!event.getCharacter().matches("[a-zA-Z]")) {
	        event.consume();
	    }
	}
	
	// Para que en registar usuario solo acepte 8 numeros y 1 letra en el DNI 
    @FXML
    private void onKeyTypedEvent3(KeyEvent event) {
        String currentText = txtIntroDni.getText();
        String newCharacter = event.getCharacter();

        if (currentText.length() < 8) {
            if (!newCharacter.matches("[0-9]")) {
                event.consume();
            }
        } else if (currentText.length() == 8) {
            if (!newCharacter.matches("[a-zA-Z]")) {
                event.consume();
            }
        } else {
            event.consume();
        }
    }

	
	// Para que en registar usuario solo acepte numeros el TELEFONO 
	@FXML
	private void onKeyTypedEvent4(KeyEvent event) {
	    TextField textField = (TextField) event.getSource();  
	    
	    if (!event.getCharacter().matches("[0-9]") || textField.getText().length() >= 9) {
	        event.consume(); 
	    }
	}

	
	
	
	
	
	
	
	
    
    
    /*
    @FXML
    private void initialize() {
        // Inicializar el ComboBox con roles
        rolesComboBox.getItems().addAll("Cliente", "Agricultor", "Técnico");

    }
    */

    @FXML
    void iniciarSesión(ActionEvent event) {
    	String dni;
    	String contra;
    	boolean esCorrecto = false;
    	
    	dni = txtUsuario.getText();
    	contra = txtConstra.getText();
    	
    
    	
    	//Creo lista y leo Json
    	ArrayList<Usuario> listaUsuarios = leerJson();
    	
    		for (int i = 0; i < listaUsuarios.size(); i++) {
        		Usuario u1 = listaUsuarios.get(i);
    			Usuario u = new Usuario(dni, contra, dni, dni, contra);
    			
    			if(u1.getDni().equals(u.getDni()) & u1.getContra().equals(u.getContra())) {
    				esCorrecto = true;
    				
    	        	//Cerrar InicioSesion y abrir ventana principal(Provisional hasta añadir los roles)
    	        	try {
    	        		Node source = (Node) event.getSource();
    	            	Stage stage = (Stage) source.getScene().getWindow();    
    	            	stage.close(); 
    	            	
    	            	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/menuPrincipal.fxml"));
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
		}
    		//Si los datos son incorrectos salta una alerta y vacia lo que has escrito
    	if(!esCorrecto) {
    		
    		Alert dialogo = new Alert(AlertType.ERROR);
        	dialogo.setTitle("Datos incorrectos");
        	dialogo.setHeaderText(null);
        	dialogo.setContentText("Datos erróneos, por favor verifique los datos");
        	dialogo.initStyle(StageStyle.UTILITY);
        	dialogo.showAndWait();
        	txtUsuario.setText("");
        	txtConstra.setText("");
    	}
    	
		
    	
    	
    	
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
    
}
