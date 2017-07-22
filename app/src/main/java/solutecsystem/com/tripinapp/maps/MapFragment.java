package solutecsystem.com.tripinapp.maps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.Pojo.Subcategorias;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.adapters.MapAdapter;
import solutecsystem.com.tripinapp.configurations.Configs;
import solutecsystem.com.tripinapp.configurations.Settings;
import solutecsystem.com.tripinapp.maps.MapsPresentator.MapsPresentator;
import solutecsystem.com.tripinapp.maps.MapsPresentator.MapsPresentatorImp;

public class MapFragment extends Fragment implements solutecsystem.com.tripinapp.maps.MapsPresentator.MapView, OnMapReadyCallback{

    private ProgressDialog pDialog;
    private GoogleMap googlezMap;
    double Lat;
    double Lon;
    int emp;

    private Marker MarkerOne;

    private String Nombre, Direccion, Imagen;
    private LatLng center;
    private String image;
    SupportMapFragment mapFragment;
    RequestQueue requestQueue;
    MarkerOptions markerOptions;
    Map <String, Integer> mMarkers = new HashMap<String, Integer>();
    ArrayList<Empresas> marks = new ArrayList<Empresas>();
    ArrayList<Subcategorias> subshows = new ArrayList<Subcategorias>();
    ListView empres;
    MapAdapter adapter;

    MapsPresentator presentator;

