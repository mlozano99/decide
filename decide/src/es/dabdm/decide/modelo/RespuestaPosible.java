package es.dabdm.decide.modelo;

import java.io.Serializable;

public class RespuestaPosible implements Serializable{

	private static final long serialVersionUID = 4876897886769836373L;
	
	private Pregunta pregunta;

    private String valor;
	
	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
