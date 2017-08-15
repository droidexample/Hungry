package com.example.rshc4u.appv3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.R;

/**
 * Created by rshc4u on 8/15/17.
 */

public class Login_fragment extends Fragment {

    private WebView openUrlWeb;

    private ProgressBar progressBar;

    private String TAG = "webView";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment, container, false);


        openUrlWeb = (WebView) view.findViewById(R.id.open_url_webview);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        WebSettings webSettings = openUrlWeb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(false);

        if (!Constants.url.isEmpty()) {


            openUrlWeb.loadUrl(Constants.url);

            openUrlWeb.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {

                    progressBar.setVisibility(View.GONE);

                }
            });

        }


        return view;

    }

}
