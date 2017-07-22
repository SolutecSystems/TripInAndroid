package solutecsystem.com.tripinapp.activitys;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lucasr.twowayview.TwoWayView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.adapters.galleryAdapter;
import solutecsystem.com.tripinapp.configurations.Configs;
import solutecsystem.com.tripinapp.maps.MapaActivity;

/**
 * Created by Shadowns on 05/01/2017.
 */

public class Storeinformation extends AppCompatActivity {

    RequestQueue requestQueue;

    ArrayList<Empresas> gallery = new ArrayList<Empresas>();
    ArrayList<Empresas> arraystor = Configs.getArrStre();

    TextView Nombre,
            Historia,
            Horario,
            directon,
            cel,
            txt,
            acercate,
            galer,
            follow,
            licen,
            mail,
            phone2,
            contact;

    CardView  calific, webv;

    ImageView logo,
            telefono,
            direccion,
            face,
            insta,
            twi,
            advisor,
            gug,
            tel2,
            str1,
            str2,
            str3,
            str4,
            str5,
            strcal1,
            strcal2,
            strcal3,
            strcal4,
            strcal5;

    LinearLayout starscalification,
            cels,
            galers;

    String nombre,
            licencia,
            direction,
            history,
            horary,
            numero,
            instagram,
            facebook,
            twitter,
            google,
            dir,
            Lat,
            Long,
            Comments,
            correo,
            cel2,
            trip,
            url;

    RelativeLayout portada;

    galleryAdapter gAdapter;

    int Starcalification = 0;
    int id_store, star = 0;

    EditText edt;

    GridView gridView;
    String imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_information);
        requestQueue = Volley.newRequestQueue(this);

        CollapsingToolbarLayout   collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Starcalification = arraystor.get(0).getCalificación();
        str1 = (ImageView)findViewById(R.id.str1);
        str2 = (ImageView)findViewById(R.id.str2);
        str3 = (ImageView)findViewById(R.id.str3);
        str4 = (ImageView)findViewById(R.id.str4);
        str5 = (ImageView)findViewById(R.id.str5);
        tel2 = (ImageView)findViewById(R.id.LlamadaIF2);
        logo = (ImageView)findViewById(R.id.imgIfempresa);
        telefono = (ImageView) findViewById(R.id.LlamadaIF);
        direccion = (ImageView)findViewById(R.id.ubicacionIF);
        face = (ImageView)findViewById(R.id.fb);
        twi = (ImageView)findViewById(R.id.twitter);
        insta = (ImageView)findViewById(R.id.insta);
        gug = (ImageView)findViewById(R.id.ggl);
        acercate = (TextView) findViewById(R.id.acercate);
        galer = (TextView) findViewById(R.id.gallerys);
        contact = (TextView) findViewById(R.id.contact);
        follow = (TextView) findViewById(R.id.follow);
        licen = (TextView) findViewById(R.id.licencia);
        Nombre = (TextView) findViewById(R.id.nombreEmpresaIF);
        txt = (TextView) findViewById(R.id.logofree);
        Horario = (TextView) findViewById(R.id.emprehorario);
        Historia = (TextView) findViewById(R.id.empresahistory);
        directon = (TextView) findViewById(R.id.empredir);
        cel = (TextView) findViewById(R.id.emprecel);
        mail = (TextView) findViewById(R.id.empremail);
        phone2 = (TextView) findViewById(R.id.emprecel2);
        starscalification = (LinearLayout)findViewById(R.id.stars);
        cels = (LinearLayout)findViewById(R.id.cels);
        calific = (CardView)findViewById(R.id.btncalificar);
        webv = (CardView) findViewById(R.id.btnweb);
        gridView = (GridView) findViewById(R.id.gallery);
