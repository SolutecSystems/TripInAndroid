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
import solutecsystem.com.tripinapp.Pojo.Subcategorias;
import solutecsystem.com.tripinapp.Pojo.Tags;
import solutecsystem.com.tripinapp.R;

/**
 * Created by Shadowns on 29/12/2016.
 */

public class GradStoreAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Empresas> data;
    private static LayoutInflater inflater = null;
    ArrayList<Empresas> arraylist;


    public GradStoreAdapter(Activity a, ArrayList<Empresas> d) {
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

            vi = inflater.inflate(R.layout.storesgrid, null);

        TextView Nombre = (TextView) vi.findViewById(R.id.namecateGrid); // title

        ImageView myImage = (ImageView) vi.findViewById(R.id.logocateGrid);

        TextView Cel = (TextView) vi.findViewById(R.id.celcateList); // title
        Cel.setVisibility(View.VISIBLE);

        TextView TextImage = (TextView) vi.findViewById(R.id.textimage);

        Empresas platos = new Empresas();
        platos = data.get(position);

        Log.d("Url:", platos.getImagen(position));

        Nombre.setText(platos.getNombre());
        Cel.setText(platos.getTelefono());

        String imagen = platos.getImagen(position);

        if (imagen.equals("null")|| platos.getLicencia().equals("Free")){

            myImage.setVisibility(View.GONE);
            TextImage.setVisibility(View.VISIBLE);

            String name = platos.getNombre();
            String[] arrname = name.split("\\s+");

            if (arrname.length >= 2){

                String name1 = arrname[0].substring(0,1);
                String name2 = arrname[1].substring(0,1);

                String total = name1 + name2;
                total = total.toUpperCase();

                TextImage.setText(total);

            } else if (arrname.length == 1){

                String name1 = arrname[0].substring(0,1);
                String name2 = arrname[0].substring(1,2);

                String total = name1 + name2;
                total = total.toUpperCase();

                TextImage.setText(total);
            }

        }else {

            myImage.setVisibility(View.VISIBLE);
            TextImage.setVisibility(View.GONE);

        String link = "http://worldtripin.com/" + imagen;
        link = link.replace(" ", "%20") ;

        Picasso.with(vi.getContext())
                .load(link)
                .into(myImage);
        }

        return vi;
    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        data.clear();
        if (charText.length() == 0) {
            data.addAll(arraylist);

        } else {
            for (Empresas postDetail : arraylist) {
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