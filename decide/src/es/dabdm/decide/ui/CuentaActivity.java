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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Usuario;
import es.dabdm.decide.util.MCuenta;
import es.dabdm.decide.util.MDialogos;
import es.dabdm.decide.util.Repositorio;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CuentaActivity extends BaseActivity {
	MDialogos alerta;
	Bundle d_usuario;
	boolean showRegistro;
	boolean usu_iden;
	SharedPreferences preferences;
	Usuario perfil_dusuario;
	MCuenta cuenta ;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
				
        cuenta = MCuenta.getMC(this);        
        this.alerta=new MDialogos(this);
        
        if (cuenta.usu_identificado()) 
    		usuario_si_esta_identificado();   
        	else 
            usuario_no_esta_identificado();
        super.onCreate(savedInstanceState);
	}	
	
	
	/* ***************************************************************************
	 * Usuario NO está identificado 
	 * ***************************************************************************
	 */
	/* ***************************************************************************
	/* Mostrar el layout cuenta que es el que inicia el login */
	private void usuario_no_esta_identificado(){
    	setContentView(R.layout.l_login);

	}
	/* ***************************************************************************
	 * Usuario SI está identificado 
	 * ***************************************************************************
	 */
	/* ***************************************************************************
	 * Si estoy identificado mostraré los datos del perfil 
	 * Esto hay que mirarlo para determinar si va en nueva activity o en la misma 
	 * **
	 */	
	private void usuario_si_esta_identificado(){
		startActivity(
				new Intent(CuentaActivity.this, PerfilActivity.class));
		this.finish();
	}	
	
	/* ***************************************************************************
	 * Comprueba las credenciales de usuario que acaba de hacer login. 	
	 * Enviar petición las credenciales de usuario. 					
	 * Simularemos un inicio de sesión enviando solo el email como 		
	 * identificador único.
	 * **  				
	 */							
	private void enviarCredenciales() {		
		EditText email = (EditText) this.findViewById(R.id.ET_Mail);
		
		this.alerta.showMensajeEstandard(this, "Envio login");
		new RecuperarDatosUsuario().execute(email.getText().toString());
	}
	
	/* ***************************************************************************
	 * ComprobarUsuario comprueba las credenciales enviadas por enviarCredenciales	 * 
	 * Decimos que está identificado si el tfno de recuperado es 9999999 
	 * Guardamos en preferences el objeto usuario que ha recuperado
	 */
	private void comprobarUsuario(Usuario usu) {		
	
		if (usu !=null && usu.getTelefono().toString().compareTo("9999999")!=0 ) {			
			
			cuenta.putString("email", usu.getEmail().toString());
			cuenta.putString("nombre", usu.getNombre().toString());
			cuenta.putString("telefono", usu.getTelefono().toString());
			cuenta.putString("publicidad", usu.getPublicidad().toString());	
			cuenta.putString("idRestistration", usu.getIdRegistration().toString());
			cuenta.putInt("identificado", 1);			
	
			Toast.makeText(this, "Mail "+ usu.getEmail().toString(),Toast.LENGTH_SHORT).show();
			
			startActivity(
					new Intent(CuentaActivity.this, PerfilActivity.class));
			this.finish();
		}
		else {
			
			this.alerta.showMensajeEstandard(this,
					"No se ha podido comprobar la identidad del usuario. Revise sus credenciales");			
		}
	}
	
		
	/* ************************************************************ 
	 * Gestión de los Eventos para Iniciar sesión y Registrarse
	 * **
	 */
	
	/* Pertenece al layout l_cuenta.xml */
	public void b_iniciar_sesion(View view) {
		 this.enviarCredenciales();
	 }
	
	/* Pertenece al layout l_cuenta.xml */
	/* Muestra el formulario para dar de alta a una persona (l_usuario_alta) */
	
	public void b_registrarse(View view) {
		this.showRegistro=true;			
		this.alerta.showRegistro();
	}	
	
	/* 
	 * Enviar datos de registro al servidor
	 * Tal y como está montado debería hacer una petición para comprobar si existe el usuario antes de registrarse
	 * Luego si cumple debería enviar la petición de registro 
	 */
	final public void p_resultado_registrarse(Usuario usu) {
		Usuario[] parametros =  {usu};
		
		new EnviarAltaUsuario().execute(parametros);
		Toast.makeText(this,"alta de usuario", Toast.LENGTH_SHORT).show();
	}
 
	 
	
	/* ************************************************************************************************** */
		
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}	 
	 
	 
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		//startActivity(new Intent(this,MainActivity.class));
		super.onRestart();
	}



	/* *********************************************************************** */
    /* GET Obtiene los datos en el servidor de un usuario a partir de su email */   
	/* *********************************************************************** */
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
			
			request = new HttpGet(Repositorio.URLusuarios + "?" + URLEncodedUtils.format(pares, "ISO-8859-1")); 
			
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
			comprobarUsuario(result);			
			super.onPostExecute(result);	
		}
	}	 	 
	
	/* ************************************************************************************************* */
	/* Enviar PUT. Alta de usuario. Asumimos que funciona bien. Y no gestionamos respuesta del servidor.
	/* ************************************************************************************************* */
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
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		    
		@Override
		protected void onPostExecute(Void result) {                
			super.onPostExecute(result);
			
		}
		
	} //Fin EnviarAltaUsuario
	
	
}
