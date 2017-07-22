package solutecsystem.com.tripinapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import solutecsystem.com.tripinapp.Pojo.Categorias;
import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.Pojo.Subcategorias;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.configurations.Configs;


public class MapAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Subcategorias> data;
    private static LayoutInflater inflater = null;


    public MapAdapter(Activity a, ArrayList<Subcategorias> d) {
        activity = a;
        data = d;
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

            vi = inflater.inflate(R.layout.listonmap, null);

            ImageView myImage = (ImageView) vi.findViewById(R.id.imgtab);

            Subcategorias platos = new Subcategorias();
            platos = data.get(position);

            String link = "http://worldtripin.com/"
                    + platos.getImagen(position);

            link = link.replace(" ", "%20");

            Picasso.with(vi.getContext())
                    .load(link)
                    .into(myImage);
        return vi;
    }

}