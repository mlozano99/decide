package es.dabdm.decide.modelo;

import java.io.Serializable;


public class PosicionGPS implements Serializable{

	private static final long serialVersionUID = -288754393674034616L;

	private double longitud;
	
	private double latitud;

	public PosicionGPS() {
	}
	
	public PosicionGPS(double longitud, double latitud) {
		super();
		this.longitud = longitud;
		this.latitud = latitud;
	}


	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	
}
