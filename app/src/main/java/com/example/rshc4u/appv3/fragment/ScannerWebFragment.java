package com.example.rshc4u.appv3.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.rshc4u.appv3.R;

/**
 * Created by rshc4u on 8/26/17.
 */

public class ScannerWebFragment extends Fragment {

    private WebView webView;
    private static final String ARG_URL = "url";


    private String mUrl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_scaner, container, false);


        webView = (WebView) view.findViewById(R.id.scan_webview);

        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(mUrl);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(ARG_URL);

        }
    }


    public static ScannerWebFragment newInstance(String param1) {
        ScannerWebFragment fragment = new ScannerWebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


            view.loadUrl(url);
            return true;

        }
    }


}