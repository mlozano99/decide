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
import android.app.ProgressDialog;
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

	ListaComunidades lista_comunidades; 
	ListView lista;
	ArrayAdapter<String> adaptador;
	LVA_Comunidades listaAdaptador;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.l_listacomunidades);       

		String tipoComunidad = "A";   
		new RecuperarComunidades().execute( tipoComunidad );
       
		ListView lista = getListView();
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		        @Override
				public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
					// TODO Auto-generated method stub
				onListItemClick(v,pos,id);				
			}		   
		});
		super.onCreate(savedInstanceState);
        
	}

	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	protected void onListItemClick(View v,int pos,long id) {
		Intent i = new Intent(this, ComunidadesDetalleActivity.class);
		
		//LVI_generico item = listaAdaptador.getItem(pos);		
		Comunidad ele=  lista_comunidades.getComunidades().get(pos) ; 				
		i.putExtra("Comunidad", ele);

		startActivity(i);  
		
		this.overridePendingTransition(R.anim.a_entra,R.anim.a_sale); 		
		Log.i( BaseActivity.DEBUG_TAG, "onLongListItemClick id=" + id ); 
	}

	
	private class RecuperarComunidades extends AsyncTask<String, Void, ListaComunidades> {

		ProgressDialog ds; 
	    	@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
	    		
	    	ds = new ProgressDialog(ComunidadesActivity.this);	
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
    		pares.add(new BasicNameValuePair("tipo", params[0])); // Parametro tipo de comunidad
    		ListaComunidades lista_comu = new ListaComunidades();    		
    		
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
					lista_comu =  gson.fromJson(json.toString(), ListaComunidades.class);			
					return lista_comu;
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
			return lista_comu;
    	}
    	
	    	

		@Override
		protected void onPostExecute(ListaComunidades comunidades) {
			// TODO Auto-generated method stub
 
			if (comunidades!=null) { // || (!comunidades.getComunidades().isEmpty())) {
				lista_comunidades=comunidades;
				
				ArrayList<LVI_generico> items = new ArrayList<LVI_generico>();
	    		for(Comunidad c : comunidades.getComunidades()){
	    		    items.add(new LVI_generico(c.getNombre(),c.getIdComunidad()));     
	    		}	 	    		
	    		listaAdaptador = new LVA_Comunidades(ComunidadesActivity.this, R.layout.l_list_item, items); 
	    		setListAdapter(listaAdaptador);	 				
			}
			ds.dismiss();
			super.onPostExecute(comunidades);
		}
		
	}
}
