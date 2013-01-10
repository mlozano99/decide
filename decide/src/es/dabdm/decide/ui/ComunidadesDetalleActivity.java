package es.dabdm.decide.ui;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Comunidad;
import es.dabdm.decide.modelo.ListaComunidades;
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
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l_detallcomunidad);
		Intent i = getIntent();
		final Comunidad comu = (Comunidad) i.getSerializableExtra("Comunidad");	
		
	
		preferences = getSharedPreferences(Repositorio.PerfilPref, Context.MODE_PRIVATE);
        usu_iden = (preferences.getInt("identificado", 0)==1) ? true:false ; 
        
        
        
        /* **************************************************** */
        /* Comprobaciones de usuario identificado */
        	email = preferences.getString("email","");
		
		
		
		
		
		TextView t = (TextView) findViewById(R.id.tv_DCnombre);		
		t.setText( comu.getNombre() + comu.getIdComunidad() );
		
		TextView t2 = (TextView) findViewById(R.id.tv_DCdescripcion);		
		t2.setText( comu.getDescripcion());
		
		Button b1 = (Button) findViewById(R.id.b_suscribir);
		Button b2 = (Button) findViewById(R.id.b_desuscribir);

		b1.setVisibility( comu.getSuscrito().equalsIgnoreCase("N") ? View.VISIBLE :View.INVISIBLE );
		b2.setVisibility( comu.getSuscrito().equalsIgnoreCase("N") ? View.INVISIBLE :View.VISIBLE );
		
		
	    b1.setOnClickListener(new View.OnClickListener() 
		{	
	    	//String email = "jdiazcan@email.es"; 
	    	
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
	    	@Override
			public void onClick(View v) 
			{

			}	
		});

	    
		
		Log.i("A:", "llega extras vacioas ");
		
	}
	
	
	
	
	/* **************************************************************** */
    /* PUT con una suscripcion del usuario en una comunidad				*/   
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
						
				android.util.Log.i("EnviarSuscripcionUsuarioComunidad","Peticion a " + Repositorio.URLsuscripciones);
				
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
    		
    		Toast.makeText(getApplicationContext(),"Suscripción realizada para " + email,Toast.LENGTH_SHORT).show();
    		super.onPostExecute(result);
		}
    	
    	
    	/*
    	@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub

    		
    		Toast.makeText(getApplicationContext(),"Suscripción realizada para " + result,Toast.LENGTH_SHORT).show();
    		super.onPostExecute(result);
		}*/
      	
	}

	
	

	
	
	
	
	
	
	
	
}
