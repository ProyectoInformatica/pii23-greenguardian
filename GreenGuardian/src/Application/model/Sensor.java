package Application.model;

public class Sensor {
	private String sensorTemp1;
	private String sensorTemp2;
	private String sensorAgua;
	private String sensorHume;

	
	public Sensor(String sensorTemp1,String sensorTemp2, String sensorAgua, String sensorHume ) {
		this.sensorTemp1 = sensorTemp1;
		this.sensorTemp2 = sensorTemp2;
		this.sensorAgua = sensorAgua;
		this.sensorHume = sensorHume;
	
	}
	
	public String getSensor1() {
		return sensorTemp1;
	}
	public String getSensor2() {
		return sensorTemp2;
	}
	public String getSensor3() {
		return sensorAgua;
	}
	public String getSensor4() {
		return sensorHume;
	}

}