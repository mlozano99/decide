package es.dabdm.decide.util;



public final class Repositorio {

                                     
	public static final String URLBASE = "http://158.42.252.238:8081/servidorDecide/rest/";
	

	/* 	
	 *   @GET
	 *  	public ListaComunidades getComunidades(String email,String tipo,String latitud,String longitud)
	 *       
	 *      - Lista de comunidades, funciona de varias formas este método:
              -  Si se envia con latitud y longitud <> 0 -> entonces envia las que estan cerca del usuario (¿por tipo tmb?
              -  Si se envia con  latitud=longitud=0     -> entonces se envia las que ha consultado por un tipo (todas las que cumplan la consulta, este o no suscrito)

           ¿ En cualquier caso email y tipo son obligatorios (verificar este punto....) ?
	 * */
	public static final String URLcomunidades = URLBASE + "comunidades";
	
	

	/*  Se usa el mismo punto, lo único que cambia es el método de la petición que tiene diferentes significados
	 *  @DELETE
         public void deSuscribirUsusarioComunidad( String email, Integer idComunidad) --> Desuscribir de una comunidad
                   
	   @PUT
	     public void suscribirUsusarioComunidad(String email, Integer idComunidad)  -> Suscribir de una comunidad

	   @GET
	     public ListaComunidades getComunidadesSuscritas( String email) -> Lista de comunidades suscritas


	*/
	public static final String URLsuscripciones = URLBASE + "suscripciones";
	
	

	/* 
         @PUT
	       public void responderPregunta(Integer idPregunta,String email, Integer idRespuesta) --> El usuario responde una pregunta 
	 */	
	public static final String URLpreguntas = URLBASE + "preguntas";
	
	
		
	/*@PUT
	     public void posicionUsuario(  double longitud,  double latitud, String email)  -> Para enviar la posición del usuario
	 */
	public static final String URLposicion = URLBASE + "posicion";
	
	/*
	  @PUT	  
	       public void altaUsuario(Usuario usuario) -> Se da de alta un usuario, hay que convertirlo primero como JSON
	    
       @GET
           public Usuario getUsuario(String email) --> Recupera todos los datos del usuario en el servidor (se reciben en formato JSON)	    
	*/
	public static final String URLusuarios = URLBASE + "usuarios";
	
	
}