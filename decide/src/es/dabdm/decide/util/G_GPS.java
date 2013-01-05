package es.dabdm.decide.util;

import es.dabdm.decide.R;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.widget.Toast;


public class G_GPS implements LocationListener {

	private Location location;
	private LocationManager locationManager;
	private boolean gps_activado;
	private Context contexto;
	private M_Dialogos alerta;
	
	public G_GPS(Context contexto) {
		this.contexto=contexto;
		this.alerta=new M_Dialogos(this.contexto);
	}    
	    
	public void activar_gps(){
		
		// Get the location manager
		try {
			locationManager = (LocationManager) this.contexto.getSystemService(Context.LOCATION_SERVICE);
			gps_activado = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		}
		catch (Exception e){			
			alerta.showExceptionDialog(R.string.locationManager);
		}
		    
		if (!gps_activado) {
			alerta.showActivarGPS();
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
			alerta.showExceptionDialog(R.string.locationManager);
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

	
	
	    		
	

}
