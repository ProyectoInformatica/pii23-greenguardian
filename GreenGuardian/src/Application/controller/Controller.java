package Application.controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Application.db.Connection;
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
	
	private ArrayList<Usuario> leerBBDD(){
		ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Connection bbdd = new Connection("SQLite/PRUEBA.db");

        try {
            // Consulta para obtener los usuarios
            String sql = "SELECT * FROM USUARIOS";
            bbdd.sentenciaSQL(sql);

            ResultSet rs = bbdd.executeQuery(sql);

            // Procesar los resultados y agregar usuarios a la lista
            while (rs.next()) {
            	int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String dni = rs.getString("dni");
                String telf = rs.getString("telefono");
                String contra = rs.getString("contra");
                String rol = rs.getString("rol");

                // Crear objeto Usuario y agregarlo a la lista
                Usuario usuario = new Usuario(id,nombre, apellido, dni, telf, contra, rol);
                listaUsuarios.add(usuario);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bbdd.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    	ArrayList<Usuario> listaUsuarios = leerBBDD();
    	
    		for (Usuario usuario : listaUsuarios) {
    			
    			if (usuario.getDni().equals(dni) && usuario.getContra().equals(contra)) {
                    nombre = usuario.getNombre();
                    Session.setUsuarioActual(usuario);
                    esCorrecto = true;
    				
                    if(usuario.getDni().equals(dni) & usuario.getContra().equals(contra) & usuario.getRol().equals("Cliente")) {
        				nombre = usuario.getNombre();
        				Session.setUsuarioActual(usuario);
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
    			
    			if(usuario.getDni().equals(dni) & usuario.getContra().equals(contra)& usuario.getRol().equals("Agricultor")) {
    				nombre = usuario.getNombre();
    				Session.setUsuarioActual(usuario);
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
    			
    			if(usuario.getDni().equals(dni) & usuario.getContra().equals(contra)& usuario.getRol().equals("Técnico")) {
    				nombre = usuario.getNombre();
    				Session.setUsuarioActual(usuario);
    				esCorrecto = true;
    				
    				 
    				
    	        	//Cerrar InicioSesion y abrir ventana principal
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