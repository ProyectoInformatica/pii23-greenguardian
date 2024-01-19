package Application.controller;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Application.model.Session;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller{
	 
	 
	@FXML
    private TextField txtUsuario;

    @FXML
    private Label lblOlvideContra;

    @FXML
    private Button btnInciarSesion;

    @FXML
    private Button btnRegistrar;
    
    @FXML
    private PasswordField txtContra;
    
    public String nombre;
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
    		ControllerRegistro control = new ControllerRegistro();
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

    @FXML
    void iniciarSesión(ActionEvent event) {
    	String dni;
    	String contra;
    	boolean esCorrecto = false;
    	
    	dni = txtUsuario.getText();
    	contra = txtContra.getText();
    	
    	//Creo lista y leo Json
    	ArrayList<Usuario> listaUsuarios = leerJson();
    	
    		for (int i = 0; i < listaUsuarios.size(); i++) {
        		Usuario u1 = listaUsuarios.get(i);
    			Usuario u = new Usuario(dni, contra, dni, dni, contra,contra);
    			
    			if(u1.getDni().equals(u.getDni()) & u1.getContra().equals(u.getContra()) & u1.getRol().equals("Cliente")) {
    				nombre = u1.getNombre();
    				Session.setUsuarioActual(u1);
    				esCorrecto = true;
    				
    				 
    				
    	        	//Cerrar InicioSesion y abrir ventana principal(Provisional hasta añadir los roles)
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
    	    			control.setLabelText(nombre);
    	    			
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}
    	        	
    			}	
    			
    			if(u1.getDni().equals(u.getDni()) & u1.getContra().equals(u.getContra())& u1.getRol().equals("Agricultor")) {
    				nombre = u1.getNombre();
    				Session.setUsuarioActual(u1);
    				esCorrecto = true;
    				
    				 
    				
    	        	//Cerrar InicioSesion y abrir ventana principal(Provisional hasta añadir los roles)
    	        	try {
    	        		Node source = (Node) event.getSource();
    	            	Stage stage = (Stage) source.getScene().getWindow();    
    	            	stage.close(); 
    	            	
    	            	FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Application/view/menuAgricultor.fxml"));
    	            	ControllerVAgricultor control = new ControllerVAgricultor();
    	            	loader1.setController(control);
    	    			Parent root1 = loader1.load();
    	    			Stage stage1 = new Stage();
    	    			stage1.setScene(new Scene(root1));
    	    			stage1.initModality(Modality.WINDOW_MODAL);
    	    			stage1.initOwner(((Node) (event.getSource())).getScene().getWindow());
    	    			stage1.show();
    	    			control.setLabelText(nombre);
    	    			
    	    			
    	    		} catch (Exception e) {
    	    			e.printStackTrace();
    	    		}
    	        	
    			}
    			
    			if(u1.getDni().equals(u.getDni()) & u1.getContra().equals(u.getContra())& u1.getRol().equals("Técnico")) {
    				nombre = u1.getNombre();
    				Session.setUsuarioActual(u1);
    				esCorrecto = true;
    				
    				 
    				
    	        	//Cerrar InicioSesion y abrir ventana principal(Provisional hasta añadir los roles)
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
    	    			control.setLabelText(nombre);
    	    			
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
        	txtContra.setText("");

    	}  	
    	
    }  


}