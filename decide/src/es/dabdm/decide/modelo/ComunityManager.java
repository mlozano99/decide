package es.dabdm.decide.modelo;

import java.io.Serializable;


public class ComunityManager implements Serializable{

	private static final long serialVersionUID = 2972103645772331624L;

	private Integer idManager;
	/*
	 * Identificador único del comunity manager
	 */
	private String nombre;

	public ComunityManager() {
	}

	public ComunityManager(String nombre) {
		super();
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdManager() {
		return idManager;
	}
	public void setIdManager(Integer idManager) {
		this.idManager = idManager;
	}
	
}
