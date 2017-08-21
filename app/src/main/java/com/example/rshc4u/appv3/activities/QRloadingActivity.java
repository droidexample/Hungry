package com.example.rshc4u.appv3.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.fragment.CommonWebViewFragment;

public class QRloadingActivity extends AppCompatActivity {

    private Context mContext;
    private String mUrl;
    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrloading);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = getApplicationContext();


        webView = (WebView) findViewById(R.id.open_url_webview);
        webView.setWebViewClient(new WebViewLoder());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        //      webView.getSettings().setGeolocationEnabled(true);


        try {
            mUrl = getIntent().getStringExtra("qr_url");

            webView.loadUrl(mUrl);
        } catch (Exception e) {
            Log.e("URL", "error link");
        }

    }


    class WebViewLoder extends WebViewClient {


        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


            webViewPreviousState = PAGE_REDIRECTED;
            view.loadUrl(url);
            return true;
        }

        public int webViewPreviousState;
        public ProgressDialog dialog = null;


        public int PAGE_STARTED = 0x1;
        public int PAGE_REDIRECTED = 0x2;

        public void onPageStarted(WebView paramWebView, String paramString,
                                  Bitmap paramBitmap) {
            super.onPageStarted(paramWebView, paramString, paramBitmap);
            webViewPreviousState = PAGE_STARTED;
            if (dialog == null || !dialog.isShowing())
                dialog = ProgressDialog.show(paramWebView.getContext(), "Please wait...", "Loading", true, true,
                        new ProgressDialog.OnCancelListener() {


                            @Override
                            public void onCancel(DialogInterface dialog) {
                                dialog.dismiss();
                                dialog = null;
                            }
                        });
            dialog.setCancelable(true);

            //  Toast.makeText(paramWebView.getContext(), "Started", Toast.LENGTH_SHORT).show();
//DO YOU STUFF IF NEEDED

        }

        public void onPageFinished(WebView paramWebView, String paramString) {

            try {
                String cookies = CookieManager.getInstance().getCookie(paramString);
                String SharedPrefName = "menubus";
                SharedPreferences preferences = mContext.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("cookie", cookies);
                editor.commit();
                Log.e("MenuBus", "All the cookies in a string:" + cookies);
                String cookieVaule = "";
                String[] temp = cookies.split(";");
                for (String ar1 : temp) {
                    if (ar1.contains("notifier-id")) {
                        String[] temp1 = ar1.split("=");
                        cookieVaule = temp1[1];


                        editor.putString("cookie", cookies);
                        editor.commit();

                        if (cookieVaule != null && cookieVaule != "") {

                            editor.putString("notifier-id", cookieVaule);
                            editor.commit();
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (webViewPreviousState == PAGE_STARTED && dialog != null) {
                dialog.dismiss();
                dialog = null;
                //Toast.makeText(paramWebView.getContext(), "Ended", Toast.LENGTH_SHORT).show();
                //I AM GETTING HERE WHEN MY PAGE IS LOADED 100%
                //NOW ITS TIME FOR ME TO RUN JAVASCRIPT FUNCTIONS
                //DO YOUR STUFF
            }

        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(QRloadingActivity.this, MainContainerActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        startActivity(intent);
    }
}
