package es.dabdm.decide;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;


public class trataGPS implements LocationListener {

	private TextView latituteField;
	private TextView longitudeField;
	private LocationManager locationManager;
	private LocationListener locationListener;
	private String provider;
	private boolean gps_activado;
	private Location location;
	private Context contexto;
	
	public trataGPS(Context contexto) {
		this.contexto=contexto;
		
	}    
	    
	public void activar_gps(){
		
		// Get the location manager
		try {
			locationManager = (LocationManager) this.contexto.getSystemService(Context.LOCATION_SERVICE);
			gps_activado = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		}
		catch (Exception e){			
			showExceptionDialog(R.string.locationManager);
		}
		    
		if (!gps_activado) {
			showActivarGPS();
		 } 		 
	 }
	
	public void iniciar_gps(){
		
		try {
			if (gps_activado){
			    location =this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			    if (location != null) onLocationChanged(location);
				this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
			  }
		}
		catch (Exception e) {
			showExceptionDialog(R.string.locationManager);
		}
	}
	
	public void parar_gps(){
		this.locationManager.removeUpdates(this);
	}
	
	
	
/* ************************************************************************************************* */	
/* Métodos para los eventos del LocationListener */
/* ************************************************************************************************* */	
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub		
	    double lat = location.getLatitude();
	    double lon = location.getLongitude();
	    Toast.makeText(contexto, "Latitud: " + lat + " | " + "Longitud: " + lon,  Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	    Toast.makeText(contexto, "Desactiva provider " + provider,
        Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	    Toast.makeText(contexto, "Activa nuevo provider " + provider,
        Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}

	
	
	
	
	private void showActivarGPS()
	{
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(contexto);
		//dialogBuilder.setTitle("Prueba");
		dialogBuilder.setMessage(R.string.muestraGPS);
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				contexto.startActivity(intent);
			}
		});
		
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
	}
	
	public void showExceptionDialog(int mensaje) {
		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(contexto);
		dialogBuilder.setMessage(mensaje);
		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		AlertDialog alertDialog = dialogBuilder.create();
		alertDialog.show();
	}
	    		
	

}
