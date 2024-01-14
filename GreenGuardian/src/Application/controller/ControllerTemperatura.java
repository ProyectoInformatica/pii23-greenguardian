package Application.controller;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Application.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
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


public class ControllerTemperatura {
	
	@FXML
    private Button VolverMenuPrin;
	
	@FXML
    private BarChart<String, Number> MostrarTemperatura;
	
	@FXML
	private Button btnActualizar;
	
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
					  
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

   
    @FXML
    void MostrarTemperatura(ActionEvent event) {
        // Suponiendo que tienes un método para obtener la temperatura actual
        double temperaturaActual = obtenerTemperatura();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Temperatura", temperaturaActual));

        MostrarTemperatura.getData().clear();
        MostrarTemperatura.getData().add(series);
    }
    @FXML
    private double obtenerTemperatura() {
        
    	
        return 25.0; 
    }
   
}
  
