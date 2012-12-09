package es.dabdm.decide;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class u_ListViewAdapter extends ArrayAdapter<u_ListViewItem> {

    private ArrayList<u_ListViewItem> items;
    private Context contexto;
    static class ViewHolder {
        TextView title;
    }
    private int ViewResourceId;
    
    private ViewHolder holder;  
    
    public u_ListViewAdapter(Context context, int ViewResourceId, ArrayList<u_ListViewItem> items) {
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
        
        u_ListViewItem elemento = items.get(position);        
        holder.title.setText(elemento.getTitle());
        
        return convertView;
    }
}
	

