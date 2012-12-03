package es.dabdm.decide.modelo;

import java.io.Serializable;
import java.util.List;

public class Comunidad implements Serializable {

	private static final long serialVersionUID = 2544867380337063679L;

	private String nombre;
	
	private String twitter; 
	
	private PosicionGPS gps;
	
	private String alcance;
	
	private String tipo;
	
	/**
	 * Para JOSE: Hará falta que haya una función que tenga esta API:
	 *   
	 *   public boolean isUsuarioEnRadioComunidad(PosicionGPS gpsUsuario, PosicionGPS gpsComunidad);
	 *   
	 *   Esta función dirá si esta o no en el radio de la comunidad.
	 */
	private Integer radio;

	private ComunityManager gestor;
	
	private List<Suscripcion> suscriptores;
	
	
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

	public List<Suscripcion> getSuscriptores() {
		return suscriptores;
	}

	public void setSuscriptores(List<Suscripcion> suscriptores) {
		this.suscriptores = suscriptores;
	}
	
	
	
}
