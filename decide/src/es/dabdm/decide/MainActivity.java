package es.dabdm.decide;




import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends BaseActivity {	
	private u_trataGPS tGPS;
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_main_activity);
       
	    
        tGPS = new u_trataGPS(this);
	    tGPS.activar_gps();
	    tGPS.iniciar_gps();  
    	
	    Button b1 = (Button) findViewById(R.id.button1);
	    		b1.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				startActivity(
					new Intent(MainActivity.this,ComunidesActivity.class));
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



