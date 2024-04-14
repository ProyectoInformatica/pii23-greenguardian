package Application.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellido;
	private String dni;
	private String telf;
	private String contra;
	private String rol;
	private Usuario tecnicoAsig;
	private Usuario tecnicoAsigDos;
	private List<Usuario> clientesAsignados;
	
	public Usuario(int id,String nombre, String apellido, String dni, String telf, String contra, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telf = telf;
		this.contra = contra;
		this.rol = rol;
	}
	//Constructor para enseñar la tabla cliente
	public Usuario(String nombre, String apellido, String dni, String telf) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telf = telf;
	}
	
	public String toString() {
    	String salida = "Usuario: ( "+nombre+", "+apellido+", "+dni+", "+telf+", "+contra+")";
    	return salida;
    }
	
	public int getId() {
        return id;
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
	
	public String getApellido() {
		return apellido;
	}

	public String getRol() {
		return rol;
	}
	
	public String getTelf() {
		return telf;
	}

	public Usuario getTecnicoAsignado() {
	    return tecnicoAsig;
	}
	
	public Usuario getTecnicoAsignadoDos() {
	    return tecnicoAsigDos;
	}
	
	public List<Usuario> getClientesAsignados() {
        return clientesAsignados;
    }
	public void setClientesAsignados(List<Usuario> clientesAsignados) {
		this.clientesAsignados = clientesAsignados;
	}
	
	public void agregarClienteAsignado(Usuario cliente) {
	    if (clientesAsignados == null) {
	        clientesAsignados = new ArrayList<>();
	    }
	    clientesAsignados.add(cliente);
	}
}
