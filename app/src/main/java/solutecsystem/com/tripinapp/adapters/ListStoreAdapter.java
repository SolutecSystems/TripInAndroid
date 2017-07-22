package solutecsystem.com.tripinapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;
import java.util.StringTokenizer;

import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.Pojo.Subcategorias;
import solutecsystem.com.tripinapp.Pojo.Tags;
import solutecsystem.com.tripinapp.R;

/**
 * Created by Shadowns on 29/12/2016.
 */
public class ListStoreAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Empresas> data;
    private static LayoutInflater inflater = null;
    ArrayList<Empresas> arraylist;


    public ListStoreAdapter(Activity a, ArrayList<Empresas> d) {
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
            vi = inflater.inflate(R.layout.storelist, null);

        TextView Nombre = (TextView) vi.findViewById(R.id.namecateList); // title

        TextView Dir = (TextView) vi.findViewById(R.id.dircateList); // title
        Dir.setVisibility(View.VISIBLE);

        TextView TextImage = (TextView) vi.findViewById(R.id.textimage);

        TextView Cel = (TextView) vi.findViewById(R.id.celcateList); // title
        Cel.setVisibility(View.VISIBLE);

        ImageView myImage = (ImageView) vi.findViewById(R.id.logocateList);

        TextView tag1, tag2, tag3, tag4, tag5;

        tag1 = (TextView) vi.findViewById(R.id.tag1);
        tag1.setVisibility(View.GONE);

        tag2 = (TextView) vi.findViewById(R.id.tag2);
        tag2.setVisibility(View.GONE);

        tag3 = (TextView) vi.findViewById(R.id.tag3);
        tag3.setVisibility(View.GONE);

        tag4 = (TextView) vi.findViewById(R.id.tag4);
        tag4.setVisibility(View.GONE);

        tag5 = (TextView) vi.findViewById(R.id.tag5);
        tag5.setVisibility(View.GONE);

        Empresas platos = new Empresas();
        platos = data.get(position);

        String namez = platos.getEtiquetas();
        String[] arrnamez = namez.split("\\s+");

      if (arrnamez.length == 2){
            tag1.setVisibility(View.VISIBLE);
            tag1.setText(arrnamez[1]);
        } else if (arrnamez.length == 3){
            tag1.setVisibility(View.VISIBLE);
            tag1.setText(arrnamez[1]);
            tag2.setVisibility(View.VISIBLE);
            tag2.setText(arrnamez[2]);
        } else if (arrnamez.length == 4){
            tag1.setVisibility(View.VISIBLE);
            tag1.setText(arrnamez[1]);
            tag2.setVisibility(View.VISIBLE);
            tag2.setText(arrnamez[2]);
            tag3.setVisibility(View.VISIBLE);
            tag3.setText(arrnamez[3]);
        } else if(arrnamez.length == 5){
            tag1.setVisibility(View.VISIBLE);
            tag1.setText(arrnamez[1]);
            tag2.setVisibility(View.VISIBLE);
            tag2.setText(arrnamez[2]);
            tag3.setVisibility(View.VISIBLE);
            tag3.setText(arrnamez[3]);
            tag4.setVisibility(View.VISIBLE);
            tag4.setText(arrnamez[4]);
        }else if(arrnamez.length == 6){
          tag1.setVisibility(View.VISIBLE);
          tag1.setText(arrnamez[1]);
          tag2.setVisibility(View.VISIBLE);
          tag2.setText(arrnamez[2]);
          tag3.setVisibility(View.VISIBLE);
          tag3.setText(arrnamez[3]);
          tag4.setVisibility(View.VISIBLE);
          tag4.setText(arrnamez[4]);
          tag4.setVisibility(View.VISIBLE);
          tag4.setText(arrnamez[5]);
      }

        Log.d("Url:", platos.getImagen(position));

        Nombre.setText(platos.getNombre());
        Dir.setText(platos.getDireccion());

        String numero = platos.getTelefono();

        if (numero.equals("null")
                || numero.equals("0")
                || numero.equals("sn")
                || numero.equals("na")
                || numero.equals("n/a")
                || numero.equals("s/n")
                || (numero.length()<4)){
            Cel.setVisibility(View.INVISIBLE);
        }
        else
            Cel.setText(numero);

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
            String link = "http://worldtripin.com/" + platos.getImagen(position);
            link = link.replace(" ", "%20") ;

            myImage.setVisibility(View.VISIBLE);
            TextImage.setVisibility(View.GONE);

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