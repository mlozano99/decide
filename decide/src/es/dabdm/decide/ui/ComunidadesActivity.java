package es.dabdm.decide.ui;


import java.util.ArrayList;

import es.dabdm.decide.R;
import es.dabdm.decide.R.layout;
import es.dabdm.decide.util.LVA_Comunidades;
import es.dabdm.decide.util.LVI_generico;
import android.util.*;
import android.app.ListActivity;
import android.location.Location;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.view.View;


public class ComunidadesActivity extends ListActivity {

	ListView lista;
	ArrayAdapter<String> adaptador;
	ArrayList datos;
	LVA_Comunidades listaAdaptador;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        setContentView(R.layout.l_listacomunidades);
	
        /*
	   datos= new ArrayList<String>();
	   llenar_lista();
	   lista = (ListView) findViewById(R.id.listaComunidades);
	   adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
	   lista.setAdapter(adaptador);
	   */
	      
       
       ArrayList<LVI_generico> items = new ArrayList<LVI_generico>();
       items.add(new LVI_generico("Belgica"));
       items.add(new LVI_generico("Holanda"));
       items.add(new LVI_generico("Francia"));
       items.add(new LVI_generico("Alemania"));
       items.add(new LVI_generico("Francia"));
       items.add(new LVI_generico("Alemania"));
       items.add(new LVI_generico("Francia"));
       items.add(new LVI_generico("Alemania"));
	   

	   listaAdaptador = new LVA_Comunidades(this, R.layout.l_list_item, items); 
	   
	   //lista = (ListView) findViewById(android.R.id.list);
	   //lista.setAdapter(listaAdaptador);
	   
	   setListAdapter(listaAdaptador);
	   
	   
	   
	}
	
	public void llenar_lista(){
		datos.add("Belgica");
		datos.add("Holanda");
		datos.add("Francia");
		datos.add("Alemania");
		datos.add("Portugal");
		datos.add("Italia");
		datos.add("Grdcia");		
	}
	
}
