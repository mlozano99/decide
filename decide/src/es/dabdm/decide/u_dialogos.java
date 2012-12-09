package es.dabdm.decide;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.content.Context;


public class u_dialogos {
	private Context contexto;
	
	public u_dialogos(Context contexto){
		this.contexto=contexto;
	}
	
	
	public void showActivarGPS()
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
