package solutecsystem.com.tripinapp.maps.MapsPresentator;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by shadowns on 20/04/17.
 */

public interface MapView {
    void onFillMarkers(String Name, String Direction, LatLng latLng, String url);
    Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight);
    void onShowError(String msg);
    void onShowCalification(ImageView imageView);
}
