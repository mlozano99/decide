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
	 * 
                   
	   @PUT
	     public void suscribirUsusarioComunidad(String email, Integer idComunidad)  -> Suscribir de una comunidad

	   @GET
	     public ListaComunidades getComunidadesSuscritas( String email) -> Lista de comunidades suscritas


	*/
	public static final String URLsuscripciones = URLBASE + "suscripciones";
	
	
	/*
       Se ha separado de la URI anterior, porque @DELETE no acepta parametros, y se ha hecho con post en esta otra URI
       
       @POST
       public void desuscribir(String email, Integer idComunidad);  --> Desuscribir de una comunidad
	 */
	public static final String URLdesuscripcion = URLBASE + "desuscribir";
	
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
	
		
	/*
	 * Referencia al fichero de preferencias o perfil de usuario
	 * 
	 */
	public static final String PerfilPref = "_perfil";
	
	/* Para enviar una notificación al terminal se utiliza esta url
	 * con los parametros idPregunta   e regId  {pregunta comienza en el 650}
	 * url 
	 * http://158.42.252.238:8081/servidorDecide/rest/notificarPregunta?idPregunta=650&regId=APA91bFKhL_B-aMdiUo3cpzRKbB14iaJ4Va-5mixdm0dkx26a3bvzyXodrtf76Q1uGhqMgAkBU2yrKLd4I58nAnHpLdoPnY_Pv6SNrIywwTYoI2xad5Zn2zAZn2FNoIBj7KxYtJaTHE4
	 */
	
}