    public MapFragment NewInstance() {
        MapFragment In = new MapFragment();
        return In;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View v = inflater.inflate(R.layout.fragment_tabempresamap, container,
                false);

        presentator = new MapsPresentatorImp(this, getActivity());

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        requestQueue = Volley.newRequestQueue(getActivity());
        empres = (ListView) v.findViewById(R.id.empresatab);
        subshows.clear();
        empres.setAdapter(null);

        filllist();

        empres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                emp = subshows.get(position).getId();
                image = subshows.get(position).getImagen(position);

                googlezMap.clear();
                mapfill(image);
            }
        });

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googlezMap = googleMap;
        googlezMap.clear();
        googlezMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        double latitude = 14.5531622;
        double longitude = -90.7343662;

        center = new LatLng(latitude,longitude);

        googlezMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        CameraUpdate zoom =CameraUpdateFactory.zoomTo(17);
        googlezMap.animateCamera(zoom);
        googlezMap.getUiSettings().setZoomControlsEnabled(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        presentator.onShowPublic();
    }

    @Override
    public void onPause() {
        super.onPause();
        presentator.onPausePublication();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presentator.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        presentator.onPausePublication();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
        Log.e("Menu", "load");

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_buscar:
                return true;

            case R.id.action_setting:
                Intent i = new Intent(getActivity(), Settings.class);
                startActivity(i);
                return true;

        }return false;
    }

    private void showpDialog() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void hidepDialog() {
        pDialog.dismiss();
    }

    public void filllist(){

        JsonArrayRequest arrayreq = new JsonArrayRequest(Configs.URL_MAPS,
                // The second parameter Listener overrides the method onResponse() and passes
                //JSONArray as a parameter
                new Response.Listener<JSONArray>() {
                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject c = (JSONObject) response.get(i);
                                Subcategorias cates = new Subcategorias();
                                cates.setId(c.getInt(Configs.TAG_ID));
                                cates.setImagen(c.getString(Configs.TAG_IMAG));
                                subshows.add(cates);
                            }

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapter = new MapAdapter(getActivity(), subshows);
                                    empres.setAdapter(adapter);
                                }
                            });
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        onShowError("Hubo un error al realizar la conexión con el servidor, por favor intente mas tarde");
                    }
                }
        );

        arrayreq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        requestQueue.add(arrayreq);
    }

    public void mapfill(final String url){
        marks.clear();
        StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_STORES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray respon = new JSONArray(response);
                    int finC;
                    int calif = 0;
                    int n = 0;

                    for (int i = 0; i < respon.length(); i++) {

                        JSONObject c = (JSONObject) respon.get(i);

                        Log.e("datos: ", c.toString());

                        String Latitud = c.getString(Configs.TAG_LAT).replace(",", "");
                        String Longitud = c.getString(Configs.TAG_LON).replace(",", "");
                        Nombre = c.getString(Configs.TAG_tit);
                        Direccion = c.getString(Configs.TAG_DIR);
                        Imagen = c.getString(Configs.TAG_IMAG);

                        Empresas empre = new Empresas();

                            empre.setId(c.getInt(Configs.TAG_ID));
                            empre.setNombre(Nombre);
                            empre.setDireccion(Direccion);
                            empre.setLatitud(Latitud);
                            empre.setLongitud(Longitud);
                            empre.setImagen(Imagen);

                            JSONArray obj = c.getJSONArray("comentarios");
                            for (int j = 0; j < obj.length(); j++)
                            {
                                n++;
                                JSONObject jc = obj.getJSONObject(j);
                                int cal = jc.getInt("clasificacion");
                                calif = (calif + cal);
                            }
                            if (calif>0){
                                    finC = (calif/n);
                                }else {
                                    finC = 0;
                                }
                            empre.setCalificación(finC);

                        marks.add(empre);

                        LatLng latLng = new LatLng(Double.parseDouble(Latitud),
                                Double.parseDouble(Longitud));

                        if (i == 0){
                            CameraPosition cameraPosition = new CameraPosition.Builder()
                                    .target(center).zoom(15).build();

                            googlezMap.animateCamera(CameraUpdateFactory
                                    .newCameraPosition(cameraPosition));
                        }

                        onFillMarkers(Nombre, Direccion, latLng, url);

                    }

                    // Setting a custom info window adapter for the google map
                    googlezMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                        // Use default InfoWindow frame
                        @Override
                        public View getInfoWindow(Marker arg0) {
                            return null;
                        }

                        // Defines the contents of the InfoWindow
                        @Override
                        public View getInfoContents(Marker arg0) {

                            View v = getActivity().getLayoutInflater().inflate(R.layout.custom_infowindows, null);
                            TextView wvNombre = (TextView) v.findViewById(R.id.name);
                            TextView wvDirec = (TextView) v.findViewById(R.id.direccion);
                            ImageView wvImage = (ImageView) v.findViewById(R.id.fotos);
                            ImageView str1 = (ImageView) v.findViewById(R.id.str1);
                            ImageView str2 = (ImageView) v.findViewById(R.id.str2);
                            ImageView str3 = (ImageView) v.findViewById(R.id.str3);
                            ImageView str4 = (ImageView) v.findViewById(R.id.str4);
                            ImageView str5 = (ImageView) v.findViewById(R.id.str5);


                            try {
                                int id = mMarkers.get(arg0.getId());

                                Empresas platos = new Empresas();
                                platos = marks.get(id);
                                int ids = platos.getId();

                                Log.e("ids", String.valueOf(ids));
                                wvNombre.setText(platos.getNombre());
                                wvDirec.setText(platos.getDireccion());
                                String imag = platos.getImagen();

                                String link = "http://worldtripin.com/" + imag;
                                link = link.replace(" ", "%20") ;

                                URL linkz = null;

                                linkz = new URL(link);

                                Bitmap bmp = null;

                                bmp = BitmapFactory.decodeStream(linkz.openConnection().getInputStream());
                                bmp = getResizedBitmap(bmp, 100, 100);
                                wvImage.setImageBitmap(bmp);

                                int calification = platos.getCalificación();

                                if (calification < 2){
                                    onShowCalification(str1);
                                }

                                else if ((calification >= 2) && (calification < 3)){
                                    onShowCalification(str1);
                                    onShowCalification(str2);
                                }

                                else if ((calification >= 3) && (calification < 4)){
                                    onShowCalification(str1);
                                    onShowCalification(str2);
                                    onShowCalification(str3);
                                }

                                else if ((calification >= 4) && (calification < 5)){
                                    onShowCalification(str1);
                                    onShowCalification(str2);
                                    onShowCalification(str3);
                                    onShowCalification(str4);
                                }

                                else if (calification >= 5){
                                    onShowCalification(str1);
                                    onShowCalification(str2);
                                    onShowCalification(str3);
                                    onShowCalification(str4);
                                    onShowCalification(str5);
                                }

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            // Returning the view containing InfoWindow contents
                            return v;
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                error.printStackTrace();
                onShowError(error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("idSubCategoria",String.valueOf(emp));

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

    @Override
    public void onFillMarkers(String Name, String Direction, LatLng latLng, String url) {
        try {
            String imageUrl = "http://worldtripin.com/" + url;
            URL myurl = new URL(imageUrl);
            Bitmap bmp = BitmapFactory.decodeStream(myurl.openConnection().getInputStream());
            bmp = getResizedBitmap(bmp, 80, 80);

            MarkerOne = googlezMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                    .title(Nombre)
                    .snippet(Direccion)
                    .position(latLng)
                    .draggable(false));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    @Override
    public void onShowError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowCalification(ImageView str) {
        Picasso.with(getActivity())
                .load(R.drawable.stargold)
                .into(str);
    }


}