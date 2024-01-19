package Application.model;

public class Session {
	    private static Usuario usuarioActual;

	    public static Usuario getUsuarioActual() {
	        return usuarioActual;
	    }

	    public static void setUsuarioActual(Usuario usuario) {
	        usuarioActual = usuario;
	    }
	
}
