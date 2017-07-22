package solutecsystem.com.tripinapp.activitys;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.city.CityContenedor;
import solutecsystem.com.tripinapp.tabs.PaginaTabAdapter;

public class ContenedorEmpresaActivity extends AppCompatActivity {

    String _cates;
    String _Stores;
    String _TripIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_empresa);

        _cates = getResources().getString(R.string.categories);
        _Stores = getResources().getString(R.string.stores);
        _TripIt = getResources().getString(R.string.tripit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(_cates));
        tabLayout.addTab(tabLayout.newTab().setText(_TripIt));
        tabLayout.addTab(tabLayout.newTab().setText(_Stores));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PaginaTabAdapter adapter = new PaginaTabAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                adapter.notifyDataSetChanged();
            }
        });

        viewPager.setCurrentItem(0);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(ContenedorEmpresaActivity.this, CityContenedor.class);
        startActivity(i);
        ContenedorEmpresaActivity.this.finish();
    }
}
