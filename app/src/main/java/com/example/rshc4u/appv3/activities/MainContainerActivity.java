package com.example.rshc4u.appv3.activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.example.rshc4u.appv3.api.AppClient;
import com.example.rshc4u.appv3.api.ApplicationConfig;
import com.example.rshc4u.appv3.data_model.home_data.FrontPageJson;
import com.example.rshc4u.appv3.data_model.home_data.HomeContent;
import com.example.rshc4u.appv3.data_model.menu_content.Pullup;
import com.example.rshc4u.appv3.fragment.CommonWebViewFragment;
import com.example.rshc4u.appv3.fragment.HomeFragment;
import com.example.rshc4u.appv3.services.NotificationService;
import com.example.rshc4u.appv3.utils.BadgeDrawerArrowDrawable;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.InternetChecker;
import com.example.rshc4u.appv3.utils.JSONParser;
import com.example.rshc4u.appv3.utils.URLParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainContainerActivity extends AppCompatActivity
        implements URLParams, CommonWebViewFragment.ReloadJsonCallback {

    private DrawerLayout drawer;
    private ImageView logoIcon;
    private ImageView imvPullButton, imgPull;

    private NavigationView navigationViewRight;
    private NavigationView navigationViewLeft;

    private TextView pullTitle, pullDetails;
    private ProgressBar navProgressBar;
    private String pull_url;
    private Context mContext;

    private String badgeCounter = "2";

    private ActionBarDrawerToggle toggle;
    private BadgeDrawerArrowDrawable badgeDrawable;


    private ListView menuList;

    private Toolbar toolbar;
    private boolean currentHomeStatus = false;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container_acitivty);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        logoIcon = (ImageView) findViewById(R.id.menuLeft);
        imvPullButton = (ImageView) findViewById(R.id.menuRight);

        navigationViewLeft = (NavigationView) findViewById(R.id.nav_view_left);
        navigationViewRight = (NavigationView) findViewById(R.id.nav_view_right);
        menuList = (ListView) findViewById(R.id.menuList);

        mContext = getApplicationContext();


        if (InternetChecker.isNetworkAvailable(mContext)) {

            new JSONParse().execute();

        } else {
            Toast.makeText(mContext, "Internet Connect Error !",
                    Toast.LENGTH_LONG).show();
        }

        /*
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

        */


        imvPullButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                getPullData();

                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);

                } else {
                    drawer.openDrawer(GravityCompat.END);


                }
            }
        });


        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {

                imvPullButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_open_in_new_black_24dp));

                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {

                imvPullButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_dehaze_black_24dp));
                super.onDrawerOpened(v);
            }
        };

        badgeDrawable = new BadgeDrawerArrowDrawable(getSupportActionBar().getThemedContext());

        toggle.setDrawerArrowDrawable(badgeDrawable);

        badgeDrawable.setText(badgeCounter);

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        View header = navigationViewRight.getHeaderView(0);
        pullTitle = (TextView) header.findViewById(R.id.pull_title);
        pullDetails = (TextView) header.findViewById(R.id.pull_details);
        imgPull = (ImageView) header.findViewById(R.id.imgPull);
        navProgressBar = (ProgressBar) header.findViewById(R.id.navProgressBar);


        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();


        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {

                    setHomePage();

                } else if (getSupportFragmentManager().findFragmentByTag("123") == null) {

                    currentHomeStatus = false;

                    Fragment f = CommonWebViewFragment.newInstance(preferences.getString("url_" + i, "123"));
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content_frame, f, "123");
                    fragmentTransaction.commit();
                } else {
                    CommonWebViewFragment.webView.loadUrl(preferences.getString("url_" + i, "123"));
                }

                drawer.closeDrawer(Gravity.LEFT);
                new JSONParse().execute();
            }
        });


        /**
         *
         * go pull loading url
         */


        pullDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (pull_url.isEmpty() || pull_url == null) {
                    pull_url = menu_url;
                } else {
                    Constants.DIRECTION_URL = pull_url;
                }

                Fragment f = CommonWebViewFragment.newInstance(Constants.DIRECTION_URL);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, f, "123");
                fragmentTransaction.commit();


                drawer.closeDrawer(GravityCompat.END);

            }
        });


        /**
         *   set main pages
         */

        setHomePage();


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


                    for (int i = 0; i < model.size(); i++) {


                        pullTitle.setText(model.get(i).getPullup().get(0).getTitle());
                        pullDetails.setText(model.get(i).getPullup().get(0).getText());
                        pull_url = model.get(i).getPullup().get(0).getUrl();


                        Picasso.with(mContext).load(model.get(i).getPullup().get(0).getImg()).
                                placeholder(R.drawable.ic_logo).error(
                                R.drawable.ic_logo).into(imgPull);


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


    @Override
    public void onBackPressed() {
       // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            if (currentHomeStatus) {
                exitByBackKey();
            } else {
                exitByBackKey();
            }
        }
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
            exitByBackKey();
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
                }else {
                    exitByBackKey();
                }

            } else if (CommonWebViewFragment.isLoading == false) {

                if (CommonWebViewFragment.webView.canGoBack()) {

                    CommonWebViewFragment.webView.goBack();

                } else if (!CommonWebViewFragment.webView.canGoBack()) {

                    setHomePage();
                }

            }


            //moveTaskToBack(false);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    protected void exitByBackKey() {

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


            JSONArray json = jParser.getJSONFromUrl(Menu_API, MainContainerActivity.this);

            try {
                Log.v("JSON", json.toString());
                menuCount = json.getJSONObject(0).getString("cart_count");
                attrs.add(json.getJSONObject(0).getString("background_color"));
                attrs.add(json.getJSONObject(0).getString("text_color"));
                attrs.add(json.getJSONObject(0).getString("home_link"));
                attrs.add(json.getJSONObject(0).getString("home_link_icon"));


                json1 = json.getJSONArray(1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                String SharedPrefName = "menubus";
                SharedPreferences preferences = MainContainerActivity.this.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE);
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
            MenuAdapter adapter = new MenuAdapter(MainContainerActivity.this, json, attrs);
            menuList.setAdapter(adapter);

            badgeDrawable.setText(menuCount);


/*

            pTitle.setText(p.getTitle().toString());
            tvContent.setText(p.getText().toString());
*/
            // Picasso.with(MainContainerActivity.this).load(jsonMenu[0].getMenuIcon()).into(burger);


            //  Picasso.with(MainContainerActivity.this).load(jsonMenu[0].getRightRevealIcon()).into(pArrow);
            Log.v("PullupContent", p.getText());
            try {
                //  Picasso.with(MainContainerActivity.this).load(jsonMenu[0].getBanner().toString()).placeholder(R.drawable.ic_logo).into(pLogo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Picasso.with(MainContainerActivity.this)
                        .load(jsonMenu[0].getLogo().toString())
                        .into(logoIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }



            /*

            pContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment f = WebViewFragment.newInstance(jsonMenu[0].getPullup().get(0).getUrl());
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, f, "123");
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                }
            });


            */


            try {
                menuList.setBackgroundColor(Color.parseColor(attrs.get(0)));
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                toolbar.setBackgroundColor(Color.parseColor("#" + jsonMenu[0].getBarColor()));
            } catch (Exception e) {
                toolbar.setBackgroundColor(Color.parseColor(jsonMenu[0].getBarColor()));
                e.printStackTrace();
            }



            /*

            cartColor = jsonMenu[0].getCartColor();
            try {
                cartImgUrl = attrs.get(4);
            } catch (Exception e) {
                e.printStackTrace();
            }
            cartTotalS = jsonMenu[0].getCartTotalFormatted();

            MainContainerActivity.cartTotal.setText(cartTotalS);
            Log.v("CartTotal", cartTotalS + "    " + cartImgUrl);
            try {
                MainContainerActivity.cartTotal.setTextColor(Color.parseColor(cartColor));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                cartContainer.setBackgroundColor(Color.parseColor(jsonMenu[0].getCartBackground()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Picasso.with(MainContainerActivity.this).load(cartImgUrl).fit().centerInside().into(MainContainerActivity.cartLogo);
            } catch (Exception e) {
                e.printStackTrace();
            }

            cartContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment f = WebViewFragment.newInstance(jsonMenu[0].getCartUrl());
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.content, f, "123");
                    fragmentTransaction.commit();
                }
            });

            */
        }
    }


}
