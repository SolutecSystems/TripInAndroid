package solutecsystem.com.tripinapp.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

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

import solutecsystem.com.tripinapp.Pojo.Categorias;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.configurations.Configs;


public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Categorias> data;
    private static LayoutInflater inflater = null;
    ArrayList<Categorias> arraylist;
    private String idio;

    public LazyAdapter(Activity a, ArrayList<Categorias> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        arraylist = new ArrayList<Categorias>();
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
        View vi = convertView;

        if (convertView == null)

            vi = inflater.inflate(R.layout.categorieslist, null);

            idio = Locale.getDefault().getLanguage();

            TextView Nombre = (TextView) vi.findViewById(R.id.namecateList); // title

            ImageView myImage = (ImageView) vi.findViewById(R.id.logocateList);

            Categorias platos = new Categorias();
            platos = data.get(position);

        if (idio.equals("es")){
            Nombre.setText(platos.getNombre());
        } else if (idio.equals("en")){
            Nombre.setText(platos.getNombre_ing());
        }

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
            for (Categorias postDetail : arraylist) {
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
