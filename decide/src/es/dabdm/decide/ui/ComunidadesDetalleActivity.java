package es.dabdm.decide.ui;


import es.dabdm.decide.R;
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
		
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			TextView t = (TextView) findViewById(R.id.tv_DC);			
			t.setText( extras.getString("Comunidad") );
			Log.i("A: llega: ",extras.getString("Comunidad"));
		}
		Log.i("A:", "llega extras vacioas ");
		
	}

}
