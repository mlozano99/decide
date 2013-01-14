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
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.dabdm.decide.R;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import es.dabdm.decide.modelo.Comunidad;
import es.dabdm.decide.modelo.ListaComunidades;
import es.dabdm.decide.util.LVA_Comunidades;
import es.dabdm.decide.util.LVI_generico;
import es.dabdm.decide.util.Repositorio;
import es.dabdm.decide.util.MCuenta;

public class PerfilActivity extends ListActivity{
	boolean usu_iden;
	SharedPreferences preferences;
	MCuenta cuenta ;
	ListaComunidades lista_comunidades; 
	LVA_Comunidades listaAdaptador;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.l_perfil);
		
		cuenta = MCuenta.getMC(this);
        
		TextView tv_nombre = (TextView) this.findViewById(R.id.tv_nombreusuario);
		TextView tv_email  = (TextView) this.findViewById(R.id.tv_emailusuario);
		TextView tv_telefono = (TextView) this.findViewById(R.id.tv_telefonousuario);
		
		if (cuenta.usu_identificado()) { 
		
			tv_nombre.setText(cuenta.getString("nombre"));
			tv_email.setText(cuenta.getString("email"));
			tv_telefono.setText(cuenta.getString("telefono"));
			
			comunidades_suscritas();
		}
		super.onCreate(savedInstanceState);	
	}

		
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
	
	/* ************************************************* */
	/* Botones del layout */
	/* ************************************************* */
	public void b_cerrar_sesion(View view) {
		cuenta.cerrarSesion();
	   	this.startActivity(new Intent(this, CuentaActivity.class)); 
	   	this.finish();
	   	
	 }
	
	/* Realizar la petición de las comunidades suscritas
	 * Si llego a ejecutar este método es que estoy identificado
	 */
	private void comunidades_suscritas(){

		 	 String email = cuenta.getString("email"); 	
		 	 String[] parametros =  {email} ; 
			 new RecuperarComunidadesSuscritas().execute(parametros);  
		
	}
	
	
	

	/* *********************************************************************** */
    /* GET Obtiene las comunidades suscritas por el usuario */   
	/* *********************************************************************** */
	private class RecuperarComunidadesSuscritas extends AsyncTask<String, Void, ListaComunidades> {

		ProgressDialog ds; 
	    	@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
	    		
	    	ds = new ProgressDialog(PerfilActivity.this);	
	    	ds.setProgress(ProgressDialog.STYLE_SPINNER);
	    	ds.setMessage("Procesando");	    	
	    	ds.show();
	    	super.onPreExecute();
		}

		@Override
    	protected ListaComunidades doInBackground(String... params) {
            		
    		HttpResponse response = null;
    		HttpEntity entity = null;
    		HttpClient client = new DefaultHttpClient();
    		HttpGet request = null;
    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
       		pares.add(new BasicNameValuePair("email", params[0])); // Email usuario
    		ListaComunidades lista_comu = new ListaComunidades();    		
    		Log.i("RecuperarComunidadesSuscritas","Peticion a " + Repositorio.URLsuscripciones + "?" + URLEncodedUtils.format(pares, "utf-8"));
    		
			try {
				
				request = new HttpGet(Repositorio.URLsuscripciones + "?" + URLEncodedUtils.format(pares, "utf-8"));
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
					lista_comu =  gson.fromJson(json.toString(), ListaComunidades.class);			
					return lista_comu;
				}
				

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
					e.printStackTrace();
			}
			
			return lista_comu;
    	}
		 
		@Override
		protected void onPostExecute(ListaComunidades comunidades) {
			// TODO Auto-generated method stub
			try {
				if (comunidades!=null) { 
					lista_comunidades=comunidades;
					
					ArrayList<LVI_generico> items = new ArrayList<LVI_generico>();
		    		for(Comunidad c : comunidades.getComunidades()){
		    		    items.add(new LVI_generico(c.getNombre(),c.getIdComunidad()));     
		    		}	 	    		
		    		listaAdaptador = new LVA_Comunidades(PerfilActivity.this, R.layout.l_list_item, items); 
		    		setListAdapter(listaAdaptador);	 				
				}
			}			
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {			
				ds.dismiss();
				super.onPostExecute(comunidades);
			}
		}
	} //Fin asyncTask

	
	
	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.l_perfil, menu);
		return true;
	}
*/
	
	








}
