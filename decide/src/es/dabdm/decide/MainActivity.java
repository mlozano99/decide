package es.dabdm.decide;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;

import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends BaseActivity {	
	private TextView latituteField;
	private TextView longitudeField;
	private LocationManager locationManager;
	private LocationListener locationListener;
	private String provider;
	private boolean gps_activado;
	private Location location;
	private trataGPS tGPS;
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main_activity);
        
        
	    tGPS = new trataGPS(this);
	    tGPS.activar_gps();
	    tGPS.iniciar_gps();  
	    
	    
    }

    @Override
    protected void onResume() {
      super.onResume();
      tGPS.iniciar_gps();  
	  

    }
    
    @Override
    protected void onPause() {
      super.onPause();
      tGPS.parar_gps();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    
}



//Button botonPlay = (Button) findViewById(R.id.button1);
/*
botonPlay.setOnClickListener(new View.OnClickListener() {
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(
				new Intent(MainActivity.this,
						LocationActivity.class));
	}
});        */


// Define the criteria how to select the locatioin provider -> use default
//Criteria criteria = new Criteria();
//provider = locationManager.getBestProvider(criteria, false);
//Location location = locationManager.getLastKnownLocation(provider);


/*

// Initialize the location fields
if (location != null) {
  System.out.println("Provider " + provider + " se ha seleccionado.");
  //onLocationChanged(location);
} else {
  latituteField.setText("Localización no disponible");
  longitudeField.setText("Localización no disponible");
}*/