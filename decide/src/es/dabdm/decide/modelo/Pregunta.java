package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.List;

public class Pregunta implements Serializable{

	private static final long serialVersionUID = 2974432651255159694L;

	private Encuesta encuesta;

	private Comunidad comunidad;
	
	private String texto;

	private List<RespuestaPosible> respuestasPosibles;
	
	public Encuesta getEncuesta() {
		return encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public List<RespuestaPosible> getRespuestasPosibles() {
		return respuestasPosibles;
	}

	public void setRespuestasPosibles(List<RespuestaPosible> respuestasPosibles) {
		this.respuestasPosibles = respuestasPosibles;
	}
	
	
}
