package es.dabdm.decide.ui;


import java.util.ArrayList;
import es.dabdm.decide.R;
import es.dabdm.decide.util.LVA_Comunidades;
import es.dabdm.decide.util.LVI_generico;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;




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
	
		
		Log.i( "A:", "onLongListItemClick id=" + id ); 
		
	}
	
	
	
	




/*
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub	
		super.onListItemClick(l, v, position, id);
		startActivity(
				new Intent(this,ComunidadesDetalleActivity.class));
		
	}


*/
	
	
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
