package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.List;

public class ComunityManager implements Serializable{

	private static final long serialVersionUID = 2972103645772331624L;

	/*
	 * Identificador único del comunity manager
	 */
	private String nombre;
	
	private List<Comunidad> comunidades;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Comunidad> getComunidades() {
		return comunidades;
	}
	public void setComunidades(List<Comunidad> comunidades) {
		this.comunidades = comunidades;
	}
	
}
