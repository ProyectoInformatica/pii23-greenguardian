package Application.controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
public class ControllerHumedad {

        @FXML
        private BarChart<String, Number> MostrarHumedad;

        @FXML
        private Button btnActualizar;

        @FXML
        private Button VolverMenuPrin;

        @FXML
        void MostrarHumedad(ActionEvent event) {
                double HumedadActual = obtenerHumedad();

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.getData().add(new XYChart.Data<>("Humedad", HumedadActual));

                MostrarHumedad.getData().clear();
                MostrarHumedad.getData().add(series);
            }
        	@FXML
            private double obtenerHumedad() {
                
            	
                return 45.0; 
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
					  
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    }

}
