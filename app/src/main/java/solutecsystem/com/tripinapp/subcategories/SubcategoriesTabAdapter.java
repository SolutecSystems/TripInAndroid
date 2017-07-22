package solutecsystem.com.tripinapp.subcategories;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import solutecsystem.com.tripinapp.maps.MapFragment;
import solutecsystem.com.tripinapp.tabs.Tripittab;

/**
 * Created by Anthony on 03/11/2016.
 */

public class SubcategoriesTabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public SubcategoriesTabAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Subcategories tab1 = new Subcategories().NewInstance();
                return tab1;
            case 1:
                Tripittab tab2 = new  Tripittab().NewInstance();
                return tab2;
            case 2:
                MapFragment tab4 = new  MapFragment().NewInstance();
                return tab4;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}