package es.dabdm.decide.modelo;

import java.io.Serializable;

public class RespuestaUsuario implements Serializable{

	private static final long serialVersionUID = 7740757546884517889L;

	private Usuario usuario;
	
	private RespuestaPosible respuesta;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public RespuestaPosible getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(RespuestaPosible respuesta) {
		this.respuesta = respuesta;
	}
	
	
}
