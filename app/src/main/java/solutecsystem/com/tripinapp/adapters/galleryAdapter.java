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

import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.R;

/**
 * Created by Shadowns on 29/12/2016.
 */
public class galleryAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Empresas> data;
    private static LayoutInflater inflater = null;
    ArrayList<Empresas> arraylist;


    public galleryAdapter(Activity a, ArrayList<Empresas> d) {
        activity = a;
        data = d;
        arraylist = new ArrayList<Empresas>();
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

        vi = inflater.inflate(R.layout.galery, null);

        ImageView myImage = (ImageView) vi.findViewById(R.id.gal);

        Empresas platos = new Empresas();
        platos = data.get(position);

        String link = "http://worldtripin.com" + platos.getImagen(position);
        link = link.replace(" ", "%20") ;

        Picasso.with(vi.getContext())
                .load(link)
                .into(myImage);
        return vi;
    }
}