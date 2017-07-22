package solutecsystem.com.tripinapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import solutecsystem.com.tripinapp.Pojo.Subcategorias;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.subcategories.Subcategories;

/**
 * Created by Shadowns on 29/12/2016.
 */

public class GradSubAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Subcategorias> data;
    private static LayoutInflater inflater = null;
    ArrayList<Subcategorias> arraylist;


    public  GradSubAdapter(Activity a, ArrayList<Subcategorias> d) {
        activity = a;
        data = d;
        arraylist = new ArrayList<Subcategorias>();
        arraylist.addAll(data);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View vi = convertView;

        if (convertView == null)

            vi = inflater.inflate(R.layout.categoriesgrid, null);

        TextView Nombre = (TextView) vi.findViewById(R.id.namecateGrid); // title

        ImageView myImage = (ImageView) vi.findViewById(R.id.logocateGrid);

        Subcategorias platos = new Subcategorias();
        platos = data.get(position);

        Log.d("Url:", platos.getImagen(position));

        Nombre.setText(platos.getNombre());
        String link = "http://worldtripin.com/" + platos.getImagen(position);
        link = link.replace(" ", "%20") ;

        Picasso.with(vi.getContext())
                .load(link)
                .into(myImage);
        return vi;
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        data.clear();
        if (charText.length() == 0) {
            data.addAll(arraylist);

        } else {
            for (Subcategorias postDetail : arraylist) {
                if (charText.length() != 0 && postDetail.getNombre().toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(postDetail);
                } else if (charText.length() != 0 && postDetail.getNombre().toLowerCase(Locale.getDefault()).contains(charText)) {
                    data.add(postDetail);
                }
            }
        }
        notifyDataSetChanged();
    }
}