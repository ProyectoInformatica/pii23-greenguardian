package Application.controller;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import Application.db.DatabaseConnection;
import Application.model.Sensor;
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

public class ControllerListaSensoresTecnico {
	@FXML
    private TableView<Sensor> tblSensores;

    @FXML
    private TableColumn<Sensor, String> ColDNI;

    @FXML
    private TableColumn<Sensor, String> ColTipoSensor;

    @FXML
    private TableColumn<Sensor, Date> ColFecha;

    @FXML
    private TableColumn<Sensor, String> ColDato;
    
    @FXML
    private Button btnVolver;
    
    DatabaseConnection bbdd = new DatabaseConnection("jdbc:mariadb://195.235.211.197/piigreenguardian","piigreenguardian","gr33nguard1an","piigreenguardian");
    
    Usuario usuarioActual = Session.getUsuarioActual();
    
    //ArrayList<Sensor> listSen = leerJson();
    
    @FXML
    void initialize() {
        
        ColDNI.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColTipoSensor.setCellValueFactory(new PropertyValueFactory<>("tipoSensor"));
        ColFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        ColDato.setCellValueFactory(new PropertyValueFactory<>("dato"));
     
        if (usuarioActual != null && usuarioActual.getRol().equals("Técnico")) {
            //List<Usuario> clientesAsignados = usuarioActual.getClientesAsignados();
        	List<Sensor> sensores = obtenerDatosSensor(usuarioActual.getId());
            /*if (clientesAsignados != null) {
                // Filtrar los sensores por los clientes asignados al técnico
                ArrayList<Sensor> sensoresFiltrados = new ArrayList<>();
                for (Sensor sensor : listSen) {
                    for (Usuario cliente : clientesAsignados) {
                        if (cliente.getDni().equals(sensor.getId())) {
                            sensoresFiltrados.add(sensor);
                            break; // No necesitamos seguir verificando otros clientes
                        }
                    }
                }*/

                tblSensores.setItems(FXCollections.observableArrayList(sensores));
            }/* else {
                // Si el técnico no tiene clientes asignados, mostrar un mensaje o hacer algo según tus necesidades
            	mostrarAlerta("Información", "El técnico no tiene clientes asignados.");
            }*/
         else {
            // Manejar el caso en que no se pueda obtener la información del técnico
        	mostrarAlerta("Error", "No se pudo obtener la información del técnico.");
        }
    }
    
    private List<Sensor> obtenerDatosSensor(int idTecnico) {
		String sql = "SELECT s.* FROM CLIENTES_TECNICOS ct JOIN USUARIOS u ON ct.ID_CLIENTE = u.ID LEFT JOIN SENSORES s ON u.DNI = s.DNI_USER WHERE ct.ID_TECNICO = ?";
		
		try (PreparedStatement pstmt = bbdd.prepareStatement(sql)) {
            pstmt.setInt(1, idTecnico);
            ResultSet rs = pstmt.executeQuery();

            List<Sensor> sensores = new ArrayList<>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
            	String id = rs.getString("DNI_USER");
                String tipoSensor = rs.getString("TIPO_SENSOR");
                String fechaStr = rs.getString("FECHA");
                Date fecha = null;
                try {
                    fecha = dateFormat.parse(fechaStr);
                } catch (ParseException e) {
                    System.err.println("Error al parsear la fecha: " + fechaStr);
                    continue;
                }
                int dato = rs.getInt("DATO");

                Sensor sensor = new Sensor(id,tipoSensor, fecha, dato);
                sensores.add(sensor);
            }

            return sensores;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                bbdd.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    
    /*private ArrayList<Sensor> leerJson() {
    	GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return df.parse(json.getAsString());
                } catch (ParseException e) {
                    throw new JsonParseException(e);
                }
            }
        });

        Gson g = gsonBuilder.create();
		ArrayList<Sensor> listasSensor = new ArrayList<>();
		try (FileReader r = new FileReader("Data/Sensores.json")){
			Type lista = new TypeToken<ArrayList<Sensor>>() {}.getType();
			listasSensor = g.fromJson(r, lista);
			
			if(listasSensor == null) {
				listasSensor = new ArrayList<>();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listasSensor;
	}*/
}
