package es.dabdm.decide.ui;


import es.dabdm.decide.R;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import es.dabdm.decide.util.Repositorio;
import es.dabdm.decide.util.MCuenta;

public class PerfilActivity extends BaseActivity {
	boolean usu_iden;
	SharedPreferences preferences;
	MCuenta cuenta ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.l_perfil);
		
		cuenta = MCuenta.getMC(this);
        
		TextView tv_nombre = (TextView) this.findViewById(R.id.tv_nombreusuario);
		TextView tv_email  = (TextView) this.findViewById(R.id.tv_emailusuario);
		TextView tv_telefono = (TextView) this.findViewById(R.id.tv_telefonousuario);
		
		if (cuenta.usu_identificado()) { 
		
			tv_nombre.setText(cuenta.getString("nombre"));
			tv_email.setText(cuenta.getString("email"));
			tv_telefono.setText(cuenta.getString("telefono"));
			
			comunidades_suscritas();
		super.onCreate(savedInstanceState);	
		}
	}

		
	 @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	public void b_cerrar_sesion(View view) {
		cuenta.cerrarSesion();
	   	this.startActivity(new Intent(this, CuentaActivity.class)); 
	   	this.finish();
	   	
	 }
	
	
	private void comunidades_suscritas(){
	
		
	}
	

	
	
	
	
	
	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.l_perfil, menu);
		return true;
	}
*/
	
	








}
