package es.dabdm.decide.ui;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Pregunta;
import es.dabdm.decide.modelo.RespuestaPosible;
import es.dabdm.decide.util.LVA_Comunidades;
import es.dabdm.decide.util.LVI_generico;
import es.dabdm.decide.util.MyHelperBBDD;
import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PreguntasActivity extends ListActivity {

	
	private MyHelperBBDD myHelperBBDD;	
	private List<Pregunta> preguntas;
	LVA_Comunidades listaAdaptador;
		
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l_preguntas);        	       
		this.myHelperBBDD = new MyHelperBBDD(this);
		preguntas = cargaPreguntas(600);//
		
		ListView lista = getListView();
		lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		        @Override
				public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
				onListItemClick(v,pos,id);				
			}		   
		});	    	
			
		ArrayList<LVI_generico> items = new ArrayList<LVI_generico>();
    	for( Pregunta pregunta : preguntas){
    		    items.add(new LVI_generico(pregunta.getTexto(),pregunta.getIdPregunta()));     		    
    	}	 	    			 
    	
    	listaAdaptador = new LVA_Comunidades(PreguntasActivity.this, R.layout.l_list_item, items); 
    	setListAdapter(listaAdaptador);	

	}

	
	
	protected void onListItemClick(View v,int pos,long id) {			
		Pregunta preguntaSeleccionada= preguntas.get(pos) ;		
		Log.i( BaseActivity.DEBUG_TAG, "onLongListItemClick preguntaSeleccionada=" + preguntaSeleccionada.getTexto() ); 
	}		

	
	
	public List<Pregunta> cargaPreguntasPorResponder(Integer idComunidad){
	    String sql = "SELECT texto,idPregunta,fechaLimite,idRespuestaDada FROM preguntas WHERE idRespuestaDada IS NULL AND idComunidad = " + idComunidad;
		return cargaPreguntas(idComunidad, sql);
	}
		
	
	public List<Pregunta> cargaPreguntasRespondidas(Integer idComunidad){
	    String sql = "SELECT texto,idPregunta,fechaLimite,idRespuestaDada FROM preguntas WHERE idRespuestaDada IS NOT NULL AND idComunidad = " + idComunidad;
		return cargaPreguntas(idComunidad, sql);
	}
	
	
	public List<Pregunta> cargaPreguntas(Integer idComunidad){
	    String sql = "SELECT texto,idPregunta,fechaLimite,idRespuestaDada FROM preguntas WHERE idComunidad = " + idComunidad;
		return cargaPreguntas(idComunidad, sql);
	}
	
	
	@SuppressLint("SimpleDateFormat")
	public List<Pregunta> cargaPreguntas(Integer idComunidad,String sql){
    
		List<Pregunta> preguntas = new ArrayList<Pregunta>();
		List<RespuestaPosible> respuestasPosibles;
		Pregunta pregunta;
		RespuestaPosible respuesta;
		
		
    	SQLiteDatabase db = this.myHelperBBDD.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT texto,idPregunta,fechaLimite,idRespuestaDada FROM preguntas WHERE idComunidad = " + idComunidad, null);
		cursor.moveToFirst();	    	
        //Recorre todas las preguntas y saca su información de BBDD
		while (!cursor.isAfterLast()) {
		
			    pregunta = new Pregunta();
				pregunta.setIdComunidad(idComunidad);
		    	pregunta.setTexto(cursor.getString(0));
		    	pregunta.setIdPregunta(cursor.getInt(1));    		
		    	try {
					pregunta.setIdRespuestaDada(cursor.getInt(3));//Puede ser nulo
				} catch (Exception e) {
					pregunta.setIdRespuestaDada(null);//Salta excepcion si es null en BBDD
				}
				String fechaLimiteTexto = cursor.getString(2);
		    	if(fechaLimiteTexto!=null && !"".equals(fechaLimiteTexto)){	    		
		    		try {
		    			 SimpleDateFormat dateFormat = new SimpleDateFormat(MyHelperBBDD.FORMATO_FECHA);
						 pregunta.setFechaLimite(dateFormat.parse(fechaLimiteTexto));
					} catch (ParseException e) {
						pregunta.setFechaLimite(null);
					}
		    	}	  
		    	preguntas.add(pregunta);
	            cursor.moveToNext();
	     }
		
    	cursor.close();
     	
    	//Para cada pregunta saca sus respuestas y las asigna a la pregunta
    	for(Pregunta p : preguntas){
		    	cursor = db.rawQuery("SELECT idRespuestaPosible,valor FROM respuestas WHERE idPregunta = "+ p.getIdPregunta() +" ORDER BY idRespuestaPosible", null);
		     	cursor.moveToFirst();
			        		     	
				respuestasPosibles = new ArrayList<RespuestaPosible>();				
				
		        while (!cursor.isAfterLast()) {
		        	respuesta = new RespuestaPosible();
		        	respuesta.setIdRespuestaPosible( cursor.getInt(0) );
		        	respuesta.setValor( cursor.getString(1) );	
		        	respuestasPosibles.add(respuesta);
		            cursor.moveToNext();
		        }	    	
			
			    p.setRespuestasPosibles(respuestasPosibles); 	//Asigna las respuestas posibles a la pregunta
			    cursor.close();
    	}
    	
    	return preguntas;	    	
    }
	
	
	
	
}
