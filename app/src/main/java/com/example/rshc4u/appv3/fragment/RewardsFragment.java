package com.example.rshc4u.appv3.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.utils.Constants;

/**
 * Created by rshc4u on 8/16/17.
 */

public class RewardsFragment extends Fragment {


    private WebView openUrlWeb;

    private ProgressBar progressBar;

    private String TAG = "webView";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.web_fragment, container, false);


        openUrlWeb = (WebView) view.findViewById(R.id.open_url_webview);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);


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


        return view;

    }

}
