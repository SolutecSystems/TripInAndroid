package solutecsystem.com.tripinapp.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import solutecsystem.com.tripinapp.R;
import solutecsystem.com.tripinapp.configurations.Configs;

/**
 * Created by shadowns on 27/03/17.
 */

public class WebActivity extends AppCompatActivity {

    String Weburl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weblayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Weburl = Configs.getURL();

        int res = Weburl.indexOf("http://");
        int resz =  Weburl.indexOf("https://");
        if ((res != -1) || (resz != -1)) {
            Log.e("Weburl", Weburl);
        } else{
            Weburl= "http://" + Weburl;
        }


        final WebView browser = (WebView) findViewById(R.id.webview);
        browser.setWebViewClient(new MyBrowser());

        WebActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                browser.loadUrl(Weburl);
                browser.getSettings().setLoadsImagesAutomatically(true);
                browser.getSettings().setJavaScriptEnabled(true);
                browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            }
        });


    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
