package Application.model;

public class RegistroFeedback {
    private String fechaHora;
    private String usuario;

    public RegistroFeedback(String fechaHora, String usuario) {
        this.setFechaHora(fechaHora);
        this.setUsuario(usuario);
    }

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

    // Métodos getter y setter si son necesarios
}