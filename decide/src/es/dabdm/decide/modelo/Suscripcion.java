package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.Date;

public class Suscripcion implements Serializable{
	
	private static final long serialVersionUID = 6352666378580854630L;

	private Date fechaAlta;
	
	private Comunidad comunidad;
	
	private Usuario usuario;

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
