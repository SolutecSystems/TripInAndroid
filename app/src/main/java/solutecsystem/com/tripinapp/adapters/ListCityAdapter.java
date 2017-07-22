package solutecsystem.com.tripinapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
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

import solutecsystem.com.tripinapp.Pojo.Citys;
import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.R;

/**
 * Created by Shadowns on 29/12/2016.
 */
public class ListCityAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Citys> data;
    private static LayoutInflater inflater = null;
    ArrayList<Citys> arraylist;


    public ListCityAdapter(Activity a, ArrayList<Citys> d) {
        activity = a;
        data = d;
        arraylist = new ArrayList<Citys>();
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

            vi = inflater.inflate(R.layout.citylist, null);

        TextView Nombre = (TextView) vi.findViewById(R.id.namecateListcity);

        Citys platos = new Citys();
        platos = data.get(position);

        Nombre.setText(platos.getCiudad()
                + ", "
                + platos.getDepartamento()
                + ", "
                + platos.getPais());


        return vi;
    }
}