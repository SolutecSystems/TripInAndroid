package solutecsystem.com.tripinapp.configurations;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.activitys.ContenedorEmpresaActivity;
import solutecsystem.com.tripinapp.activitys.SplashScreen;
import solutecsystem.com.tripinapp.city.CityContenedor;

/**
 * Created by Shadowns on 04/01/2017.
 */

public class Settings extends AppCompatActivity {

    public ImageView logo;

    Locale LocaleEs, LocaleEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinlenguage);

        List<String> idioms = new ArrayList<String>();
        idioms.add(getResources().getString(R.string.Idiom));
        idioms.add(getResources().getString(R.string.spanish));
        idioms.add(getResources().getString(R.string.english));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, idioms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView arg0, View arg1,
                                       int arg2, long arg3) {
                switch (arg2) {
                    case 0:
                        break;

                    case 1:
                        LocaleEs = new Locale("es", "ES");
                        setLocale(LocaleEs);
                        finish();
                        Intent iz = new Intent(Settings.this, CityContenedor.class);
                        startActivity(iz);
                        break;
                    case 2:
                        LocaleEn = Locale.ENGLISH;
                        setLocale(LocaleEn);
                        finish();
                        Intent xz = new Intent(Settings.this, CityContenedor.class);
                        startActivity(xz);
                        break;
                }
            }

            public void onNothingSelected(AdapterView arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void setLocale(Locale locale) {
        Locale.setDefault(locale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
    }
}
