/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.dabdm.decide;

import static es.dabdm.decide.GCMCommonUtilities.SENDER_ID;
import static es.dabdm.decide.GCMCommonUtilities.displayMessage;

import java.text.SimpleDateFormat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.dabdm.decide.modelo.Pregunta;
import es.dabdm.decide.modelo.RespuestaPosible;
import es.dabdm.decide.ui.DemoActivity;
import es.dabdm.decide.ui.PreguntasDetalleActivity;
import es.dabdm.decide.util.MyHelperBBDD;

/**
 * IntentService responsible for handling GCM messages.
 */
public class GCMIntentService extends GCMBaseIntentService {

    @SuppressWarnings("hiding")
    private static final String TAG = "GCMIntentService";
       
    
    public GCMIntentService() {
        super(SENDER_ID);
    }

    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, getString(R.string.gcm_registered));
        GCMServerUtilities.register(context, registrationId);
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        if (GCMRegistrar.isRegisteredOnServer(context)) {
            GCMServerUtilities.unregister(context, registrationId);
        } else {
            // This callback results from the call to unregister made on
            // ServerUtilities when the registration to the server failed.
            Log.i(TAG, "Ignoring unregister callback");
        }
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
       // String message = getString(R.string.gcm_message);
        
        Object datosPregunta = intent.getExtras().get("pregunta");
        if(datosPregunta!=null && datosPregunta instanceof String){

	        Gson gson = new GsonBuilder().setDateFormat(MyHelperBBDD.FORMATO_FECHA).create();	
	        Pregunta pregunta = gson.fromJson((String) datosPregunta,Pregunta.class);
	     
	        //Guarda el ID de la pregunta notificada en las preferencias 
	        saveData(pregunta.getIdPregunta());
	        //Guarda los datos de la pregunta en BBDD
	        guardarPreguntaBBDD(pregunta);
        
            displayMessage(context, pregunta.getTexto() );
            generateNotification(context, pregunta.getTexto(),pregunta.getIdPregunta());   
        }
        //displayMessage(context, message);
       // generateNotification(context, message);
    }

    
    
    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message,-1);
    }

    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message,Integer idPregunta) {
      
    	int icon = R.drawable.ic_stat_gcm;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, PreguntasDetalleActivity.class);
        notificationIntent.putExtra("idPregunta", idPregunta);
        
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        
        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }

    
    /**
     * Guarda en las preferencias la pregunta que acaba de recibir como notificación
     * @param idPregunta
     */
	public void saveData(Integer idPregunta) {
		
		SharedPreferences preferences = getSharedPreferences("estado.xml",Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putInt("idPregunta", idPregunta );				
		editor.commit();
	}

	
	/**
	 * Guarda la pregunta y sus respuestas en BBDD
	 * @param pregunta Datos de la pregunta recibida
	 */
	public void guardarPreguntaBBDD(Pregunta pregunta){
		 
		 if(pregunta==null || pregunta.getRespuestasPosibles()==null || pregunta.getRespuestasPosibles().size()==0){
			 return;
		 }
		 String insert;
		 try {
			 MyHelperBBDD myHelperBBDD;
			 myHelperBBDD = new MyHelperBBDD(this);
			 SQLiteDatabase db = myHelperBBDD.getWritableDatabase();		 
			 //db.beginTransaction();
			 SimpleDateFormat dateFormat = new SimpleDateFormat(MyHelperBBDD.FORMATO_FECHA);		 
			 insert ="INSERT INTO preguntas  (idPregunta,texto,idComunidad,fechaLimite) VALUES ("+ pregunta.getIdPregunta() +",'"+pregunta.getTexto()+"',"+pregunta.getIdComunidad()+",'"+ dateFormat.format(pregunta.getFechaLimite()) +"');"; 
			 db.execSQL(insert);
			 
			 for(RespuestaPosible respuesta : pregunta.getRespuestasPosibles()){
				 insert = "INSERT INTO respuestas (idPregunta,idRespuestaPosible,valor) VALUES ("+ pregunta.getIdPregunta() +","+respuesta.getIdRespuestaPosible()+",'"+respuesta.getValor()+"');";
				 db.execSQL(insert);
			 }
			// db.endTransaction();
			 //db.close();
		} catch (Exception e) {
			e.printStackTrace();
			if(e!=null){
			    Log.i("Error guardarPreguntaBBDD",  e.getMessage());
			}
		}
	}
	
    
}
