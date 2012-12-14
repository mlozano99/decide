package es.dabdm.decide.modelo;

import java.io.Serializable;




public class Comunidad implements Serializable {

	private static final long serialVersionUID = 2544867380337063679L;

	private Integer idComunidad;
	
	private String nombre;
	
	private String twitter; 
	
	private PosicionGPS gps;
	
	private String alcance;
	
	private String tipo;
	
	private Integer radio;

	private ComunityManager gestor;
	
    private String suscrito;
    
	
    public Comunidad() {
	}
    
    
    
	public Comunidad(String nombre, String twitter, PosicionGPS gps,
			String alcance, String tipo, Integer radio, ComunityManager gestor,String suscrito) {
		super();
		this.nombre = nombre;
		this.twitter = twitter;
		this.gps = gps;
		this.alcance = alcance;
		this.tipo = tipo;
		this.radio = radio;
		this.gestor = gestor;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public PosicionGPS getGps() {
		return gps;
	}

	public void setGps(PosicionGPS gps) {
		this.gps = gps;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getRadio() {
		return radio;
	}

	public void setRadio(Integer radio) {
		this.radio = radio;
	}

	public ComunityManager getGestor() {
		return gestor;
	}

	public void setGestor(ComunityManager gestor) {
		this.gestor = gestor;
	}


	public Integer getIdComunidad() {
		return idComunidad;
	}

	public void setIdComunidad(Integer idComunidad) {
		this.idComunidad = idComunidad;
	}



	public String getSuscrito() {
		return suscrito;
	}



	public void setSuscrito(String suscrito) {
		this.suscrito = suscrito;
	}



	
}