//        portada = (RelativeLayout) findViewById(R.id.portadaperfil);

        String port;
        imagen = arraystor.get(0).getImagen(0);
        port = arraystor.get(0).getPortada();
        nombre = arraystor.get(0).getNombre();

        collapsingToolbarLayout.setTitle(nombre);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        if (imagen.equals("null")){
            Nombre.setVisibility(View.GONE);
            showtxt(imagen, nombre);
        }else {
            Nombre.setVisibility(View.GONE);
            showtxt(imagen, nombre);
        }

        id_store = arraystor.get(0).getId();
        numero = arraystor.get(0).getTelefono();
        Lat = arraystor.get(0).getLatitud();
        Long = arraystor.get(0).getLongitud();
        url = arraystor.get(0).getUrl();

        Log.e("Lat", Lat);
        Log.e("Lon", Long);

        dir = arraystor.get(0).getDireccion();
        correo = arraystor.get(0).getEmail();
        cel2 = arraystor.get(0).getTelefono2();

        if (correo.equals("null"))
            mail.setVisibility(View.GONE);
        else
            mail.setText(correo.toLowerCase());

        gallery.clear();
        gridView.setAdapter(null);

        stdata("Vis");
        numero = numero.toLowerCase();
        if (numero.equals("null")
                || numero.equals("0")
                || numero.equals("sn")
                || numero.equals("na")
                || numero.equals("n/a")
                || numero.equals("s/n")
                || (numero.length()<4)){
            cel.setVisibility(View.GONE);
            contact.setVisibility(View.GONE);
            cels.setVisibility(View.GONE);
        }
        else
            cel.setText(numero);
            
        if(dir.equals("null")){
            directon.setVisibility(View.GONE);
        }else{
            directon.setVisibility(View.VISIBLE);
            directon.setText(dir);
        }

        horary = arraystor.get(0).getHorarioweek()
                + "\n"
                + arraystor.get(0).getHorarioweekend();

        history = arraystor.get(0).getHistoria();

        licencia = arraystor.get(0).getLicencia();

        Log.e("imagen", imagen);

        if (licencia.equals("Free")){
            disabledsocials();

            showtxt(imagen, nombre);

            int res = horary.indexOf("Cerrado");
            if(res >= 2){
                Horario.setVisibility(View.GONE);
                acercate.setVisibility(View.GONE);
            }else {
                Horario.setText(horary);
            }

            telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callnumber(numero);
                }
            });

            direccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent map = new Intent(getApplicationContext(), MapaActivity.class);
                    Configs.setLAT(Lat);
                    Configs.setLONG(Long);
                    Configs.setNOMBRE(nombre);
                    startActivity(map);
                }
            });

        } else if (licencia.equals("Premium") || licencia.equals("Gold"))
        {

            if (cel2.equals("null")
                    || cel2.equals("0")
                    || cel2.equals("sn")
                    || cel2.equals("na")
                    || cel2.equals("n/a")
                    || cel2.equals("s/n")
                    || (cel2.length()<4)){
                phone2.setVisibility(View.GONE);
                tel2.setVisibility(View.GONE);
            }
            else{
                phone2.setText(cel2);
                tel2.setVisibility(View.VISIBLE);
                tel2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callnumber(cel2);
                    }
                });
            }

            enabledsocials();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fillstars(Starcalification);
                    gtcategorys();
                }
            });

            if (url.equals("null")){
                webv.setVisibility(View.GONE);
            }
            webv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent iz;
                    Configs.setURL(url);
                    iz = new Intent(Storeinformation.this, WebActivity.class);
                    startActivity(iz);
                }
            });

            instagram = arraystor.get(0).getInsta();
            facebook = arraystor.get(0).getFb();
            twitter = arraystor.get(0).getTwt();
            google = arraystor.get(0).getGg();

            if (instagram.equals("null"))
                insta.setVisibility(View.GONE);
            else
            {
                int res = instagram.indexOf("http://");
                int resz =  instagram.indexOf("https://");
                if ((res != -1) || (resz != -1)) {
                    Log.e("Instagram", instagram);
                } else{
                    instagram = "https://" + instagram;
                }
            }

            if (twitter.equals("null"))
                twi.setVisibility(View.GONE);
            else
            {
                int res = twitter.indexOf("http://");
                int resz =  twitter.indexOf("https://");
                if ((res != -1) || (resz != -1)) {
                    Log.e("Twt", twitter);
                } else
                    twitter = "http://" + twitter;
            }


            if (facebook.equals("null"))
                face.setVisibility(View.GONE);
            else
            {
                int res = facebook.indexOf("http://");
                int resz =  facebook.indexOf("https://");
                if ((res != -1) || (resz != -1)) {
                    Log.e("FB", facebook);
                } else
                    facebook = "https://" + facebook;
            }


            if (google.equals("null"))
                gug.setVisibility(View.GONE);
            else
            {
                int res = google.indexOf("http://");
                int resz =  google.indexOf("https://");
                if ((res != -1) || (resz != -1)) {
                    Log.e("G+", google);
                } else
                    google = "https://" + google;
            }

