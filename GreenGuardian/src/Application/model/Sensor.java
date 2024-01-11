package Application.model;

public class Sensor {
	private Double sensorTemp1;
	private Double sensorTemp2;
	private Double sensorAgua;
	private Double sensorHume;

	
	public Sensor(Double sensorTemp1,Double sensorTemp2, Double sensorAgua, Double sensorHume ) {
		this.sensorTemp1 = sensorTemp1;
		this.sensorTemp2 = sensorTemp2;
		this.sensorAgua = sensorAgua;
		this.sensorHume = sensorHume;
		
	
	}
	
	public Double getSensor1() {
		return sensorTemp1;
	}
	public Double getSensor2() {
		return sensorTemp2;
	}
	public Double getSensor3() {
		return sensorAgua;
	}
	public Double getSensor4() {
		return sensorHume;
	}

}