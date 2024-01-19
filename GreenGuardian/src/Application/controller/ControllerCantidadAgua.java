package Application.controller;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import Application.model.Sensor;
import Application.model.Session;
import Application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class ControllerCantidadAgua {
	

	    @FXML
	    private BarChart<String, Number> MostrarCantidadAgua;

	    @FXML
	    private Button btnActualizar;

	    @FXML
	    private Button VolverMenuPrin;
	    
	    Usuario usuarioActual = Session.getUsuarioActual();
	    
	    ArrayList<Sensor> listSensores = leerJson();
	    ArrayList<Sensor> listaSenAguaUser = rellenarListaSensorUser();
	        
	        ArrayList<Sensor> rellenarListaSensorUser() {
	        	ArrayList<Sensor> result = new ArrayList<>();
	        	for (int i = 0; i < listSensores.size(); i++) {
	        		Sensor s = listSensores.get(i);
					if(s.getId().equals(usuarioActual.getDni())& s.getTipoSensor().equals("Agua")) {
						result.add(s);
						
					}
				}
				return result;
	        }

	    @FXML
	    void MostrarCantidadAgua(ActionEvent event) {
            double AguaActual = obtenerAgua();

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>("Cantidad de agua", AguaActual));

            MostrarCantidadAgua.getData().clear();
            MostrarCantidadAgua.getData().add(series);
        }
    	@FXML
        private double obtenerAgua() {
            
        	
            return 500.0; 
        }

    	
    	
@FXML
	void irMenuPrincipal(ActionEvent event) {
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
		}
}

private ArrayList<Sensor> leerJson() {
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
}

}