//            if (trip.equals("null"))
//                advisor.setVisibility(View.GONE);
//            else
//            {
//                int res = trip.indexOf("http://");
//                int resz =  trip.indexOf("https://");
//                if ((res != -1) || (resz != -1)) {
//                    Log.e("G+", trip);
//                } else
//                    trip = "https://" + trip;
//            }


            int res = horary.indexOf("Cerrado");
            if(res >= 2){
                Horario.setVisibility(View.GONE);
                acercate.setVisibility(View.GONE);
            }else {
                Horario.setText(horary);
            }

            if(history.equals("null")){
                Historia.setVisibility(View.GONE);
            }else{
                Historia.setVisibility(View.VISIBLE);
                Historia.setText(history);
            }

            telefono.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                 callnumber(numero);
                }
            });

            direccion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stdata("Dir");
                    Intent map = new Intent(getApplicationContext(), MapaActivity.class);
                    Configs.setLAT(Lat);
                    Configs.setLONG(Long);
                    Configs.setNOMBRE(nombre);
                    startActivity(map);
                }
            });

            face.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    stdata("Fb");
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(facebook)));

                }
            });

            insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    stdata("Ins");
                    Uri uri = Uri.parse(instagram);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("com.instagram.android");

                    try {

                        startActivity(likeIng);

                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(instagram)));
                    }

                }
            });

            twi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    stdata("Twt");
                    Uri uri = Uri.parse(twitter);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("com.twitter.android");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(twitter)));
                    }
                }
            });


            gug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stdata("GGL");
                    Uri uri = Uri.parse(google);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("com.google.android.apps.plus");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(twitter)));
                    }
                }
            });

