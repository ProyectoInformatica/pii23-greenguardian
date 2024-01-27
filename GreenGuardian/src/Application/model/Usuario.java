package Application.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nombre;
	private String apellido;
	private String dni;
	private String telf;
	private String contra;
	private String rol;
	private Usuario tecnicoAsig;
	private Usuario tecnicoAsigDos;
	private List<Usuario> clientesAsignados;
	
	public Usuario(String nombre, String apellido, String dni, String telf, String contra, String rol) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telf = telf;
		this.contra = contra;
		this.rol = rol;
	}
	//Contrucor para el cliente
	public Usuario(String nombre, String apellido, String dni, String telf, String contra, String rol, Usuario tecnicoAsig, Usuario tecnicoAsigDos) {
        this(nombre, apellido, dni, telf, contra, rol);
        this.tecnicoAsig = tecnicoAsig;
        this.tecnicoAsigDos = tecnicoAsigDos;
    }
	
	//Constructor para el tecnico
	public Usuario(String nombre, String apellido, String dni, String telf, String contra, String rol, List<Usuario> clientesAsignados) {
        this(nombre, apellido, dni, telf, contra, rol);
        this.clientesAsignados = clientesAsignados;
    }
	public String toString() {
    	String salida = "Usuario: ( "+nombre+", "+apellido+", "+dni+", "+telf+", "+contra+")";
    	return salida;
    }

	public String toString2() {
    	String salida = "Usuario: ( "+nombre+", "+apellido+", "+dni+", "+telf+", "+contra+clientesAsignados+")";
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
