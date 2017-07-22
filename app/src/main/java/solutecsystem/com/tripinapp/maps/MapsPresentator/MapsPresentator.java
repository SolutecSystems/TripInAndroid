package solutecsystem.com.tripinapp.maps.MapsPresentator;

/**
 * Created by shadowns on 20/04/17.
 */

public interface MapsPresentator {
    void setPublication();
    void onPublicate();
    void onShowPublic();
    void onTimerPublication();
    void onDownloadData();
    void onCallStore();
    void onDestroy();
    void onPausePublication();
}
