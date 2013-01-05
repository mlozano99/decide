package es.dabdm.decide.ui;

import es.dabdm.decide.R;
import es.dabdm.decide.util.M_Dialogos;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CuentaActivity extends BaseActivity {
	M_Dialogos alerta;
	Bundle d_usuario;
	boolean showRegistro;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
        setContentView(R.layout.l_cuenta);
		this.alerta=new M_Dialogos(this);
		this.d_usuario = new Bundle();
		this.showRegistro= false; 
				
	
		Button b1 = (Button) this.findViewById(R.id.b_registrarse);
		b1.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				alerta.showRegistro();
				showRegistro=true;				
			}
		});
		
		super.onCreate(savedInstanceState);
	}
	
	public Bundle getD_usuario() {
		return d_usuario;
	}

	public void setD_usuario(Bundle d_usuario) {
		this.d_usuario = d_usuario;
	}
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		/*if (this.showRegistro) {
			c_guardar_datos();
			Log.i("A: ----------------",d_usuario.getString("Nombre"));
		}*/
		super.onPause();
	}
	
	
	private void c_guardar_datos(){
		EditText tx1 = (EditText) findViewById(R.id.ET_Nombre);
		EditText tx2 = (EditText) findViewById(R.id.ET_Apellidos);
		EditText tx3 = (EditText) findViewById(R.id.ET_Mail);
		EditText tx4 = (EditText) findViewById(R.id.ET_Telefono);				

		d_usuario.putString("Nombre", tx1.getText().toString());
		d_usuario.putString("Apellidos", tx2.getText().toString());
		d_usuario.putString("Email", tx3.getText().toString());
		d_usuario.putString("Telefono", tx4.getText().toString());
	}

	
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.showRegistro= false; 
	}

}
