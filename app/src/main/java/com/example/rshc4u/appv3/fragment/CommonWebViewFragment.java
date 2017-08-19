package com.example.rshc4u.appv3.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.rshc4u.appv3.R;

/**
 * Created by rshc4u on 8/19/17.
 */

public class CommonWebViewFragment extends Fragment {


    private static final String ARG_URL = "url";


    private String mUrl;
    public static WebView webView;
    public static boolean isLoading = false;

    ReloadJsonCallback reload;


    public interface ReloadJsonCallback {
        public void reload();

    }


    public CommonWebViewFragment() {
        // Required empty public constructor
    }


    public static CommonWebViewFragment newInstance(String param1) {
        CommonWebViewFragment fragment = new CommonWebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(ARG_URL);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.web_fragment, container, false);

        /*
        MenuBus.leftarrow.setVisibility(View.GONE);
        MenuBus.cartContainer.setVisibility(View.VISIBLE);

*/
        webView = (WebView) v.findViewById(R.id.open_url_webview);
        webView.setWebViewClient(new HelloWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        //      webView.getSettings().setGeolocationEnabled(true);

        webView.loadUrl(mUrl);


        return v;
    }


    class HelloWebViewClient extends WebViewClient {


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
            isLoading = true;

            //  Toast.makeText(paramWebView.getContext(), "Started", Toast.LENGTH_SHORT).show();
//DO YOU STUFF IF NEEDED

        }

        public void onPageFinished(WebView paramWebView, String paramString) {

            try {
                String cookies = CookieManager.getInstance().getCookie(paramString);
                String SharedPrefName = "menubus";
                SharedPreferences preferences = getActivity().getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("cookie", cookies);
                editor.commit();
                Log.d("MenuBus", "All the cookies in a string:" + cookies);
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
            isLoading = false;
            reload.reload();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            reload = (ReloadJsonCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnItemClickedListener");
        }
    }
}
