package es.dabdm.decide;


import java.util.ArrayList;
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


public class ComunidesActivity extends ListActivity {

	ListView lista;
	ArrayAdapter<String> adaptador;
	ArrayList datos;
	u_ListViewAdapter listaAdaptador;
	
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
	      
       
       ArrayList<u_ListViewItem> items = new ArrayList<u_ListViewItem>();
       items.add(new u_ListViewItem("Belgica"));
       items.add(new u_ListViewItem("Holanda"));
       items.add(new u_ListViewItem("Francia"));
       items.add(new u_ListViewItem("Alemania"));
       items.add(new u_ListViewItem("Francia"));
       items.add(new u_ListViewItem("Alemania"));
       items.add(new u_ListViewItem("Francia"));
       items.add(new u_ListViewItem("Alemania"));
	   

	   listaAdaptador = new u_ListViewAdapter(this, R.layout.l_list_item, items); 
	   
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
