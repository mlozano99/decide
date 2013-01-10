package es.dabdm.decide.util;

import es.dabdm.decide.modelo.Usuario;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.Context;



/* Clase para realizar acciones de verificación de usuario
 */

public class MCuenta {
	private Context contexto;
	private Usuario usu;
	private boolean usu_iden;
	private SharedPreferences preferences;
	
	static private MCuenta singleton = null;
	
	private MCuenta(Context contexto){
		this.contexto=contexto;
		this.usu = new Usuario();
	}
	
	static public MCuenta getMC (Context contexto){
        
		if (singleton == null) {
            singleton = new MCuenta(contexto);
        }
        return singleton;		
	}
	
	
	public boolean usu_identificado(){
		this.usu_iden=false;
		this.preferences = contexto.getSharedPreferences( Repositorio.PerfilPref , Context.MODE_PRIVATE);
        usu_iden = (preferences.getInt("identificado", 0)==1) ? true:false ;	
		return usu_iden;
	}
	
	public void cerrarSesion(){	
		Editor editor = this.preferences.edit();
		editor.putInt("identificado", 0);
		editor.commit();		 
	}	
	
	public void putString(String nombre, String valor) {
		Editor editor = this.preferences.edit();
		editor.putString(nombre, valor);
		editor.commit();
	}
	
	public void putInt(String nombre,int valor){
		Editor editor = this.preferences.edit();
		editor.putInt(nombre, valor);
		editor.commit();
	}	
	
	public String getString(String valor){
		//Editor editor = this.preferences.edit();
		return this.preferences.getString(valor, "");		
	}	
	
	public int getInt(String valor){
		//Editor editor = this.preferences.edit();		
		return this.preferences.getInt(valor, 0);
	}

	
	
	
}
