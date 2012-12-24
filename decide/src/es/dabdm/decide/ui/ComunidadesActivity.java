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
import es.dabdm.decide.modelo.Comunidad;
import es.dabdm.decide.modelo.ListaComunidades;
import es.dabdm.decide.util.LVA_Comunidades;
import es.dabdm.decide.util.LVI_generico;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;



import static es.dabdm.decide.util.Repositorio.URLcomunidades;



public class ComunidadesActivity extends ListActivity {

	ListView lista;
	ArrayAdapter<String> adaptador;
	//ArrayList datos;
	LVA_Comunidades listaAdaptador;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.l_listacomunidades);		   
       
       String tipoComunidad = "A";//Hay que establecer los códigos posibles

       new RecuperarComunidades().execute( tipoComunidad );
        
       
       
	   ListView lista = getListView();
	   lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
				// TODO Auto-generated method stub
				onLongListItemClick(v,pos,id);
				return true;
			}		   
	   	});
	   
	}
	
	protected void onLongListItemClick(View v,int pos,long id){
		Intent i = new Intent(this,ComunidadesDetalleActivity.class);
		
		LVI_generico item = listaAdaptador.getItem(pos);
		i.putExtra("Comunidad",item.getTitle());		
		startActivity(i);  
		
		this.overridePendingTransition(R.anim.a_entra,R.anim.a_sale); 		
		Log.i( BaseActivity.DEBUG_TAG, "onLongListItemClick id=" + id ); 
		
	}
	
	
	
	
	 
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
        		pares.add(new BasicNameValuePair("tipo", params[0])); // Parametro tipo de comunidad
        		
        		
				try {
					
					request = new HttpGet(URLcomunidades); // + URLEncodedUtils.format(pares, "utf-8"));
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
	    		
	    		listaAdaptador = new LVA_Comunidades(ComunidadesActivity.this, R.layout.l_list_item, items); 
	    		setListAdapter(listaAdaptador);	 
	    		
	    		super.onProgressUpdate(comunidades);
	    		Thread.currentThread().interrupt();
	    	}
	    }

}