//            advisor.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    stdata("TRP");
//                    Uri uri = Uri.parse(trip);
//                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
//                    likeIng.setPackage("com.tripadvisor.tripadvisor");
//                    try {
//                        startActivity(likeIng);
//                    } catch (ActivityNotFoundException e) {
//                        startActivity(new Intent(Intent.ACTION_VIEW,
//                                Uri.parse(trip)));
//                    }
//                }
//            });

            calific.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showChangeLangDialog();
                }
            });

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                    seegallery(i);
                }
            });

        }
    }

    public void disabledsocials(){
        webv.setVisibility(View.GONE);
        phone2.setVisibility(View.GONE);
        follow.setVisibility(View.GONE);
        acercate.setVisibility(View.GONE);
        galer.setVisibility(View.GONE);
        licen.setVisibility(View.VISIBLE);
        txt.setVisibility(View.VISIBLE);
        logo.setVisibility(View.GONE);
//        portada.setVisibility(View.GONE);
        galer.setVisibility(View.GONE);
        gridView.setVisibility(View.GONE);
        starscalification.setVisibility(View.GONE);
        calific.setVisibility(View.GONE);
        Historia.setVisibility(View.GONE);
        Horario.setVisibility(View.GONE);
        face.setVisibility(View.GONE);
        insta.setVisibility(View.GONE);
        twi.setVisibility(View.GONE);
        gug.setVisibility(View.GONE);
    }

    public void enabledsocials(){
        phone2.setVisibility(View.VISIBLE);
        follow.setVisibility(View.VISIBLE);
        acercate.setVisibility(View.VISIBLE);
        galer.setVisibility(View.VISIBLE);
        licen.setVisibility(View.GONE);
        logo.setVisibility(View.VISIBLE);
        galer.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.VISIBLE);
        starscalification.setVisibility(View.VISIBLE);
        calific.setVisibility(View.VISIBLE);
        Historia.setVisibility(View.VISIBLE);
        Horario.setVisibility(View.VISIBLE);
        face.setVisibility(View.VISIBLE);
        insta.setVisibility(View.VISIBLE);
        twi.setVisibility(View.VISIBLE);
        gug.setVisibility(View.VISIBLE);
        webv.setVisibility(View.VISIBLE);
    }

    private void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.calficate, null);
        dialogBuilder.setView(dialogView);

        edt = (EditText) dialogView.findViewById(R.id.savecoment);
        strcal1 = (ImageView)dialogView.findViewById(R.id.strc1);
        strcal2 = (ImageView)dialogView.findViewById(R.id.strc2);
        strcal3 = (ImageView)dialogView.findViewById(R.id.strc3);
        strcal4 = (ImageView)dialogView.findViewById(R.id.strc4);
        strcal5 = (ImageView)dialogView.findViewById(R.id.strc5);

        strcal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = 1;
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal1);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal2);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal3);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal4);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal5);

            }
        });

        strcal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = 2;
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal1);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal2);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal3);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal4);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal5);

            }
        });
        strcal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = 3;
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal1);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal2);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal3);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal4);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal5);

            }
        });

        strcal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = 4;
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal1);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal2);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal3);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal4);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.startransparent)
                        .into(strcal5);

            }
        });
        strcal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                star = 5;
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal1);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal2);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal3);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal4);
                Picasso.with(dialogView.getContext())
                        .load(R.drawable.stargold)
                        .into(strcal5);
            }
        });


        dialogBuilder.setTitle(R.string.comment);
        dialogBuilder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                Comments = edt.getText().toString();

                if (Comments.equals("") || star <  1){

                    Toast.makeText(Storeinformation.this,
                            "Por favor realice algun comentario o Seleccione alguna calificación para poder continuar ",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    commentar();
                }
            }
        });
        dialogBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void fillstars(int calification){
        if (calification < 2){
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str1);
        }

        else if ((calification >= 2) && (calification < 3)){
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str1);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str2);
        }

        else if ((calification >= 3) && (calification < 4)){
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str1);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str2);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str3);
        }

        else if ((calification >= 4) && (calification < 5)){
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str1);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str2);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str3);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str4);
        }

        else if (calification >= 5){
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str1);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str2);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str3);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str4);
            Picasso.with(getApplicationContext())
                    .load(R.drawable.stargold)
                    .into(str5);
        }
    }

    private void callnumber(String numero) {
        try {
            stdata("Cel");
            Uri call = Uri.parse("tel:" + numero);
            Intent surf = new Intent(Intent.ACTION_CALL, call);
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling

                return;
            }
            startActivity(surf);
        }
        catch (ActivityNotFoundException activityException) {

            Toast.makeText(getApplicationContext(),"No se pudo realizar la llamada.",Toast.LENGTH_SHORT).show();
        }
    }

    public void commentar(){

      StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_SAVECOMMENT,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    Log.e("Respuesta", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("idEstablecimiento",String.valueOf(id_store));
                params.put("calificacion",String.valueOf(star));
                params.put("comentario", Comments);

                return params;
            }

          @Override
          public Map<String, String> getHeaders() throws AuthFailureError {
              Map<String,String> params = new HashMap<String, String>();
              params.put("Content-Type","application/x-www-form-urlencoded");
              return params;
          }

        };

        sr.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(sr);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {


        private RelativeLayout bmImage;

        public DownloadImageTask(RelativeLayout bmImage) {
            this.bmImage = bmImage;
        }

        protected void onPreExecute() {
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            //set image of your imageview
            BitmapDrawable background = new BitmapDrawable(getResources(), result);
            bmImage.setBackgroundDrawable(background);
        }
    }

    private void gtcategorys(){

        StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_GALERIA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray respon = new JSONArray(response);

                    for (int i = 0; i < respon.length(); i++) {
                        JSONObject c = (JSONObject) respon.get(i);
                        Empresas empre = new Empresas();
                        empre.setImagen(c.getString("imagen"));
                        gallery.add(empre);
                    }

                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (gallery.size()>0){
                                gAdapter = new galleryAdapter(Storeinformation.this, gallery);
                                gridView.setAdapter(gAdapter);
                            }else {
                                galer.setVisibility(View.GONE);
                                gridView.setVisibility(View.GONE);
                            }

                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("idEstablesimiento",String.valueOf(id_store));

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        sr.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(sr);
    }

    private void stdata(final String data){

        StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_DATOS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Respuesta", response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("idEstablesimiento",String.valueOf(id_store));
                params.put("tipo", data);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        sr.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(sr);
    }

    public void seegallery(final int pos){

        final int n = pos;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Storeinformation.this);
        LayoutInflater inflater = getLayoutInflater();


        final View dialogView = inflater.inflate(R.layout.gallery, null);
        dialogBuilder.setView(dialogView);

        final ImageView image, exit;
        final TwoWayView slide;


        image = (ImageView) dialogView.findViewById(R.id.imagegal);
        exit = (ImageView)dialogView.findViewById(R.id.close);
        slide = (TwoWayView) dialogView.findViewById(R.id.slidegal);

        gAdapter = new galleryAdapter(Storeinformation.this, gallery);
        slide.setAdapter(gAdapter);

        String link = "http://worldtripin.com/" + gallery.get(n).getImagen();
        link = link.replace(" ", "%20");

        Picasso.with(dialogView.getContext())
                .load(link)
                .into(image);

        slide.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String link = "http://worldtripin.com/" + gallery.get(i).getImagen();
                link = link.replace(" ", "%20");

                Picasso.with(dialogView.getContext())
                        .load(link)
                        .placeholder(R.drawable.logot)
                        .into(image);
            }
        });



        final AlertDialog b = dialogBuilder.create();
        b.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

        b.show();
    }

    public void showtxt(String imag, String nombre){

        if (imag.equals("null")){
            String[] arrname = nombre.split("\\s+");
            txt.setVisibility(View.VISIBLE);
//            portada.setVisibility(View.GONE);

            if (arrname.length >= 2){

                String name1 = arrname[0].substring(0,1);
                String name2 = arrname[1].substring(0,1);

                String total = name1 + name2;
                total = total.toUpperCase();

                txt.setText(total);

            } else if (arrname.length == 1){

                String name1 = arrname[0].substring(0,1);
                String name2 = arrname[0].substring(1,2);

                String total = name1 + name2;
                total = total.toUpperCase();

                txt.setText(total);
            }
        }
        else
        {
            String link = "http://worldtripin.com/" + imag;
            link = link.replace(" ", "%20") ;
            Picasso.with(this)
                    .load(link)
                    .into(logo);
        }

    }
}
