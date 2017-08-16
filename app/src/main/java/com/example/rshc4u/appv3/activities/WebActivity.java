package com.example.rshc4u.appv3.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.utils.Constants;

public class WebActivity extends AppCompatActivity {

    private WebView openUrlWeb;

    private ProgressBar progressBar;

    private String TAG = "webView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.content_web);


        openUrlWeb = (WebView) findViewById(R.id.open_url_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        WebSettings webSettings = openUrlWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptCookie(true);

        webSettings.setAppCacheEnabled(false);

        if (!Constants.DIRECTION_URL.isEmpty()) {


            openUrlWeb.loadUrl(Constants.DIRECTION_URL);

            openUrlWeb.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {

                    progressBar.setVisibility(View.GONE);

                }
            });

        }

    }


    @Override
    public void onBackPressed() {
        if (openUrlWeb.canGoBack()) {
            openUrlWeb.goBack();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && openUrlWeb.canGoBack()) {
            openUrlWeb.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        openUrlWeb.saveState(outState);
        Log.i(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        openUrlWeb.restoreState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState");
    }


}
