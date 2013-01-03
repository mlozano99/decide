package es.dabdm.decide.util;

import java.util.ArrayList;

import es.dabdm.decide.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class LVA_Comunidades extends ArrayAdapter<LVI_generico> {

    private ArrayList<LVI_generico> items;
    private Context contexto;
    
    static class ViewHolder {
        TextView title;
    }
    private int ViewResourceId;
    
    private ViewHolder holder;  
    
    public LVA_Comunidades(Context context, int ViewResourceId, ArrayList<LVI_generico> items) {
        super(context, ViewResourceId, items);
        this.items = items;
        this.contexto=context;
        this.ViewResourceId=ViewResourceId;
        
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        if(convertView == null) {
            
        	LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            
        	convertView = vi.inflate(this.ViewResourceId, null);
            
            holder = new ViewHolder();            
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
            
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        LVI_generico elemento = items.get(position);        
        holder.title.setText(elemento.getTitle());
        
        return convertView;
    }


	
	
    
    
    
}
	

