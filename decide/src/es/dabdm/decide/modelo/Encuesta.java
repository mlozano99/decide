package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Encuesta implements Serializable{

	private static final long serialVersionUID = -8414195943489605518L;

	private Date fechaLimite;
	
	private List<Usuario> usuarios;
	
	private List<Pregunta> preguntas;
	
	
	public Date getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
}
