package es.dabdm.decide.util;



public final class Repositorio {

	
	public static final String URLBASE = "http://158.42.252.238:8081/servidorDecide/rest/";
	

	/* GET
	 * Lista de comunidades. 
	 * Parámetros: 
	 * Tipo: tipo de comunidad
	 * usuario: email de usuario
	 * En esta petición nos dará si la comunidad está suscrita para ese usuario
	 * */
	public static final String URLgetComunidades = URLBASE + "comunidades";
	

	/* PUT
	 * Petición de un usuario para suscribirse */
	/* Parámetros:
	 * email: 
	 * id_comunidad:
	 */
	public static final String URLsuscribir = "URLBASE" + "suscribir";
	
	
	/* DELETE
	 * Petición de un usuario para desuscribirse */
	/* Parámetros:
	 * email: 
	 * id_comunidad:
	 */	
	public static final String URLdesuscribir = "URLBASE" + "desuscribir";	
	
	
	/* POST
	 * Usuario guardar una preguenta */
	/* Parámetros:
	 * usuario: -email del usuario 
	 * id_pregunta:
	 * respuesta
	 */	
	public static final String URLguardarpregunta = "URLBASE" + "guardarpregunta";
	
	
	
	/* POST
	 * Usuario pide una preguenta a partir de un id que le llega en la notificación
	 * Esto se hará si es necesario */
	/* Parámetros:
	 * usuario: -email del usuario 
	 * id_pregunta:
	 * 
	 */	
	public static final String URLleerpregunta = "URLBASE" + "leerpregunta";

		
	/* PUT
	 * Usuario envía posición GPS */
	/* Parámetros:
	 * longitud: 
	 * latitud:
	 * usuario: -email del usuario
	 */
	public static final String URLposicion = "URLBASE" + "posicion";
	
	
	
	
	
	
	
}