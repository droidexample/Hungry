package com.example.rshc4u.appv3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rshc4u.appv3.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class ScannerActivity extends AppCompatActivity {


    private IntentIntegrator qrScan;
    private WebView openUrlWeb;

    private ProgressBar progressBar;

    private String TAG = "webView";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize scan object

        qrScan = new IntentIntegrator(this);


        qrScan.initiateScan();


        openUrlWeb = (WebView) findViewById(R.id.scan_webview);
        progressBar = (ProgressBar) findViewById(R.id.scan_progressBar);


        WebSettings webSettings = openUrlWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        CookieManager.getInstance().setAcceptCookie(true);

        webSettings.setAppCacheEnabled(false);


    }


    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                // Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();

                startActivity(new Intent(ScannerActivity.this, MainContainerActivity.class));

            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());

                    //  textViewName.setText(obj.getString("name"));
                    // textViewAddress.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();

                    Log.e("URL", result.getContents());


                    if (!result.getContents().isEmpty()) {


                        openUrlWeb.loadUrl(result.getContents());

                        openUrlWeb.setWebViewClient(new WebViewClient() {

                            public void onPageFinished(WebView view, String url) {

                                progressBar.setVisibility(View.GONE);

                            }
                        });

                    }
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
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
