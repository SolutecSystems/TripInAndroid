package solutecsystem.com.tripinapp.stores;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import solutecsystem.com.tripinapp.maps.MapFragment;

/**
 * Created by Shadowns on 03/01/2017.
 */

public class Storestab extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public Storestab(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Stores tab1 = new Stores().NewInstance();
                return tab1;
            case 1:
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
