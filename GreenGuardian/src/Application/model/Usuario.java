package Application.model;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String telf;
	private String contra;
	private String rol;
	
	public Usuario(String nombre, String apellido, String dni, String telf, String contra, String rol) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telf = telf;
		this.contra = contra;
		this.rol = rol;
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
	public String getRol() {
		return rol;
	}
}
