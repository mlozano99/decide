package es.dabdm.decide.util;

import es.dabdm.decide.R;
import es.dabdm.decide.modelo.Usuario;
import es.dabdm.decide.ui.CuentaActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.content.Context;



/* Clase para mostrar diálogos 
 * en cualquier parte de la aplicación
 */
public class MDialogos {
	private Context contexto;
	private Usuario usu;
	
	public MDialogos(Context contexto){
		this.contexto=contexto;
		this.usu = new Usuario();
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
	
	
	
	public void showMensajeEstandard(Context contexto,String mensaje) {
	
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
		final CuentaActivity mi_acti = (CuentaActivity) this.contexto;


		LayoutInflater inflater = (LayoutInflater) mi_acti.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View vista_dialogo = inflater.inflate(R.layout.l_usuario_alta,null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this.contexto);			
	
		
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
			   @Override
			   public void onClick(DialogInterface arg0, int arg1) {
			    // TODO Auto-generated method stub
				     EditText e1 = (EditText) vista_dialogo.findViewById(R.id.ET_Nombre);
				     EditText e2 = (EditText) vista_dialogo.findViewById(R.id.ET_Apellidos);
				     EditText e3 = (EditText) vista_dialogo.findViewById(R.id.ET_Mail);
				     EditText e4 = (EditText) vista_dialogo.findViewById(R.id.ET_Telefono);

				     usu.setNombre(e1.getText().toString()+ " " +e2.getText().toString());
				     usu.setEmail(e3.getText().toString());
				     usu.setTelefono(e4.getText().toString());
					 usu.setIdRegistration("Asd_ID_REGISTRO_GCM_gasgdasdgasdgEE");
				     usu.setPublicidad("N");
				     
				     mi_acti.p_resultado_registrarse(usu);
			   }});
			   
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
			 
			   @Override
			   public void onClick(DialogInterface arg0, int arg1) {
			    // TODO Auto-generated method stub
			     
			   }});
			
		builder.setView(vista_dialogo);
		builder.show();
		
	}





	
	
	




}
