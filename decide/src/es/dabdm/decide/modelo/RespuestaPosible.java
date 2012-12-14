package es.dabdm.decide.modelo;

import java.io.Serializable;


public class RespuestaPosible implements Serializable{

	private static final long serialVersionUID = -5355438869956833903L;

	private Integer idRespuestaPosible;
	
    private String valor;

    public RespuestaPosible() {
	}
    
	public RespuestaPosible(Integer idRespuestaPosible, String valor) {
		super();
		this.idRespuestaPosible = idRespuestaPosible;
		this.valor = valor;
	}

	public Integer getIdRespuestaPosible() {
		return idRespuestaPosible;
	}

	public void setIdRespuestaPosible(Integer idRespuestaPosible) {
		this.idRespuestaPosible = idRespuestaPosible;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
  
}
