package es.dabdm.decide.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import android.os.Bundle;
import android.webkit.WebView;
import es.dabdm.decide.R;

public class InfoActivity extends BaseActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
        setContentView(R.layout.l_info);  
        super.onCreate(savedInstanceState);
        
    	WebView webVisor = (WebView) findViewById(R.id.visorWeb);
    	webVisor.loadData(leerHtml("info.html"), "text/html", "utf-8");
   	
        	
	}	
	

	
	
	private String leerHtml(String nombre){
		StringBuilder sb = new StringBuilder();
        try {
        
        	InputStream stream = getAssets().open(nombre);
        	
	        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String line = null;			
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			stream.close();	                
        }
        catch (IOException e){
        	e.printStackTrace();
        }

        //String s = sb.toString();
        //(charset)getBytes("ISO8859_1"), android.util.Base64.DEFAULT);
        //s= android.util.Base64.encodeToString(s.getBytes(Charset.forName("ISO-8859-1")),android.util.Base64.DEFAULT);
        return sb.toString();
	}
	
	
}
