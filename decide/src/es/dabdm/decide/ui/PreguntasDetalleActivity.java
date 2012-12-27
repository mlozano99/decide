package es.dabdm.decide.ui;


import static es.dabdm.decide.GCMCommonUtilities.DISPLAY_MESSAGE_ACTION;
import static es.dabdm.decide.GCMCommonUtilities.EXTRA_MESSAGE;
import static es.dabdm.decide.GCMCommonUtilities.SENDER_ID;
import static es.dabdm.decide.GCMCommonUtilities.SERVER_URL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.android.gcm.GCMRegistrar;

import es.dabdm.decide.GCMServerUtilities;
import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Pregunta;
import es.dabdm.decide.util.MyHelperBBDD;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PreguntasDetalleActivity extends BaseActivity {

	    private Integer idPregunta;
	    TextView textoPregunta;
		private MyHelperBBDD myHelperBBDD;
	    
	    AsyncTask<Void, Void, Void> mRegisterTask;

	    private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
	            textoPregunta.append(newMessage + "\n");
	        }
	    };	    
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {	      
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.preguntasdetalle);
             
	    	
	    	Bundle bundle = getIntent().getExtras();
            if(bundle!=null){	    	 
	    	    this.idPregunta = bundle.getInt("idPregunta");
            }else{
            	this.idPregunta = obtienePreguntaUltimaNoficacion();
            }
            
	    	this.myHelperBBDD = new MyHelperBBDD(this);

	        Pregunta pregunta = cargaPregunta(this.idPregunta);
	        EditText textoPregunta = (EditText) findViewById(R.id.textoPregunta);
	        textoPregunta.setText(pregunta.getTexto());
	    	cargaRespuestasPregunta(this.idPregunta);
	    	
	    }
	    
		public Cursor getRespuestas(Integer idPregunta){
	    	SQLiteDatabase db = this.myHelperBBDD.getReadableDatabase();
	    	Cursor cursor = db.rawQuery("SELECT idRespuestaPosible,valor FROM respuestas WHERE idPregunta = "+ idPregunta +" ORDER BY idRespuestaPosible", null);
	    	startManagingCursor(cursor);
	    	return cursor;
	    }	    
	    
		/**
		 * Guardamos en BBDD que ha respondido el usuario
		 * @param idPregunta
		 * @param idRespuesta
		 */
		public void responderPregunta(Integer idPregunta, Integer idRespuesta){
			//Se responde a una pregunta
			if(idPregunta!=null && idRespuesta!=null && !idPregunta.equals(-1) && !idRespuesta.equals(-1)){
					SQLiteDatabase db = this.myHelperBBDD.getReadableDatabase();
					db.execSQL("UPDATE preguntas SET idRespuestaDada = "+ idRespuesta +" WHERE idPregunta = " + idPregunta +" ;");
                    
					if (  idPregunta.equals(obtienePreguntaUltimaNoficacion() ) ){
                    	 borraPreguntaDePreferencia();//Se da por respondida la notificacion de la pregunta
                    }
			}		
			
			//Hay que hacer una llamada al WS remoto para enviar la respuesta.....
		}
		
		
		
	    public void cargaRespuestasPregunta(Integer idPregunta){
	        TableLayout tabla = (TableLayout) findViewById(R.id.tablaRespuestas);
	        tabla.removeAllViews();
	        TableRow fila;
	        TextView texto;        
	        Cursor cursor = getRespuestas(idPregunta);
	        cursor.moveToFirst();
	        
	        while (!cursor.isAfterLast()) {
	            fila = new TableRow(this);

	            // Columna idRespuestaPosible
	            texto = new TextView(this);
	            texto.setText(  Integer.toString( cursor.getInt(0) ));
	            fila.addView(texto);	            
	            
	            // Columna valor
	            texto = new TextView(this);
	            texto.setText( cursor.getString(1));
	            fila.addView(texto);
            
	            //Aade la fila
	            tabla.addView(fila);
	            
	            cursor.moveToNext();
	          }
	    }
	    
	    /**
	     * Obtiene los datos de una pregunta desde la BBDD por su código
	     * @param idPregunta
	     * @return
	     */
	    @SuppressLint("SimpleDateFormat")
		public Pregunta cargaPregunta(Integer idPregunta){
	    	Pregunta pregunta = new Pregunta();
	    	SQLiteDatabase db = this.myHelperBBDD.getReadableDatabase();
			Cursor cursor = db.rawQuery("SELECT texto,idComunidad,fechaLimite FROM preguntas WHERE idPregunta = " + idPregunta, null);
			cursor.moveToFirst();	    	
	    	pregunta.setIdPregunta(idPregunta);
	    	pregunta.setTexto(cursor.getString(0));
	    	pregunta.setIdComunidad(cursor.getInt(1));

	    	String fechaLimiteTexto = cursor.getString(2);
	    	if(fechaLimiteTexto!=null && !"".equals(fechaLimiteTexto)){	    		
	    		try {
	    			 SimpleDateFormat dateFormat = new SimpleDateFormat(MyHelperBBDD.FORMATO_FECHA);
					 pregunta.setFechaLimite(dateFormat.parse(fechaLimiteTexto));
				} catch (ParseException e) {
					pregunta.setFechaLimite(null);
				}
	    	}	    	    	
	    	return pregunta;	    	
	    }
	    
	    
	    public Integer obtieneUnaPreguntaPorResponderEnBBDD(){
			
	    	Integer idPreguntaEncontrada = -1;
	    	SQLiteDatabase db = this.myHelperBBDD.getReadableDatabase();
			Cursor cursor = db.rawQuery("SELECT max(idPregunta) AS id FROM preguntas WHERE idRespuestaDada IS NULL ", null);
			cursor.moveToFirst();
			idPreguntaEncontrada = cursor.getInt(0);			
			return idPreguntaEncontrada;
	    }
	    
	    
		/**
		 * Cuando recibe la notificación se deja en las preferencias compartidas
		 * @return
		 */
		public Integer obtienePreguntaUltimaNoficacion() {
			try {
				SharedPreferences preferences = getSharedPreferences("estado.xml",Context.MODE_PRIVATE);		
				return preferences.getInt("numAyudas",-1);												
			} catch (Exception e) {
				return -1;
			}		
			
		}	    

		public void borraPreguntaDePreferencia(){
			SharedPreferences preferences = getSharedPreferences("estado.xml",Context.MODE_PRIVATE);		
			//Borra la última pregunta
			Editor editor = preferences.edit();	
            editor.remove("idPregunta");		
			editor.commit();
		}
		
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.options_menu, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch(item.getItemId()) {
	            case R.id.options_clear:
	                textoPregunta.setText(null);
	                return true;
	            case R.id.options_exit:
	                finish();
	                return true;
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }

	    @Override
	    protected void onDestroy() {
	        if (mRegisterTask != null) {
	            mRegisterTask.cancel(true);
	        }
	        unregisterReceiver(mHandleMessageReceiver);
	        GCMRegistrar.onDestroy(this);
	        super.onDestroy();
	    }

	    
       public void registrarGCM(){
    	  
    	    checkNotNull(SERVER_URL, "SERVER_URL");
	        checkNotNull(SENDER_ID, "SENDER_ID");
	        // Make sure the device has the proper dependencies.
	        GCMRegistrar.checkDevice(this);
	        // Make sure the manifest was properly set - comment out this line
	        // while developing the app, then uncomment it when it's ready.
	        GCMRegistrar.checkManifest(this);
	        setContentView(R.layout.main);
	        textoPregunta = (TextView) findViewById(R.id.textoPregunta);
	        registerReceiver(mHandleMessageReceiver, new IntentFilter(DISPLAY_MESSAGE_ACTION));
	       
	        final String regId = GCMRegistrar.getRegistrationId(this);
	        if (regId.equals("")) {
	            // Automatically registers application on startup.
	            GCMRegistrar.register(this, SENDER_ID);
	        } else {
	            // Device is already registered on GCM, check server.
	            if (GCMRegistrar.isRegisteredOnServer(this)) {
	                // Skips registration.
	                textoPregunta.append(getString(R.string.already_registered) + "\n");
	            } else {
	                // Try to register again, but not in the UI thread.
	                // It's also necessary to cancel the thread onDestroy(),
	                // hence the use of AsyncTask instead of a raw thread.
	                final Context context = this;
	                mRegisterTask = new AsyncTask<Void, Void, Void>() {

	                    @Override
	                    protected Void doInBackground(Void... params) {
	                        boolean registered = GCMServerUtilities.register(context, regId);
	                        // At this point all attempts to register with the app
	                        // server failed, so we need to unregister the device
	                        // from GCM - the app will try to register again when
	                        // it is restarted. Note that GCM will send an
	                        // unregistered callback upon completion, but
	                        // GCMIntentService.onUnregistered() will ignore it.
	                        if (!registered) {
	                            GCMRegistrar.unregister(context);
	                        }
	                        return null;
	                    }

	                    @Override
	                    protected void onPostExecute(Void result) {
	                        mRegisterTask = null;
	                    }

	                };
	                mRegisterTask.execute(null, null, null);
	            }
	        }
       }
	    
	    
	    private void checkNotNull(Object reference, String name) {
	        if (reference == null) {
	            throw new NullPointerException(getString(R.string.error_config, name));
	        }
	    }

	    
	
}
