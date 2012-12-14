package es.dabdm.decide.ui;


import es.dabdm.decide.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;



public class BaseActivity extends Activity {
	
	public static final String DEBUG_TAG = "Depurando en: ";	
	private java.util.Random r;
	private int id_log;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.r = new java.util.Random();
        id_log= r.nextInt(200);
        android.util.Log.i(DEBUG_TAG, "["+ this.id_log +"] A: ["+ this.getClass().getSimpleName() +"] creada onCreate()" );
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.layout_activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		android.util.Log.i(DEBUG_TAG, "["+ this.id_log +"] A: ["+ this.getClass().getSimpleName() +"] pasa background. onPause()" );
		super.onPause();		
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		android.util.Log.i(DEBUG_TAG,"["+ this.id_log +"] A: ["+ this.getClass().getSimpleName() +"] se reinicia onRestart()");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		android.util.Log.i(DEBUG_TAG,"["+ this.id_log +"] A: ["+ this.getClass().getSimpleName() +"]  puede comenzar a interactuar con usuario onResume()");
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		android.util.Log.i(DEBUG_TAG,"["+ this.id_log +"] A: ["+ this.getClass().getSimpleName() +"] se visualiza al usuario onStart()");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		android.util.Log.i(DEBUG_TAG,"["+ this.id_log +"] A: ["+ this.getClass().getSimpleName() +"]  ya no es visible onStop()");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (isFinishing()) 
			android.util.Log.i(DEBUG_TAG,"["+ this.id_log +"] A: ["+ this.getClass().getSimpleName() +"] onDestory() por el sistema");
			else android.util.Log.i(DEBUG_TAG,"["+ this.id_log +"] A: ["+ this.getClass().getSimpleName()+"] onDestroy() por el usuario");

		super.onDestroy();
	}
}