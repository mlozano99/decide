package es.dabdm.decide;


import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class LocationActivity extends BaseActivity implements LocationListener {
	  private TextView latituteField;
	  private TextView longitudeField;
	  private LocationManager locationManager;
	  private String provider;
	  
	  
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.l_location_activity);
	    latituteField = (TextView) findViewById(R.id.TextView02);
	    longitudeField = (TextView) findViewById(R.id.TextView04);

	    // Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    
	    // Define the criteria how to select the locatioin provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    
	    Location location = locationManager.getLastKnownLocation(provider);

	    
	    
	    // Initialize the location fields
	    if (location != null) {
	      System.out.println("Provider " + provider + " se ha seleccionado.");
	      onLocationChanged(location);
	    } else {
	      latituteField.setText("Localización no disponible");
	      longitudeField.setText("Localización no disponible");
	    }
	  }

	  
	  
	  
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	    double lat = location.getLatitude();
	    double lng = location.getLongitude();
	    latituteField.setText(String.valueOf(lat));
	    longitudeField.setText(String.valueOf(lng));
	
	}
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	    Toast.makeText(this, "Desactiva provider " + provider,
        Toast.LENGTH_SHORT).show();
	

	}
	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	    Toast.makeText(this, "Activa nuevo provider " + provider,
        Toast.LENGTH_SHORT).show();

		
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
		
	}
	  
	  

}
