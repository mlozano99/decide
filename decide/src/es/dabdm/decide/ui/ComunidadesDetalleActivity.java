package es.dabdm.decide.ui;


import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Comunidad;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ComunidadesDetalleActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l_detallcomunidad);
		
		Intent i = getIntent();
		Comunidad comu = (Comunidad) i.getSerializableExtra("Comunidad");	
		
		TextView t = (TextView) findViewById(R.id.tv_DC);		
		t.setText( comu.getNombre() + comu.getIdComunidad() );
		
		TextView t2 = (TextView) findViewById(R.id.tv_DCdescripcion);		
		t2.setText( comu.getDescripcion());
		/*
		
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			TextView t = (TextView) findViewById(R.id.tv_DC);	
			
			t.setText( extras.getString("Comunidad") + "-" + extras.getInt("id") );
			
			Log.i("A: llega: ",extras.getString("Comunidad"));
		}
		*/
		
		Log.i("A:", "llega extras vacioas ");
		
	}

}
