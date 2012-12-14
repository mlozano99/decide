package es.dabdm.decide.util;



public final class Repositorio {

	
	public static final String URLBASE = "http://158.42.252.238:8081/servidorDecide/rest/";
	

	/* GET
	 * Lista de comunidades. 
	 * Par�metros: 
	 * Tipo: tipo de comunidad
	 * usuario: email de usuario
	 * En esta petici�n nos dar� si la comunidad est� suscrita para ese usuario
	 * */
	public static final String URLgetComunidades = URLBASE + "comunidades";
	

	/* PUT
	 * Petici�n de un usuario para suscribirse */
	/* Par�metros:
	 * email: 
	 * id_comunidad:
	 */
	public static final String URLsuscribir = "URLBASE" + "suscribir";
	
	
	/* DELETE
	 * Petici�n de un usuario para desuscribirse */
	/* Par�metros:
	 * email: 
	 * id_comunidad:
	 */	
	public static final String URLdesuscribir = "URLBASE" + "desuscribir";	
	
	
	/* POST
	 * Usuario guardar una preguenta */
	/* Par�metros:
	 * usuario: -email del usuario 
	 * id_pregunta:
	 * respuesta
	 */	
	public static final String URLguardarpregunta = "URLBASE" + "guardarpregunta";
	
	
	
	/* POST
	 * Usuario pide una preguenta a partir de un id que le llega en la notificaci�n
	 * Esto se har� si es necesario */
	/* Par�metros:
	 * usuario: -email del usuario 
	 * id_pregunta:
	 * 
	 */	
	public static final String URLleerpregunta = "URLBASE" + "leerpregunta";

		
	/* PUT
	 * Usuario env�a posici�n GPS */
	/* Par�metros:
	 * longitud: 
	 * latitud:
	 * usuario: -email del usuario
	 */
	public static final String URLposicion = "URLBASE" + "posicion";
	
	
	
	
	
	
	
}