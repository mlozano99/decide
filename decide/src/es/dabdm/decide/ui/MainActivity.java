package es.dabdm.decide.ui;




import es.dabdm.decide.R;
import es.dabdm.decide.R.id;
import es.dabdm.decide.R.layout;
import es.dabdm.decide.R.menu;
import es.dabdm.decide.util.G_GPS;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends BaseActivity {	
	private G_GPS tGPS;
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main_activity);
       
	    
        tGPS = new G_GPS(this);
	    tGPS.activar_gps();
	    tGPS.iniciar_gps();  
    	
	    Button b1 = (Button) findViewById(R.id.b_comu);
	    b1.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(
					new Intent(MainActivity.this,ComunidadesActivity.class));
			}	
		});
	    

	    Button b2 = (Button) findViewById(R.id.b_gcm);
	    b2.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(
					new Intent(MainActivity.this,DemoActivity.class));
			}	
		});
	    
	    
	    
	    
    }

    @Override
    protected void onResume() {
      super.onResume();
      tGPS.iniciar_gps();  
    }
    
    @Override
    protected void onPause() {
      super.onPause();
      tGPS.parar_gps();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}



