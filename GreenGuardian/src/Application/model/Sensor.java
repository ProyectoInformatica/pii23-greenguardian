package Application.model;

import java.util.Date;

public class Sensor {
	private String id;
	private String tipoSensor;
	private Date fecha;
	private int dato;
	
	public Sensor(String id, String tipoSensor, Date fecha, int dato) {
		this.id = id;
		this.tipoSensor = tipoSensor;
		this.fecha = fecha;
		this.dato = dato;
	}

	public String getId() {
		return id;
	}

	public String getTipoSensor() {
		return tipoSensor;
	}

	public Date getFecha() {
		return fecha;
	}

	public int getDato() {
		return dato;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", tipoSensor=" + tipoSensor + ", fecha=" + fecha + ", dato=" + dato + "]";
	}
	
	
	
}
