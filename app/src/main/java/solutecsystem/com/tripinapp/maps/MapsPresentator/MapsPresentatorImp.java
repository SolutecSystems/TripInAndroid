package solutecsystem.com.tripinapp.maps.MapsPresentator;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import solutecsystem.com.tripinapp.maps.MapFragment;

/**
 * Created by shadowns on 20/04/17.
 */

public class MapsPresentatorImp implements MapsPresentator {

    MapView view;
    Context context;

    public MapsPresentatorImp(MapFragment fragment, FragmentActivity activity) {
        this.view = fragment;
        this.context = activity;
    }

    @Override
    public void setPublication() {

    }

    @Override
    public void onPublicate() {

    }

    @Override
    public void onShowPublic() {

    }

    @Override
    public void onTimerPublication() {

    }

    @Override
    public void onDownloadData() {

    }

    @Override
    public void onCallStore() {

    }

    @Override
    public void onDestroy() {
        view = null;
        context = null;
    }

    @Override
    public void onPausePublication() {

    }
}
