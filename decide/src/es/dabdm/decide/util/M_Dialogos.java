package es.dabdm.decide.util;

import es.dabdm.decide.R;
import es.dabdm.decide.ui.CuentaActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;


/* Clase para mostrar diálogos 
 * en cualquier parte de la aplicación
 */



public class M_Dialogos {
	private Context contexto;
	
	public M_Dialogos(Context contexto){
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
	    		

	public void showRegistro()
	{
		final Activity mi_acti = (Activity) this.contexto;
		
		LayoutInflater inflater = (LayoutInflater) mi_acti.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogo = inflater.inflate(R.layout.l_usuario, (ViewGroup) mi_acti.getCurrentFocus());
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this.contexto);			
		builder.setView(dialogo);		
		builder.show();		
		
	}




	
	
	
}
