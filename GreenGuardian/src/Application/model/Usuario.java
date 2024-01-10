package Application.model;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String telf;
	private String contra;
	
	public Usuario(String nombre, String apellido, String dni, String telf, String contra) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telf = telf;
		this.contra = contra;
	}
	
	public String toString() {
    	String salida = "Usuario: ( "+nombre+", "+apellido+", "+dni+", "+telf+", "+contra+")";
    	return salida;
    }

	public String getDni() {
		return dni;
	}

	public String getContra() {
		return contra;
	}
	

	public String getNombre() {
		return nombre;
	}
}
