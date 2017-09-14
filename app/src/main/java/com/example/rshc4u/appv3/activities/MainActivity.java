package com.example.rshc4u.appv3.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.adapter.MenuAdapter;
import com.example.rshc4u.appv3.adapter.PullMenuAdapter;
import com.example.rshc4u.appv3.api.AppClient;
import com.example.rshc4u.appv3.api.ApplicationConfig;
import com.example.rshc4u.appv3.data_model.home_data.FrontPageJson;
import com.example.rshc4u.appv3.data_model.home_data.HomeContent;
import com.example.rshc4u.appv3.data_model.home_data.PullupContent;
import com.example.rshc4u.appv3.data_model.menu_content.Pullup;
import com.example.rshc4u.appv3.fragment.CommonWebViewFragment;
import com.example.rshc4u.appv3.fragment.HomeFragment;
import com.example.rshc4u.appv3.fragment.ScannerWebFragment;
import com.example.rshc4u.appv3.services.NotificationService;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.InternetChecker;
import com.example.rshc4u.appv3.utils.JSONParser;
import com.example.rshc4u.appv3.utils.RecyclerTouchListener;
import com.example.rshc4u.appv3.utils.URLParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements URLParams, CommonWebViewFragment.ReloadJsonCallback {

    private DrawerLayout drawer;
    private ImageView menuLeft;
    private ImageView menuRight;
    private ImageView toolBarLogo;
    private TextView tvBadgeCounter;

    private NavigationView navigationViewRight;
    private NavigationView navigationViewLeft;

    private CoordinatorLayout main_coordinator_layout;
    private ProgressBar navProgressBar;
    private RecyclerView pull_recycler;
    // private String pull_url;
    private Context mContext;

    String device_id = "";


    private ActionBarDrawerToggle toggle;

    private LinearLayoutManager mLayoutManager;
    private ListView menuList;

    private Toolbar toolbar;
    private boolean currentHomeStatus = false;
    ArrayList<PullupContent> pullupContentArrayList;

    private PullMenuAdapter pullMenuAdapter;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container_acitivty);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        main_coordinator_layout = (CoordinatorLayout) findViewById(R.id.main_coordinator_layout);
        menuLeft = (ImageView) findViewById(R.id.menuLeft);
        menuRight = (ImageView) findViewById(R.id.menuRight);
        toolBarLogo = (ImageView) findViewById(R.id.toolbar_logo);
        tvBadgeCounter = (TextView) findViewById(R.id.tvbadgeCounter);

        navigationViewLeft = (NavigationView) findViewById(R.id.nav_view_left);
        navigationViewRight = (NavigationView) findViewById(R.id.nav_view_right);
        menuList = (ListView) findViewById(R.id.menuList);

        pull_recycler = (RecyclerView) findViewById(R.id.pull_recycler_view);

        mContext = getApplicationContext();
        navProgressBar = (ProgressBar) findViewById(R.id.navProgressBar);
        mLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        pull_recycler.setLayoutManager(mLayoutManager);
        pull_recycler.setItemAnimator(new DefaultItemAnimator());


        if (InternetChecker.isNetworkAvailable(mContext)) {

            new JSONParse().execute();

            getPullData();

        } else {
            Snackbar.make(main_coordinator_layout, "Please check internet connection !", 10000).show();
        }


        menuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);

                } else {
                    drawer.openDrawer(GravityCompat.START);

                }
            }
        });


        menuRight.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                // getPullData();

                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);

                } else {
                    drawer.openDrawer(GravityCompat.END);


                }
            }
        });


        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();


        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {

                    setHomePage();

                    pull_recycler.setVisibility(View.VISIBLE);
                    menuRight.setVisibility(View.VISIBLE);
                    navigationViewRight.setVisibility(View.VISIBLE);

                } else if (i == 1) {
                    currentHomeStatus = false;
                    startActivity(new Intent(MainActivity.this, ScannerActivity.class));

                    pull_recycler.setVisibility(View.GONE);
                    menuRight.setVisibility(View.GONE);
                    navigationViewRight.setVisibility(View.GONE);
                } else if (getSupportFragmentManager().findFragmentByTag("123") == null) {

                    currentHomeStatus = false;

                    Fragment f = CommonWebViewFragment.newInstance(preferences.getString("url_" + i, "123"));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, f, "123");
                    fragmentTransaction.commit();

                    pull_recycler.setVisibility(View.GONE);
                    menuRight.setVisibility(View.GONE);
                    navigationViewRight.setVisibility(View.GONE);

                } else {
                    CommonWebViewFragment.webView.loadUrl(preferences.getString("url_" + i, "123"));
                    pull_recycler.setVisibility(View.GONE);
                    menuRight.setVisibility(View.GONE);
                    navigationViewRight.setVisibility(View.GONE);
                }

                drawer.closeDrawer(Gravity.LEFT);
                new JSONParse().execute();
            }
        });


        pull_recycler.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, pull_recycler,
                new RecyclerTouchListener.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {


                        currentHomeStatus = false;

                        String URL = pullupContentArrayList.get(position).getUrl();

                        Fragment f = CommonWebViewFragment.newInstance(URL);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.content_frame, f, "123");
                        fragmentTransaction.commit();


                        drawer.closeDrawer(GravityCompat.END);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }));


        /**
         *   set main pages
         */


        TelephonyManager tManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        device_id = tManager.getDeviceId();

        Log.e("deviceId", device_id);


        if (Constants.loadFromQr) {

            currentHomeStatus = false;


            if (!Constants.scanURL.isEmpty()) {

                String encodedurl = "";

                try {
                    String url = Constants.scanURL;
                    encodedurl = URLEncoder.encode(url, "UTF-8");
                    Log.e("TEST", Encoder_URL_Header + encodedurl);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                Fragment f = ScannerWebFragment.newInstance(Encoder_URL_Header + encodedurl);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, f, "124");
                fragmentTransaction.commit();

            }

        } else {
            setHomePage();

        }

        /**
         *   drawer icon set
         */
        setActionDrawableLeftRightIcon();

        startService(new Intent(this.getApplicationContext(), NotificationService.class));

    }


    private void getPullData() {

        final ApplicationConfig apiReader = AppClient.getApiService();


        Call<ArrayList<HomeContent>> list = apiReader.getHomeData();

        list.enqueue(new Callback<ArrayList<HomeContent>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeContent>> call, Response<ArrayList<HomeContent>> response) {

                if (response.isSuccessful()) {


                    ArrayList<HomeContent> model = response.body();

                    pullupContentArrayList = model.get(0).getPullup();


                    pullMenuAdapter = new PullMenuAdapter(getApplicationContext(), pullupContentArrayList);
                    pull_recycler.setAdapter(pullMenuAdapter);


                    if (model.get(0).getHide_statusbar().equals("true")) {
                        toolbar.setVisibility(View.INVISIBLE);
                    } else {
                        toolbar.setVisibility(View.VISIBLE);
                    }

                    try {

                        Picasso.with(mContext).load(model.get(0).getBackground_image()).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                main_coordinator_layout.setBackground(new BitmapDrawable(bitmap));

                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });


                    } catch (Exception e) {

                    }


                    navProgressBar.setVisibility(View.GONE);


                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeContent>> call, Throwable t) {

            }
        });


    }


    private void setActionDrawableLeftRightIcon() {

        final ApplicationConfig apiReader = AppClient.getApiService();


        Call<ArrayList<HomeContent>> list = apiReader.getHomeData();

        list.enqueue(new Callback<ArrayList<HomeContent>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeContent>> call, Response<ArrayList<HomeContent>> response) {

                if (response.isSuccessful()) {


                    ArrayList<HomeContent> model = response.body();


                    Picasso.with(mContext).load(model.get(0).getMenu_icon()).
                            placeholder(R.drawable.ic_dehaze_black_24dp).error(
                            R.drawable.ic_dehaze_black_24dp).into(menuLeft);

                    Picasso.with(mContext).load(model.get(0).getRight_reveal_icon()).
                            placeholder(R.drawable.ic_open_in_new_black_24dp).error(
                            R.drawable.ic_open_in_new_black_24dp).into(menuRight);


                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeContent>> call, Throwable t) {

            }
        });


    }


    private void fragmentSetForHome(Fragment fragment) {

        FragmentManager fragmentManagerHome = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionHome = fragmentManagerHome.beginTransaction();
        fragmentTransactionHome.replace(R.id.content_frame, fragment);
        fragmentTransactionHome.commit();

    }


    private void setHomePage() {

        currentHomeStatus = true;

        final Handler mDrawerHandler = new Handler();

        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentSetForHome(new HomeFragment());
            }
        }, 240);

    }


    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitDialog();
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (currentHomeStatus) {
                //

                // setHomePage();

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    exitDialog();
                }

            } else if (CommonWebViewFragment.isLoading == false) {

                try {
                    if (CommonWebViewFragment.webView.canGoBack()) {

                        CommonWebViewFragment.webView.goBack();

                    } else if (!CommonWebViewFragment.webView.canGoBack()) {

                        setHomePage();
                    }
                } catch (Exception e) {
                    setHomePage();
                }

            } else if (keyCode == KeyEvent.KEYCODE_HOME) {
                Log.e("BackPress", "backpress");
            } else {
                setHomePage();
            }


            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void exitDialog() {

        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        alertbox.setMessage("Do you want to exit application?");
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {

                finish();
                android.os.Process.killProcess(android.os.Process.myPid());


            }
        });
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {

            // do something when the button is clicked
            public void onClick(DialogInterface arg0, int arg1) {
                setHomePage();
            }
        });
        alertbox.show();

    }


    @Override
    public void reload() {
        new JSONParse().execute();
    }


    public class JSONParse extends AsyncTask<String, String, JSONArray> {

        public static final String SERVER_URL = Layout_API;
        private static final String TAG = "MenuFetcher";
        FrontPageJson[] jsonMenu;
        private ProgressDialog pDialog;
        Pullup p;
        String menuCount;
        List<String> attrs = new ArrayList<String>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

           /* pDialog = new ProgressDialog(MenuBus.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();*/

        }


        @Override
        protected JSONArray doInBackground(String... args) {
            JSONParser jParser = new JSONParser();

            JSONArray json1 = null;
            ;
            // Getting JSON from URL

            /**
             *   add device id in API parameter
             */

            JSONArray json = jParser.getJSONFromUrl(Menu_API + "/device_id-" + device_id, MainActivity.this);

            try {
                Log.v("JSON", json.toString());
                menuCount = json.getJSONObject(0).getString("cart_count");
                attrs.add(json.getJSONObject(0).getString("background_color"));
                attrs.add(json.getJSONObject(0).getString("text_color"));
                attrs.add(json.getJSONObject(0).getString("home_link"));
                attrs.add(json.getJSONObject(0).getString("home_link_icon"));

                attrs.add(json.getJSONObject(0).getString("scan_link"));
                attrs.add(json.getJSONObject(0).getString("scan_link_icon"));

                //  Log.e("sanL", json.getJSONObject(0).getString("scan_link"));

                json1 = json.getJSONArray(1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                String SharedPrefName = "menubus";
                SharedPreferences preferences = MainActivity.this.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
                String notifierId = preferences.getString("cookie", "");

                if (notifierId.equals("")) {
                    Log.d("NOTIFIER (mbjava) 1", "getJSONFromUrl: EMPTY");
                } else
                    Log.d("NOTIFIER (mbjava) 2", "getJSONFromUrl: " + notifierId);
                //Create an HTTP client
                HttpClient client = new DefaultHttpClient();
                HttpGet post = new HttpGet(SERVER_URL);
                post.addHeader("Cookie", notifierId);

                //Perform the request and check the status code
                HttpResponse response = client.execute(post);
                StatusLine statusLine = response.getStatusLine();
                if (statusLine.getStatusCode() == 200) {
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();

                    try {
                        //Read the server response and attempt to parse it as JSON
                        Reader reader = new InputStreamReader(content);

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                        Gson gson = gsonBuilder.create();

                        jsonMenu = gson.fromJson(reader, FrontPageJson[].class);
                        Log.v("JsonMenu", jsonMenu[0].getCartTotal() + "");
                        p = jsonMenu[0].getPullup().get(0);
                        content.close();
                        Log.d(TAG, "doInBackground: " + jsonMenu[0].getBottomBarBg().toString());


                    } catch (Exception ex) {
                        Log.e(TAG, "Failed to parse JSON due to: " + ex);

                    }
                } else {
                    Log.e(TAG, "Server responded with status code: " + statusLine.getStatusCode());

                }
            } catch (Exception ex) {
                Log.e(TAG, "(2) Failed to send HTTP POST request due to: " + ex);

            }
            return json1;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(final JSONArray json) {
            /*pDialog.dismiss();*/
            MenuAdapter adapter = new MenuAdapter(MainActivity.this, json, attrs);
            menuList.setAdapter(adapter);

            tvBadgeCounter.setText(menuCount);


            try {
                //  Picasso.with(MainActivity.this).load(jsonMenu[0].getBanner().toString()).placeholder(R.drawable.ic_logo).into(pLogo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Picasso.with(MainActivity.this)
                        .load(jsonMenu[0].getLogo().toString())
                        .into(toolBarLogo);
            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                menuList.setBackgroundColor(Color.parseColor(attrs.get(0)));
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (toolbar.getVisibility() == View.VISIBLE) {

                try {
                    toolbar.setBackgroundColor(Color.parseColor("#" + jsonMenu[0].getBarColor()));
                } catch (Exception e) {

                    try {
                        toolbar.setBackgroundColor(Color.parseColor(jsonMenu[0].getBarColor()));

                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                    e.printStackTrace();
                }

            }
        }

    }


    @Override
    public void onBackPressed() {
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        }


        if (Constants.loadFromQr) {
            setHomePage();
            Constants.loadFromQr = false;
        }

        if (currentHomeStatus) {
            exitDialog();
        }


    }

}
