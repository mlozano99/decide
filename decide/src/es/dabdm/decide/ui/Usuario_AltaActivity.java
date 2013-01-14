package es.dabdm.decide.ui;
/* ESta clase es para borrar */
import es.dabdm.decide.R;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;

public class Usuario_AltaActivity extends BaseActivity {

	SharedPreferences preferences;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.l_usuario_alta);
	}	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.l_perfil, menu);
		return true;
	}
*/
	
	
	
	 public void b_cerrar_sesion(View view) {
		 
		Editor editor = this.preferences.edit();
		editor.putInt("identificado", 0);
		editor.commit();		 
		 
	   	this.startActivity(new Intent(this, CuentaActivity.class)); 
	   	
	 }

}




/*
//Leer datos registrados en sharepreferences
preferences = getSharedPreferences("_configuracion", Context.MODE_PRIVATE);

TextView tv_nombre = (TextView) this.findViewById(R.id.tv_nombreusuario);
TextView tv_email  = (TextView) this.findViewById(R.id.tv_emailusuario);
TextView tv_telefono = (TextView) this.findViewById(R.id.tv_telefonousuario);

tv_nombre.setText(preferences.getString("nombre", "") );
tv_email.setText(preferences.getString("email", "") );
tv_telefono.setText(preferences.getString("telefono", "") );
super.onCreate(savedInstanceState);	
*/