package es.dabdm.decide.ui;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONStringer;



import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Comunidad;
import es.dabdm.decide.modelo.ListaComunidades;
import es.dabdm.decide.util.MCuenta;
import es.dabdm.decide.util.Repositorio;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ComunidadesDetalleActivity extends BaseActivity {

	SharedPreferences preferences ;
	boolean usu_iden;
	String email;
	MCuenta cuenta;
	Comunidad comu;
	Button b1,b2;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l_detallcomunidad);
		Intent i = getIntent();
		comu = (Comunidad) i.getSerializableExtra("Comunidad");	
		cuenta = MCuenta.getMC(this);
		
        
        /* **************************************************** */
        /* Comprobaciones de usuario identificado */

		
		TextView t = (TextView) findViewById(R.id.tv_DCnombre);		
		t.setText( comu.getNombre() + comu.getIdComunidad() );
		
		TextView t2 = (TextView) findViewById(R.id.tv_DCdescripcion);		
		t2.setText( comu.getDescripcion());

		if (cuenta.usu_identificado()){

			b1 = (Button) findViewById(R.id.b_suscribir);
			b2 = (Button) findViewById(R.id.b_desuscribir);
			
			boolean suscrita=false;			
			String CoSuscrita = cuenta.getString("CoSuscritas");			
			suscrita = CoSuscrita.contains(comu.getIdComunidad().toString()) ? true : false ;  
			
			b1.setVisibility( suscrita ? View.GONE :View.VISIBLE );
			b2.setVisibility( suscrita ? View.VISIBLE :View.GONE );
			

		}
		
		Log.i("A:", "llega extras vacioas ");
		
	}
	
	/* ************************************************* */
	/* Botones del layout */
	/* ************************************************* */
	/* Enviar suscripción de un usuario a una comunidad */
	public void suscribirseComunidad(View view){
		
    	String email = cuenta.getString("email"); 
	 	Integer idComunidad = comu.getIdComunidad();
	 	Object[] parametros =  {email, idComunidad} ; 
    	
	 	new EnviarSuscripcionUsuarioComunidad().execute(parametros);
		
	}
	
	/* Enviar desuscripción de un usuario a una comunidad */	
	public void desuscribirseComunidad(View view){
		
    	String email = cuenta.getString("email"); 
	 	Integer idComunidad = comu.getIdComunidad();
	 	Object[] parametros =  {email, idComunidad} ; 
    	
	 	new EnviarDesuscripcionUsuarioComunidad().execute(parametros);
		
	}
	
	
	
	
	/* **************************************************************** */
    /* PUT con una SUScripcion del usuario en una comunidad				*/   
	/* **************************************************************** */
	private class EnviarSuscripcionUsuarioComunidad extends AsyncTask<Object, Void, Object[]> {
    	
    	@Override
    	protected void onPreExecute() {    
    		super.onPreExecute();
    	}

    	@Override
    	protected Object[] doInBackground(Object...params) {
            		
    		HttpClient client = new DefaultHttpClient();
    		HttpPut request = null;
    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
    		
    	    String email = (String) params[0];
    	    Integer idComunidad = (Integer) params[1];
    		pares.add( new BasicNameValuePair("email", email ) );
    		pares.add( new BasicNameValuePair("idComunidad",  Integer.toString(idComunidad) ) );
    		 	    			  
			try {
						
				android.util.Log.i("EnviarSuscripcionUsuarioComunidad","Peticion a " + Repositorio.URLsuscripciones );
				
				request = new HttpPut(Repositorio.URLsuscripciones);
				request.setEntity(new UrlEncodedFormEntity(pares));				
				client.execute(request);				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return params;
    	}
    	

    	@Override
		protected void onPostExecute(Object[] result) {
			// TODO Auto-generated method stub

    		String email = result[0].toString();    		
    		String comu_suscritas = cuenta.getString("CoSuscritas");
    		comu_suscritas= comu_suscritas.length()==0 ? comu.getIdComunidad().toString() : "," + comu.getIdComunidad().toString() ;
   		
    		cuenta.putString("CoSuscritas", comu_suscritas);
    	
    		//boolean suscrita = comu_suscritas.contains(comu.getIdComunidad().toString());
			b1.setVisibility(View.GONE);
			b2.setVisibility(View.VISIBLE);
    		
    		
    		
    		Toast.makeText(getApplicationContext(),"Suscripción realizada para " + result[0] + " "+ result[1] ,Toast.LENGTH_SHORT).show();
    		super.onPostExecute(result);
		}
    	
      	
	}
	
	
	
	//EnviarDesuscripcionUsuarioComunidad

	
	/* **************************************************************** */
    /* DELETE -- Elimina una suscripción de un usuario a una comunidad  */   
	/* **************************************************************** */
	private class EnviarDesuscripcionUsuarioComunidad extends AsyncTask<Object, Void, Object[]> {
    	
    	@Override
    	protected void onPreExecute() {    
    		super.onPreExecute();
    	}

    	@Override
    	protected Object[] doInBackground(Object...params) {
            		
    		HttpClient client = new DefaultHttpClient();
    		HttpDelete request = null;
    		List<NameValuePair> pares = new ArrayList<NameValuePair>();
    		
    	    String email = (String) params[0];
    	    Integer idComunidad = (Integer) params[1];
    		pares.add( new BasicNameValuePair("email", email ) );
    		pares.add( new BasicNameValuePair("idComunidad",  Integer.toString(idComunidad) ) );
    		 	    			  
			try {
						
				android.util.Log.i("EnviarSuscripcionUsuarioComunidad","Peticion a " + Repositorio.URLsuscripciones 
						+ "?" + URLEncodedUtils.format(pares, "utf-8"));
				request = new HttpDelete(Repositorio.URLsuscripciones);
				//request.setEntity(new UrlEncodedFormEntity(pares));			//+ "?" + URLEncodedUtils.format(pares, "utf-8"));
				client.execute(request);								
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return params;
    	}
    	

    	/* Borrar el id de comunidad del string de comunidades suscritas que tenemos en el perfil
    	 */
    	@Override
		protected void onPostExecute(Object[] result) {
			// TODO Auto-generated method stub
    		
    		//String email = result[0].toString();    		
    		String comu_suscritas = cuenta.getString("CoSuscritas");
    		
    		List<String> ls_comu = new ArrayList<String>(Arrays.asList(comu_suscritas.split(",")));
    		ls_comu.remove(comu.getIdComunidad().toString());
    		
    		StringBuilder sal= new StringBuilder();
    		for (String s: ls_comu) {
    			if (ls_comu.size()==1) sal.append(s);
    			else ls_comu.add("," + s);
    		}
    		cuenta.putString("CoSuscritas",sal.toString());
    		
    		
    		/* Activa la suscripcion */
    		boolean suscrita = ls_comu.contains(comu.getIdComunidad().toString());
			b1.setVisibility(View.VISIBLE);
			b2.setVisibility(View.GONE);
    		
    		
    		Toast.makeText(getApplicationContext(),"Suscripción realizada para " + email,Toast.LENGTH_SHORT).show();
    		super.onPostExecute(result);
		}
    	
      	
	}

	
}


/*b1.setOnClickListener(new View.OnClickListener() 
{	
	String email = cuenta.getString("email"); 
 	Integer idComunidad = comu.getIdComunidad();
 	Object[] parametros =  {email, idComunidad} ; 
	
 	@Override
	public void onClick(View v) 
	{
		new EnviarSuscripcionUsuarioComunidad().execute(parametros);
	}	
});


b2.setOnClickListener(new View.OnClickListener() 
{			
	
	String email = cuenta.getString("email"); 
 	Integer idComunidad = comu.getIdComunidad();
 	Object[] parametros =  {email, idComunidad} ; 
	
	
	@Override
	public void onClick(View v) 
	{
		new EnviarDesuscripcionUsuarioComunidad().execute(parametros);
	}	
});*/
