package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable{

	private static final long serialVersionUID = -8594642118817237794L;

	/**
	 * Esto será la clave que identifique un usario
	 */
	private String email;	
	
	/**
	 * Registro GCM del usuario en la aplicacion
	 */
	private String idRegistration;
	
	private String telefono;
	
	private String nombre;	
	
	private String publicidad;

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(String publicidad) {
		this.publicidad = publicidad;
	}

	public String getIdRegistration() {
		return idRegistration;
	}

	public void setIdRegistration(String idRegistration) {
		this.idRegistration = idRegistration;
	}
	
	
	
}
