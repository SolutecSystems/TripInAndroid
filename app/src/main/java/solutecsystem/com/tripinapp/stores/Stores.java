package solutecsystem.com.tripinapp.stores;

/**
 * Created by Shadowns on 28/12/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.GridView;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import solutecsystem.com.tripinapp.Pojo.Empresas;
import solutecsystem.com.tripinapp.Pojo.Subcategorias;
import solutecsystem.com.tripinapp.Pojo.Tags;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.activitys.Storeinformation;
import solutecsystem.com.tripinapp.adapters.GradStoreAdapter;
import solutecsystem.com.tripinapp.adapters.GradSubAdapter;
import solutecsystem.com.tripinapp.adapters.ListStoreAdapter;
import solutecsystem.com.tripinapp.adapters.ListSubAdapter;
import solutecsystem.com.tripinapp.configurations.Configs;
import solutecsystem.com.tripinapp.configurations.Settings;


public class Stores extends Fragment {

    ListView listacategories;
    GridView GridCategories;
    CoordinatorLayout coordinator;
    ListStoreAdapter adapter;
    GradStoreAdapter sadapter;
    private int num = Configs.select;
    private int emp = Configs.emp;
    private int calif = 0;
    private int n = 0;

    private String idio = Configs.getIdiom();

    JSONArray objs;

    String _no_internet , _horarioWeek, _HorarioWeekend;
    String _message;

    int finC;
    String idiom;
    private ProgressDialog pDialog;

    String tag;
    RequestQueue requestQueue;
    ArrayList<Empresas> arrayOfUsers = new ArrayList<Empresas>();
    ArrayList<Empresas> arraystore = new ArrayList<Empresas>();

    public Stores NewInstance() {
        Stores In = new Stores();
        return In;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        listacategories = (ListView) view.findViewById(R.id.Listcategories);
        GridCategories = (GridView) view.findViewById(R.id.Gridcategories);
        coordinator = (CoordinatorLayout) view.findViewById(R.id.coordinatorLayout2);

        requestQueue = Volley.newRequestQueue(getActivity());
        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        _no_internet = getResources().getString(R.string.no_internet);
        _message = getResources().getString(R.string.message_load);

        idiom = Locale.getDefault().getLanguage();

        if (isNetworkAvailable()) {
            if (num == 0){
                listacategories.setVisibility(View.VISIBLE);
                llenarlist();

                listacategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        fillinfo(i);
                    }
                });

            } else if (num == 1){

                arrayOfUsers.clear();
                GridCategories.setAdapter(null);
            }


        }else {
            mostrarError(_no_internet);
        }
    }

    private void fillinfo(int i) {
        arraystore.clear();
        Intent iz = new Intent(getActivity(), Storeinformation.class);

        Empresas esta = new Empresas();
        esta.setId(arrayOfUsers.get(i).getId());
        esta.setNombre(arrayOfUsers.get(i).getNombre());
        esta.setImagen(arrayOfUsers.get(i).getImagen(i));
        esta.setHorarioweek(arrayOfUsers.get(i).getHorarioweek());
        esta.setHorarioweekend(arrayOfUsers.get(i).getHorarioweekend());
        esta.setHistoria(arrayOfUsers.get(i).getHistoria());
        esta.setLicencia(arrayOfUsers.get(i).getLicencia());
        esta.setDireccion(arrayOfUsers.get(i).getDireccion());
        esta.setTelefono(arrayOfUsers.get(i).getTelefono());
        esta.setLatitud(arrayOfUsers.get(i).getLatitud());
        esta.setLongitud(arrayOfUsers.get(i).getLongitud());
        esta.setFb(arrayOfUsers.get(i).getFb());
        esta.setPortada(arrayOfUsers.get(i).getPortada());
        esta.setInsta(arrayOfUsers.get(i).getInsta());
        esta.setTwt(arrayOfUsers.get(i).getTwt());
        esta.setGg(arrayOfUsers.get(i).getGg());
        esta.setUrl(arrayOfUsers.get(i).getUrl());
        esta.setCalificación(arrayOfUsers.get(i).getCalificación());
        esta.setTelefono2(arrayOfUsers.get(i).getTelefono2());
        esta.setEmail(arrayOfUsers.get(i).getEmail());

        arraystore.add(esta);
        Configs.setArrStre(arraystore);
        startActivity(iz);
    }

    public void llenarlist(){
        showpDialog();
        arrayOfUsers.clear();
        listacategories.setAdapter(null);

        StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_STORES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray respon = new JSONArray(response);//
                    for (int i = 0; i < respon.length(); i++) {

                        JSONObject c = (JSONObject) respon.get(i);
                        Log.e("Objeto", c.toString());

                        String Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo;

                        Lunes = c.getString(Configs.TAG_HORARIOLU);
                        Martes = c.getString(Configs.TAG_HORARIOMA);
                        Miercoles = c.getString(Configs.TAG_HORARIOMI);
                        Jueves = c.getString(Configs.TAG_HORARIOJU);
                        Viernes = c.getString(Configs.TAG_HORARIOVI);
                        Sabado = c.getString(Configs.TAG_HORARIOSA);
                        Domingo = c.getString(Configs.TAG_HORARIODO);

                        if (Lunes.equals("null")){
                            Lunes = "Cerrado";
                        }
                        if (Martes.equals("null")){
                            Martes = "Cerrado";
                        }
                        if (Miercoles.equals("null")){
                            Miercoles = "Cerrado";
                        }
                        if (Jueves.equals("null")){
                            Jueves = "Cerrado";
                        }
                        if (Viernes.equals("null")){
                            Viernes = "Cerrado";
                        }
                        if (Sabado.equals("null")){
                            Sabado = "Cerrado";
                        }
                        if (Domingo.equals("null")){
                            Domingo = "Cerrado";
                        }

                        if (Lunes.equals(Martes)
                                && Lunes.equals(Miercoles)
                                && Lunes.equals(Jueves)
                                && Lunes.equals(Viernes))
                        {
                            _horarioWeek = "Lunes a Viernes: " + Lunes;
                        } else{
                            _horarioWeek = "Lunes: " + Lunes + "\n"
                                    + "Martes: " + Martes + "\n"
                                    + "Miercoles: " + Miercoles + "\n"
                                    + "Jueves: " + Jueves + "\n"
                                    + "Viernes: " + Viernes + "\n";
                        }

                        _HorarioWeekend =  "Sabado : " + Sabado + "\n"
                                + "Domingo: " + Domingo + "\n";

                        Empresas cates = new Empresas();
                        cates.setId(c.getInt(Configs.TAG_ID));
                        cates.setNombre(c.getString(Configs.TAG_tit));
                        cates.setLicencia(c.getString(Configs.TAG_LICENCIA));
                        cates.setImagen(c.getString(Configs.TAG_IMAG));
                        cates.setHorarioweek(_horarioWeek);
                        cates.setHorarioweekend(_HorarioWeekend);

                        if(idiom.equals("es"))
                            cates.setHistoria(c.getString(Configs.TAG_HISTORY));
                        else
                            cates.setHistoria(c.getString(Configs.TAG_HISTORYING));

                        cates.setTelefono(c.getString(Configs.TAG_TEL));
                        cates.setLatitud(c.getString(Configs.TAG_LAT));
                        cates.setDireccion(c.getString(Configs.TAG_DIR));
                        cates.setLongitud(c.getString(Configs.TAG_LON));
                        cates.setPortada(c.getString(Configs.TAG_PORTADA));
                        cates.setFb(c.getString(Configs.TAG_FACE));
                        cates.setInsta(c.getString(Configs.TAG_IN));
                        cates.setTwt(c.getString(Configs.TAG_TW));
                        cates.setGg(c.getString(Configs.TAG_GG));
                        cates.setTelefono2(c.getString(Configs.TAG_TELEFONO2));
                        cates.setEmail(c.getString(Configs.TAG_MAIL));
                        cates.setUrl(c.getString(Configs.TAG_URL));

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
                        cates.setCalificación(finC);

                        tag = " ";

                        if (idiom.equals("es")){
                            objs = c.getJSONArray("etiquetaEsp");
                        } else {
                            objs = c.getJSONArray(Configs.TAG_ETIING);
                        }

                        for (int j = 0; j < objs.length(); j++)
                        {
                            JSONObject jz = objs.getJSONObject(j);
                            tag = tag + " " + jz.getString("nombre");
                        }

                        cates.setEtiquetas(tag);

                        arrayOfUsers.add(cates);
                    }
                        getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                hidepDialog();
                                adapter = new ListStoreAdapter(getActivity(), arrayOfUsers);
                                listacategories.setAdapter(adapter);
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

    private void showpDialog() {
        Log.e("Respo", _message);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage(_message);
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    private void hidepDialog() {
        pDialog.dismiss();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_store, menu);
        MenuItem searchItem = menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {

                if (num == 0) {

                    adapter.filter(searchQuery.toString().trim());
                    listacategories.invalidate();

                }else if (num == 1) {

                    sadapter.filter(searchQuery.toString().trim());
                    GridCategories.invalidate();

                }
                return true;
            }
        });

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

            default:
                break;
        }

        return false;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void mostrarError(String mssg) {
        Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);

        Snackbar snackbar = Snackbar
                .make(coordinator, mssg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
