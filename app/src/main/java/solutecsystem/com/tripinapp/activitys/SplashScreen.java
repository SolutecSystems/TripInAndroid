package solutecsystem.com.tripinapp.activitys;

/**
 * Created by Shadowns on 27/12/2016.
 */

        import android.content.Context;
        import android.content.Intent;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.Toast;

        import com.android.volley.DefaultRetryPolicy;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.Locale;
        import java.util.Timer;
        import java.util.TimerTask;

        import ch.halcyon.squareprogressbar.SquareProgressBar;
        import solutecsystem.com.tripinapp.Pojo.Categorias;
        import solutecsystem.com.tripinapp.Pojo.Empresas;
        import solutecsystem.com.tripinapp.R;
        import solutecsystem.com.tripinapp.city.CityContenedor;
        import solutecsystem.com.tripinapp.configurations.Configs;
        import solutecsystem.com.tripinapp.subcategories.ContenedorSubcategories;

public class SplashScreen extends AppCompatActivity {

    private int numlistconf = Configs.getAlac();
    RequestQueue requestQueue;
    String lenguage;
    private static final long SPLASH_SCREEN_DELAY = 2600;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idioma);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        if (isNetworkAvailable()){
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    lenguage =  Locale.getDefault().getLanguage();
                    Configs.setIdiom(lenguage);
                    startActivity(new Intent(getApplicationContext(), CityContenedor.class));
                    SplashScreen.this.finish();
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, SPLASH_SCREEN_DELAY);

        } else {
            Toast.makeText(this, "No dispone de Conexión a Internet, revise e intente nuevamente", Toast.LENGTH_LONG).show();
            SplashScreen.this.finish();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
