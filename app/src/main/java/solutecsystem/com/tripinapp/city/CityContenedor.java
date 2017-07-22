package solutecsystem.com.tripinapp.city;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import solutecsystem.com.tripinapp.Pojo.Citys;
import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.activitys.ContenedorEmpresaActivity;
import solutecsystem.com.tripinapp.adapters.ListCityAdapter;
import solutecsystem.com.tripinapp.configurations.Configs;
import solutecsystem.com.tripinapp.tabs.PaginaTabAdapter;

public class CityContenedor extends AppCompatActivity{

    ListView Cityz;
    ArrayList<Citys> arraycitys = new ArrayList<Citys>();
    ArrayList<Empresas> arrayempre = new ArrayList<Empresas>();
    RequestQueue requestQueue;
    ArrayList<String> ctis = new ArrayList<String>();
    Spinner Spincity;
    String _message;
    private ProgressDialog pDialog;

    int Idcity;

    ListCityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        Spincity = (Spinner) findViewById(R.id.spincity);
        _message = getResources().getString(R.string.message_load);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spincity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Pos", String.valueOf(i));
                if (i!=0) {
                    int j = i - 1;
                    Idcity = arraycitys.get(j).getId();
                    fillempresas();
                    Log.e("ID", String.valueOf(Idcity));
                    String idi = Configs.getIdiom();
                    Log.e("idioma", idi);
                    Configs.setIdCity(Idcity);
                    Intent iz = new Intent(CityContenedor.this, ContenedorEmpresaActivity.class);
                    startActivity(iz);
                    CityContenedor.this.finish();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(isNetworkAvailable()){
           city();
        }
    }

    private void showpDialog() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage(_message);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void hidepDialog() {
        pDialog.dismiss();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void city(){
        showpDialog();
        JsonArrayRequest arrayreq = new JsonArrayRequest(Configs.URL_CITY,
                // The second parameter Listener overrides the method onResponse() and passes
                //JSONArray as a parameter
                new Response.Listener<JSONArray>() {
                    // Takes the response from the JSON request
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            ctis.add(getResources().getString(R.string.no_select));
                            for (int i = 0; i < response.length(); i++) {
                                    JSONObject c = (JSONObject) response.get(i);
                                    Citys cates = new Citys();
                                    cates.setId(c.getInt(Configs.TAG_ID));
                                    cates.setCiudad(c.getString(Configs.TAG_CITY));
                                    cates.setDepartamento(c.getString(Configs.TAG_ESTATE));
                                    cates.setPais(c.getString(Configs.TAG_COUNTRY));
                                    arraycitys.add(cates);

                                    ctis.add(c.getString(Configs.TAG_CITY));
                            }
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    hidepDialog();
                                    Spincity.setAdapter(
                                            new ArrayAdapter<String>(CityContenedor.this,
                                                     android.R.layout.simple_spinner_dropdown_item,
                                                     ctis));
                                }
                            });
                        }
                        // Try and catch are included to handle any errors due to JSON
                        catch (JSONException e) {
                            // If an error occurs, this prints the error to the log
                            e.printStackTrace();
                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    // Handles errors that occur due to Volley
                    public void onErrorResponse(VolleyError error) {
                        hidepDialog();
                        Toast.makeText(CityContenedor.this,
                                "Hubo un error al realizar la conexión con el servidor, por favor intente mas tarde",
                                Toast.LENGTH_LONG).show();
                    }
                }
        );

        arrayreq.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(arrayreq);
    }

    public void fillempresas(){
        StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_STORESEARCH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray respon = new JSONArray(response);

                    for (int i = 0; i < respon.length(); i++) {
                        JSONObject c = (JSONObject) respon.get(i);
                        Empresas empre = new Empresas();
                        empre.setNombre(c.getString(Configs.TAG_tit));
                        empre.setLatitud(c.getString(Configs.TAG_LAT));
                        empre.setLongitud(c.getString(Configs.TAG_LON));
                        empre.setImagen(c.getString(Configs.TAG_IMAG));
                        arrayempre.add(empre);
                    }
                    Configs.setArrgps(arrayempre);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("idCiudad",String.valueOf(Idcity));

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
    protected void onDestroy() {
        super.onDestroy();
        CityContenedor.this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CityContenedor.this.finish();
    }
}
