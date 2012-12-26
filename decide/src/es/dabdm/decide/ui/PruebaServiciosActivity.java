package es.dabdm.decide.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Comunidad;
import es.dabdm.decide.modelo.ListaComunidades;
import es.dabdm.decide.modelo.PosicionGPS;
import es.dabdm.decide.modelo.Usuario;
import es.dabdm.decide.util.LVI_generico;
import es.dabdm.decide.util.Repositorio;


/**
 * La he creado para temporalmente hacer pruebas sobre los servicios web.
 * Voy a dejar todo el código necesario para hacer cada una de las peticiones web al servidor.
 * Las cojeis de aqui y la llevais al activity que corresponda.
 * 
 * Puedo meter tambien alguna cuestion referida al almacenamiento para tengamos el código necesario.
 * 
 * En teoría hasta este punto es la parte que me correspondia.Si necesitas algo mas avisa...
 * 
 * @author manolo
 *
 */
public class PruebaServiciosActivity extends BaseActivity {

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pruebaservicios);
	}
	
	

	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////    Para hacer una petición de lista de comunidades   //////////////////////////////////////////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private class RecuperarComunidades extends AsyncTask<String, ListaComunidades, Boolean> {
	    	
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Boolean doInBackground(String... params) {
	            		
       		HttpResponse response = null;
       		HttpEntity entity = null;
       		HttpClient client = new DefaultHttpClient();
       		HttpGet request = null;
       		List<NameValuePair> pares = new ArrayList<NameValuePair>();
       		pares.add(new BasicNameValuePair("email", params[0])); // Email usuario
       		pares.add(new BasicNameValuePair("tipo", params[1])); //  tipo de comunidad
       		pares.add(new BasicNameValuePair("longitud", params[2]));
       		pares.add(new BasicNameValuePair("latitud", params[3])); 
       		
       		
				try {
					
					android.util.Log.i("RecuperarComunidades","Peticion a " + Repositorio.URLcomunidades);
					
					request = new HttpGet(Repositorio.URLcomunidades + "?" + URLEncodedUtils.format(pares, "utf-8")); 
					response = client.execute(request);
					entity = response.getEntity();
					
					if (entity != null) {
						InputStream stream = entity.getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line + "\n");
						}
						stream.close();
						String responseString = sb.toString();
						GsonBuilder builder = new GsonBuilder();
						Gson gson = builder.create();
						JSONObject json = new JSONObject(responseString);
						//Aqui hay que cargar la lista de scores...
						ListaComunidades lista =  gson.fromJson(json.toString(), ListaComunidades.class);			
						publishProgress(lista);
					}

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}catch (JSONException e) {
					e.printStackTrace();
				}

	    		return true;
	    	}

	    	@Override
	    	protected void onProgressUpdate(ListaComunidades... comunidades) {

	    		ArrayList<LVI_generico> items = new ArrayList<LVI_generico>();
	    		for(Comunidad c : comunidades[0].getComunidades()){
	    		    items.add(new LVI_generico(c.getNombre()));     
	    		}
	    		
	    		// Código necesario para mostrar la información obtenida en la activity
	    		
	    		super.onProgressUpdate(comunidades);
	    		Thread.currentThread().interrupt();
	    	}
	    }

	 
		
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////    Para hacer una petición de lista de comunidades a las que esta suscrito un ususario  ///////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private class RecuperarComunidadesSuscritas extends AsyncTask<String, ListaComunidades, Boolean> {
	    	
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Boolean doInBackground(String... params) {
	            		
       		HttpResponse response = null;
       		HttpEntity entity = null;
       		HttpClient client = new DefaultHttpClient();
       		HttpGet request = null;
       		List<NameValuePair> pares = new ArrayList<NameValuePair>();
       		pares.add(new BasicNameValuePair("email", params[0])); // Email usuario

				try {
					
					android.util.Log.i("RecuperarComunidades","Peticion a " + Repositorio.URLsuscripciones);
					
					request = new HttpGet(Repositorio.URLcomunidades  + "?" +  URLEncodedUtils.format(pares, "utf-8")); 
					response = client.execute(request);
					entity = response.getEntity();
					
					if (entity != null) {
						InputStream stream = entity.getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line + "\n");
						}
						stream.close();
						String responseString = sb.toString();
						GsonBuilder builder = new GsonBuilder();
						Gson gson = builder.create();
						JSONObject json = new JSONObject(responseString);
						//Aqui hay que cargar la lista de scores...
						ListaComunidades lista =  gson.fromJson(json.toString(), ListaComunidades.class);			
						publishProgress(lista);
					}

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}catch (JSONException e) {
					e.printStackTrace();
				}

	    		return true;
	    	}

	    	@Override
	    	protected void onProgressUpdate(ListaComunidades... comunidades) {

	    		ArrayList<LVI_generico> items = new ArrayList<LVI_generico>();
	    		for(Comunidad c : comunidades[0].getComunidades()){
	    		    items.add(new LVI_generico(c.getNombre()));     
	    		}
	    		
	    		// Código necesario para mostrar la información obtenida en la activity
	    		
	    		super.onProgressUpdate(comunidades);
	    		Thread.currentThread().interrupt();
	    	}
	    }	 
	 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////    Para hacer un PUT con la posicion donde me encuentro   /////////////////////////////////////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	 private class EnviarPosicionUsuario extends AsyncTask<Object, Void, Void> {
	    	
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Void doInBackground(Object... params) {
	            		
	    		HttpClient client = new DefaultHttpClient();
	    		HttpPut request = null;
	    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
	    		PosicionGPS posicion = (PosicionGPS) params[0];
	    		String email = (String) params[1];
	    		pares.add( new BasicNameValuePair("longitud", Double.toString( posicion.getLongitud() )  ));
	    		pares.add( new BasicNameValuePair("latitud",  Double.toString( posicion.getLatitud()  )  ));
	    		pares.add( new BasicNameValuePair("email", email ));
	    		 
	    			  
				try {
					android.util.Log.i("EnviarPosicionUsuario","Peticion a " + Repositorio.URLposicion);
					
					request = new HttpPut(Repositorio.URLposicion);
					request.setEntity(new UrlEncodedFormEntity(pares));				
					client.execute(request); // No hace falta el response... no hay respuesta a esta peticion desde el servidor				
					publishProgress();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	    	}
               
	      	protected void onProgressUpdate(Void... values) {      		  		
	    		super.onProgressUpdate(values);
	    		Thread.currentThread().interrupt();
	    	}
	    }
	 

	 
 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////    Para hacer un PUT con la posicion donde me encuentro   /////////////////////////////////////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	 private class EnviarAltaUsuario extends AsyncTask<Usuario, Void, Void> {
	    	
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Void doInBackground(Usuario...params) {
	            		
	    		HttpClient client = new DefaultHttpClient();
	    		HttpPut request = null;
	    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
	    		
                Usuario usuario = params[0];
	    		 	    			  
				try {
					Gson gson = new Gson();
					pares.add( new BasicNameValuePair("usuario", gson.toJson(usuario) ) );
					android.util.Log.i("EnviarAltaUsuario","Peticion a " + Repositorio.URLusuarios);
					
					request = new HttpPut(Repositorio.URLusuarios);
					request.setEntity(new UrlEncodedFormEntity(pares));				
					client.execute(request); // No hace falta el response... no hay respuesta a esta peticion desde el servidor				
					publishProgress();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	    	}
               
	      	protected void onProgressUpdate(Void... values) {      		  		
	    		super.onProgressUpdate(values);
	    		Thread.currentThread().interrupt();
	    	}
	    }
	 
	 
	 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////    Para hacer un PUT con una suscripcion del usuario en una comunidad   /////////////////////////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 	 
	 private class EnviarSuscripcionUsuarioComunidad extends AsyncTask<Object, Void, Void> {
	    	
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Void doInBackground(Object...params) {
	            		
	    		HttpClient client = new DefaultHttpClient();
	    		HttpPut request = null;
	    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
	    		
	    	    String email = (String) params[0];
	    	    Integer idComunidad = (Integer) params[1];
	    		pares.add( new BasicNameValuePair("email", email ) );
	    		pares.add( new BasicNameValuePair("idComunidad",  Integer.toString(idComunidad) ) );
	    		 	    			  
				try {
							
					android.util.Log.i("EnviarSuscripcionUsuarioComunidad","Peticion a " + Repositorio.URLsuscripciones);
					
					request = new HttpPut(Repositorio.URLsuscripciones);
					request.setEntity(new UrlEncodedFormEntity(pares));				
					client.execute(request); // No hace falta el response... no hay respuesta a esta peticion desde el servidor				
					publishProgress();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	    	}
               
	      	protected void onProgressUpdate(Void... values) {      		  		
	    		super.onProgressUpdate(values);
	    		Thread.currentThread().interrupt();
	    	}
	    }
	 


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////    Para hacer un DELETE de una suscripcion del usuario en una comunidad   /////////////////////////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 	 
	 private class EnviarDesuscripcionUsuarioComunidad extends AsyncTask<Object, Void, Void> {
	    	
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Void doInBackground(Object...params) {
	            		
	    		HttpClient client = new DefaultHttpClient();
	    		HttpDelete request = null;
	    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
	    		
	    	    String email = (String) params[0];
	    	    Integer idComunidad = (Integer) params[1];
	    		pares.add( new BasicNameValuePair("email", email ) );
	    		pares.add( new BasicNameValuePair("idComunidad",  Integer.toString(idComunidad) ) );
	    		 		    
				try {
// Hay que probar si funciona correctamente esto!!!!!!!!!!!!!!!!!!!
					android.util.Log.i("EnviarDesuscripcionUsuarioComunidad","Peticion a " + Repositorio.URLsuscripciones);					
					request = new HttpDelete(Repositorio.URLsuscripciones  + "?" + URLEncodedUtils.format(pares, "utf-8"));
							
					client.execute(request); // No hace falta el response... no hay respuesta a esta peticion desde el servidor				
					publishProgress();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	    	}
               
	      	protected void onProgressUpdate(Void... values) {      		  		
	    		super.onProgressUpdate(values);
	    		Thread.currentThread().interrupt();
	    	}
	    }
		 
	 


	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////      Obtiene los datos en el servidor de un usuario a partir de su email      ///////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 private class RecuperarDatosUsuario extends AsyncTask<String, Void, Usuario> {
	    	
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Usuario doInBackground(String... params) {
	    		Usuario usuario = null;         		
	       		HttpResponse response = null;
	       		HttpEntity entity = null;
	       		HttpClient client = new DefaultHttpClient();
	       		HttpGet request = null;
	       		List<NameValuePair> pares = new ArrayList<NameValuePair>();
	       		pares.add(new BasicNameValuePair("email", params[0])); // Email usuario

				try {
					
					android.util.Log.i("RecuperarDatosUsuario","Peticion a " + Repositorio.URLusuarios);
					
					request = new HttpGet(Repositorio.URLusuarios + "?" + URLEncodedUtils.format(pares, "utf-8")); 
					response = client.execute(request);
					entity = response.getEntity();
					
					if (entity != null) {
						InputStream stream = entity.getContent();
						BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
						StringBuilder sb = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							sb.append(line + "\n");
						}
						stream.close();
						String responseString = sb.toString();
						GsonBuilder builder = new GsonBuilder();
						Gson gson = builder.create();
						JSONObject json = new JSONObject(responseString);
						//Aqui hay que cargar la lista de scores...
						usuario =  gson.fromJson(json.toString(), Usuario.class);			
					
					}

				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}catch (JSONException e) {
					e.printStackTrace();
				}

	    		return usuario;
	    	}
		    	
	    	@Override
	    	protected void onPostExecute(Usuario result) {                
	    	    super.onPostExecute(result);
   	            //Se recupera el usuario, aqui hace lo que sea menester
	    	}
	    }	 	 
	 
	 
	 
 
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////    Para hacer un PUT con una repuesta a una pregunta del usuario   /////////////////////////////////////////   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 	 
	 private class EnviarRespuestaPreguntaUsuario extends AsyncTask<Object, Void, Void> {
	    
	    	@Override
	    	protected void onPreExecute() {    
	    		super.onPreExecute();
	    	}

	    	@Override
	    	protected Void doInBackground(Object...params) {
	            		
	    		HttpClient client = new DefaultHttpClient();
	    		HttpPut request = null;
	    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
	    		
	    		Integer idPregunta = (Integer) params[0];
	    	    String email = (String) params[1];
	    	    Integer idRespuesta = (Integer) params[2];
	    	    
	    	    pares.add( new BasicNameValuePair("idPregunta",  Integer.toString(idPregunta) ) );
	    	    pares.add( new BasicNameValuePair("email", email ) );	    			    		
	    		pares.add( new BasicNameValuePair("idRespuesta",  Integer.toString(idRespuesta) ) );
	    		 	    			  
				try {
							
					android.util.Log.i("EnviarRespuestaPreguntaUsuario","Peticion a " + Repositorio.URLpreguntas);					
					request = new HttpPut(Repositorio.URLpreguntas);
					request.setEntity(new UrlEncodedFormEntity(pares));				
					client.execute(request); // No hace falta el response... no hay respuesta a esta peticion desde el servidor				
					publishProgress();
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
	    	}
               
	      	protected void onProgressUpdate(Void... values) {      		  		
	    		super.onProgressUpdate(values);
	    		Thread.currentThread().interrupt();
	    	}
	    }
		 
	 
	 
	public void onClickEnviarPosicionUsuario(View v){
	 	 PosicionGPS posicion = new PosicionGPS(2.57d, 68.9d);
	 	 String email = "direccion@email.es";
		 Object[] parametros =  {posicion, email} ; // Respetar el orden
		 new EnviarPosicionUsuario().execute(parametros);
	}  
	
	
	public void onClickRecuperarComunidades(View v){
	 	 String email = "direccion@email.es";
	 	 String tipo = "A";
	 	 String longitud = Double.toString(345.435d);
	 	 String latitud = Double.toString(12.974d);
	 	
	 	 String[] parametros =  {email, tipo,longitud,latitud} ; // Respetar el orden
		 new RecuperarComunidades().execute(parametros);
	}  

	
	public void onClickRecuperarComunidadesSuscritas(View v){
	 	 String email = "direccion@email.es"; 	
	 	 String[] parametros =  {email} ; 
		 new RecuperarComunidadesSuscritas().execute(parametros);
	}  
	
	public void onClickEnviarAltaUsuario(View v){
 
 		 Usuario usuario = new Usuario("direccion@email.es","Asd_ID_REGISTRO_GCM_gasgdasdgasdgEE","46656745","nombre del usuario","N");	 	 
	 	 Usuario[] parametros =  {usuario} ;
		 new EnviarAltaUsuario().execute(parametros);
	}  
	
	
	public void onClickEnviarSuscripcionUsuarioComunidad(View v){
	 	 String email = "direccion@email.es"; 	
	 	 Integer idComunidad = 1;
	 	 Object[] parametros =  {email, idComunidad} ;  // Respetar el orden
		 new EnviarSuscripcionUsuarioComunidad().execute(parametros);
	}  
	

	public void onClickEnviarDesuscripcionUsuarioComunidad(View v){
	 	 String email = "direccion@email.es"; 	
	 	 Integer idComunidad = 1;
	 	 Object[] parametros =  {email, idComunidad} ;  // Respetar el orden
		 new EnviarDesuscripcionUsuarioComunidad().execute(parametros);
	}  

	public void onClickRecuperarDatosUsuario(View v){
	 	 String email = "direccion@email.es"; 	
	 	 String[] parametros =  {email} ; 
		 new RecuperarDatosUsuario().execute(parametros);
	}  	
	
	
	public void onClickEnviarRespuestaPreguntaUsuario(View v){
		 Integer idPregunta=1;
		 String email = "direccion@email.es";
		 Integer idRespuesta=2;
	 	 Object[] parametros =  {idPregunta,email,idRespuesta} ;  // Respetar el orden
		 new EnviarRespuestaPreguntaUsuario().execute(parametros);

	}  		

	
}
