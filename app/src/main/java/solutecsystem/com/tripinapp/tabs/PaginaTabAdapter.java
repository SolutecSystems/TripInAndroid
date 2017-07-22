package solutecsystem.com.tripinapp.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import solutecsystem.com.tripinapp.maps.MapFragment;

/**
 * Created by Anthony on 03/11/2016.
 */

public class PaginaTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PaginaTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tabcategories tab1 = new Tabcategories().NewInstance();
                return tab1;
            case 1:
                Tripittab tab2 = new  Tripittab().NewInstance();
                return tab2;
            case 2:
                MapFragment tab3 = new  MapFragment().NewInstance();
                return tab3;
//                Tripittab tab3 = new  Tripittab().NewInstance();
//                return tab3;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}