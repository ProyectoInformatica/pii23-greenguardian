package Application.controller;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Application.db.Connection;
import Application.model.Sensor;
import Application.model.Session;
import Application.model.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerTemperatura {

    @FXML
    private Button VolverMenuPrin;

    @FXML
    private ComboBox<Date> cmbFechas;

    @FXML
    private BarChart<String, Number> MostrarTemperatura;

    @FXML
    private Button btnActualizar;

    Usuario usuarioActual = Session.getUsuarioActual();

    Connection bbdd = new Connection("SQLite/PRUEBA.db");

    ArrayList<Sensor> listaSenTempUser = leerDesdeDB();

    private ArrayList<Sensor> leerDesdeDB() {
        ArrayList<Sensor> sensores = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String sql = "SELECT DNI_USER, TIPO_SENSOR, FECHA, DATO FROM SENSORES WHERE TIPO_SENSOR = 'Temperatura' AND DNI_USER = ?";
        try (PreparedStatement pstmt = bbdd.prepareStatement(sql)  		
             ) {  	
        	pstmt.setString(1, usuarioActual.getDni());
        	ResultSet rs = pstmt.executeQuery();
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

                Sensor sensor = new Sensor(id, tipoSensor, fecha, dato);
                sensores.add(sensor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return sensores;
    }

    @FXML
    void irMenuPrincipal(ActionEvent event) {
        try {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Application/view/menuPrincipal.fxml"));
            ControllerVentanaPrincipal control = new ControllerVentanaPrincipal();
            loader.setController(control);
            Parent root = loader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root));
            stage1.initModality(Modality.WINDOW_MODAL);
            stage1.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage1.show();
            control.setLabelText(usuarioActual.getNombre());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void MostrarTemperatura(ActionEvent event) {
        double temperaturaActual = obtenerTemperatura();

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Temperatura", temperaturaActual));

        MostrarTemperatura.getData().clear();
        MostrarTemperatura.getData().add(series);
    }

    @FXML
    void initialize() {
        List<Date> fechas = obtenerFechasDesdeSensores(listaSenTempUser);
        ObservableList<Date> fechasObservable = FXCollections.observableArrayList(fechas);
        cmbFechas.setItems(fechasObservable);
    }

    private List<Date> obtenerFechasDesdeSensores(ArrayList<Sensor> sensores) {
        return sensores.stream().map(Sensor::getFecha).collect(Collectors.toList());
    }

    private double obtenerTemperatura() {
        Date fechaSeleccionada = cmbFechas.getValue();
        Optional<Sensor> sensorOptional = listaSenTempUser.stream()
            .filter(sensor -> sensor.getFecha().equals(fechaSeleccionada))
            .findFirst();
        return sensorOptional.map(Sensor::getDato).orElse(0);
    }
}

  
