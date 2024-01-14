package Application.model;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Sensor {
    private double temperatura;
    private String rol; // Agricultor, Tecnico, Cliente
    private LocalDateTime horaUso;
    private double humedad;
    private double cantidadAgua;
    
    public Sensor(Double temperatura,Double humedad, Double cantidadAgua) {
		this.rol = rol;
		this.horaUso = LocalDateTime.now();		
		this.temperatura = temperatura;
		this.humedad = humedad;
		this.cantidadAgua=cantidadAgua;
   
    }
    public String toString() {
    	String salida1 = "Sensores: ( "+temperatura+", "+humedad+", "+cantidadAgua+", "+horaUso+", "+rol+")";
    	return salida1;
    }
    public double gettemperatura() {
		return temperatura;
	}

	public String getrol() {
		return rol;
	}
	

	public LocalDateTime gethoraUso() {
		return horaUso;
	}
	
	public double gethumedad() {
		return humedad;
	}
	
	public double getcantidadAgua() {
		return cantidadAgua;
	}
}
