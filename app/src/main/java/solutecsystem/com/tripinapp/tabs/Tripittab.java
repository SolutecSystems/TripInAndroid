package solutecsystem.com.tripinapp.tabs;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import solutecsystem.com.tripinapp.Pojo.Subcategorias;
import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.activitys.ContenedorEmpresaActivity;
import solutecsystem.com.tripinapp.adapters.GradAdapter;
import solutecsystem.com.tripinapp.adapters.GradSubAdapter;
import solutecsystem.com.tripinapp.adapters.LazyAdapter;
import solutecsystem.com.tripinapp.adapters.ListSubAdapter;
import solutecsystem.com.tripinapp.configurations.Configs;
import solutecsystem.com.tripinapp.configurations.Settings;
import solutecsystem.com.tripinapp.stores.ContenedorStore;


public class Tripittab extends Fragment {
    ListView listacategories;
    GridView GridCategories;
    CoordinatorLayout coordinator;
    ListSubAdapter adapter;
    GradSubAdapter sadapter;
    private int num = Configs.getList();
    private int param = 51;
    String idiom;

    String _no_internet;
    String _message;

    int idcity = Configs.getIdCity();

    private ProgressDialog pDialog;

    RequestQueue requestQueue;
    ArrayList<Subcategorias> arrayOfUsers = new ArrayList<Subcategorias>();

    static final String TAG_tit = "Nombre";
    static final String TAG_IMAG = "Imagen";

    public Tripittab NewInstance() {
        Tripittab In = new Tripittab();
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
                arrayOfUsers.clear();
                listacategories.setAdapter(null);
                llenarlist();

                listacategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        int Id =  arrayOfUsers.get(i).getId();
                        Configs.setEmp(Id);
                        Intent iz = new Intent(getActivity(), ContenedorStore.class);
                        startActivity(iz);

                    }
                });
            } else if (num == 1){
                arrayOfUsers.clear();
                GridCategories.setAdapter(null);
                fillgrid();

                GridCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        int Id =  arrayOfUsers.get(i).getId();
                        Configs.setEmp(Id);
                        Intent iz = new Intent(getActivity(), ContenedorStore.class);
                        startActivity(iz);

                    }
                });
            }

        }else {
            mostrarError(_no_internet);
        }
    }

    public void llenarlist(){

        listacategories.setVisibility(View.VISIBLE);

        StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_SUBCATEGORIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray respon = new JSONArray(response);

                    Log.e("Respuesta", respon.toString());

                    for (int i = 0; i < respon.length(); i++) {
                        JSONObject c = (JSONObject) respon.get(i);
                        Subcategorias cates = new Subcategorias();
                        if (idiom.equals("es")){
                            cates.setNombre(c.getString(Configs.TAG_tit));
                        } else {
                            cates.setNombre(c.getString(Configs.TAG_titing));
                        }
                        cates.setId(c.getInt(Configs.TAG_ID));
                        cates.setImagen(c.getString(Configs.TAG_IMAG));
                        arrayOfUsers.add(cates);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {

                            Log.e("Respuesta", arrayOfUsers.toString());
                            adapter = new ListSubAdapter(getActivity(), arrayOfUsers);
                            listacategories.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
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
                params.put("idCategoria",String.valueOf(param));
                params.put("idCiudad",String.valueOf(idcity));
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

    private void fillgrid(){
        showpDialog();

        GridCategories.setVisibility(View.VISIBLE);

        StringRequest sr= new StringRequest(Request.Method.POST, Configs.URL_SUBCATEGORIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray respon = new JSONArray(response);

                    for (int i = 0; i < respon.length(); i++) {
                        JSONObject c = (JSONObject) respon.get(i);
                        Subcategorias cates = new Subcategorias();
                        cates.setId(c.getInt(Configs.TAG_ID));
                        cates.setNombre(c.getString(Configs.TAG_tit));
                        cates.setImagen(c.getString(Configs.TAG_IMAG));
                        arrayOfUsers.add(cates);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            hidepDialog();
                            sadapter = new GradSubAdapter(getActivity(), arrayOfUsers);
                            GridCategories.setAdapter(sadapter);
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
                params.put("idCategoria",String.valueOf(param));
                params.put("idCiudad",String.valueOf(idcity));

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
        inflater.inflate(R.menu.menu_main, menu);
        if (num == 0)
            menu.getItem(1).setIcon(R.drawable.cuadrado);
        else
            menu.getItem(1).setIcon(R.drawable.l1);


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_buscar:
                return false;

            case R.id.action_lista:

                if (num == 0) {
                    num = 1;
                    Configs.setList(1);
                    getActivity().finish();
                    Intent iz = new Intent(getActivity(), ContenedorEmpresaActivity.class);
                    startActivity(iz);
                } else {
                    num = 0;
                    Configs.setList(0);
                    getActivity().finish();
                    Intent ix = new Intent(getActivity(), ContenedorEmpresaActivity.class);
                    startActivity(ix);
                }

                return false;

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
