package Application.controller;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Application.db.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerRegistro implements Initializable{
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
    private Button btnRegistrarmeR;
    
    @FXML
    private Button btnVolverR;
    
    @FXML
    private ComboBox<String> rolesComboBox;
    
    DatabaseConnection bbdd = new DatabaseConnection("jdbc:mariadb://195.235.211.197/piigreenguardian","piigreenguardian","gr33nguard1an","piigreenguardian");
    
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
    	
    	//Creo el usuario
    	
    	String nombre;
    	String apellido;
    	String dni;
    	String telf;
    	String contra;
    	String repeatContra;
    	String selectedRole;
    	
    	//Recoger datos de los texfield 
    	nombre= txtIntroNombre.getText();
    	apellido = txtIntroApellido.getText();
    	dni = txtIntroDni.getText();
    	telf = txtIntroTelf.getText();
    	contra = txtIntroContr.getText();
    	repeatContra = txtReintroContr.getText();
    	selectedRole = rolesComboBox.getValue();
    	
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
        if(!validarNIF(dni)) {
        		Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("DNI incorrecto");
                alert.showAndWait();
                return;       	
        }
        	

        	    // Verificación de DNI duplicado
        		//TODO : Hacer la validación con la BBDD
        	/*    if (listaUsuarios.stream().anyMatch(usuario -> usuario.getDni().equals(dni))) {
        	        // Mostrar un mensaje de error
        	        Alert alert = new Alert(AlertType.ERROR);
        	        alert.setTitle("Error de Registro");
        	        alert.setHeaderText(null);
        	        alert.setContentText("Ya existe un usuario con este DNI.");
        	        alert.showAndWait();
        	        return;
        	    }*/
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
        if (selectedRole == null || selectedRole.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en el Registro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecciona un rol.");
            alert.showAndWait();
            return; // No continuar con el registro
        }

        
    	
    	if(selectedRole.equals("Cliente")) {
    	    
    	    insertarUsuario(nombre, apellido, dni, telf, contra, selectedRole);
    	    
    	}else if(selectedRole.equals("Técnico")) {
    		
    		insertarUsuario(nombre, apellido, dni, telf, contra, selectedRole);
    		
    	}else if(selectedRole.equals("Agricultor")) {
    		
    	    
    	    insertarUsuario(nombre, apellido, dni, telf, contra, selectedRole);
    	   
    	}   	
    	
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
    	Alert dialogo1 = new Alert(AlertType.INFORMATION);
    	dialogo1.setTitle("Usuario Registrado");
    	dialogo1.setHeaderText(null);
    	dialogo1.setContentText("Usuario registrado correctamente");
    	dialogo1.initStyle(StageStyle.UTILITY);
    	dialogo1.showAndWait();
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
	
    public boolean validarNIF(String dni) {
        dni = dni.toUpperCase();
        // Comprobar si el NIF mide 9 caracteres
        if (dni.length() != 9) {
            return false;

        }

        // Comprobar si los caracteres que se encuentran entre la(s) letra(s) son números
        for (int i = 1; i < dni.length()-1; i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                return false;
            }
        }


        // Comprobar si la letra del NIF es correcta
        String letraFinal = "TRWAGMYFPDXBNJZSQVHLCKE";
        String letraNIE = "XYZ";

        if (letraNIE.contains((String.valueOf(dni.charAt(0))))) {
            switch (dni.charAt(0)) {
                case 'X':
                    dni = dni.substring(1);
                    dni = "0" + dni;
                    break;
                case 'Y':
                    dni = dni.substring(1);
                    dni = "1" + dni;
                    break;
                case 'Z':
                    dni = dni.substring(1);
                    dni = "2" + dni;
                    break;
                default:
                    break;
            }
        }

        int numeroDni = Integer.parseInt(dni.substring(0, 8));
        char letraCalculada = letraFinal.charAt(numeroDni % 23);

        if (letraCalculada != (dni.charAt(8))) {
            return false;
        }
        return true;
}
    
    private void insertarUsuario(String nombre, String apellido, String dni, String telf, String contra,
            String selectedRole) {
    	
        // Preparar la consulta SQL
        String sql = "INSERT INTO USUARIOS (NOMBRE, APELLIDO, DNI, TELEFONO, CONTRA, ROL) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = bbdd.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setString(3, dni);
            pstmt.setString(4, telf);
            pstmt.setString(5, contra);
            pstmt.setString(6, selectedRole);
            pstmt.executeUpdate(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                bbdd.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<String> roles = FXCollections.observableArrayList("Cliente", "Agricultor", "Técnico");
        rolesComboBox.setItems(roles);	
	}
}
