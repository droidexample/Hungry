package com.example.rshc4u.appv3.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.api.AppClient;
import com.example.rshc4u.appv3.api.ApplicationConfig;
import com.example.rshc4u.appv3.data_model.home_data.HomeContent;
import com.example.rshc4u.appv3.fragment.HomeFragment;
import com.example.rshc4u.appv3.fragment.ItemCartFragment;
import com.example.rshc4u.appv3.fragment.LocationFragment;
import com.example.rshc4u.appv3.fragment.Login_fragment;
import com.example.rshc4u.appv3.fragment.MenuFragment;
import com.example.rshc4u.appv3.fragment.ReviewFragment;
import com.example.rshc4u.appv3.fragment.WebFragment;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.URLParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainContainerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, URLParams {

    private DrawerLayout drawer;
    private ImageView menuLeft;
    private ImageView menuRight, imgPull;

    private NavigationView navigationViewRight;
    private NavigationView navigationViewLeft;

    private TextView pullTitle, pullDetails;
    private ProgressBar navProgressBar;
    private String pull_url;
    private Context mContext;
    private boolean currentStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container_acitivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuLeft = (ImageView) findViewById(R.id.menuLeft);
        menuRight = (ImageView) findViewById(R.id.menuRight);

        navigationViewLeft = (NavigationView) findViewById(R.id.nav_view_left);
        navigationViewRight = (NavigationView) findViewById(R.id.nav_view_right);

        mContext = getApplicationContext();
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

        /**
         *   set main logo
         */


        getMainLogo();


        menuRight.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                getData();

                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);

                } else {
                    drawer.openDrawer(GravityCompat.END);


                }
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {

                menuRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_open_in_new_black_24dp));

                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {

                menuRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_dehaze_black_24dp));
                super.onDrawerOpened(v);
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationViewLeft.setNavigationItemSelectedListener(this);
        navigationViewRight.setNavigationItemSelectedListener(this);


        View header = navigationViewRight.getHeaderView(0);
        pullTitle = (TextView) header.findViewById(R.id.pull_title);
        pullDetails = (TextView) header.findViewById(R.id.pull_details);
        imgPull = (ImageView) header.findViewById(R.id.imgPull);
        navProgressBar = (ProgressBar) header.findViewById(R.id.navProgressBar);


        pullDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentStatus = false;

                if (pull_url.isEmpty()) {
                    pull_url = menu_url;
                } else {
                    Constants.DIRECTION_URL = pull_url;
                }
                fragmentSetForHome(new WebFragment());

                drawer.closeDrawer(GravityCompat.END);

            }
        });


        setHomePage();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else if (currentStatus) {

            super.onBackPressed();

        } else {
            setHomePage();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.user_home) {
            currentStatus = false;
            fragmentSetForHome(new HomeFragment());


        } else if (id == R.id.user_login_register) {
            currentStatus = false;
            Constants.DIRECTION_URL = login_register_url;
            fragmentSetForHome(new Login_fragment());


        } else if (id == R.id.user_shopping_card) {
            currentStatus = false;
            Constants.DIRECTION_URL = Constants.cart_url;
            fragmentSetForHome(new ItemCartFragment());


        } else if (id == R.id.user_menu) {
            Constants.DIRECTION_URL = menu_url;
            fragmentSetForHome(new MenuFragment());


        } else if (id == R.id.location) {
            currentStatus = false;
            Constants.DIRECTION_URL = location_url;
            fragmentSetForHome(new LocationFragment());


        } else if (id == R.id.user_rewards) {
            currentStatus = false;
            Constants.DIRECTION_URL = reward_url;
            fragmentSetForHome(new ReviewFragment());


        } else if (id == R.id.user_review) {
            currentStatus = false;

            Constants.DIRECTION_URL = reviews_url;

            fragmentSetForHome(new ReviewFragment());


        }


        drawer.closeDrawer(GravityCompat.START);
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }


    private void fragmentSetForHome(Fragment fragment) {

        FragmentManager fragmentManagerHome = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionHome = fragmentManagerHome.beginTransaction();
        fragmentTransactionHome.replace(R.id.content_frame, fragment);
        fragmentTransactionHome.commit();

    }


    private void setHomePage() {
        currentStatus = true;
        final Handler mDrawerHandler = new Handler();


        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentSetForHome(new HomeFragment());
            }
        }, 240);

    }


    private void getMainLogo() {

        final ApplicationConfig apiReader = AppClient.getApiService();

        Call<ArrayList<HomeContent>> list = apiReader.getHomeData();

        list.enqueue(new Callback<ArrayList<HomeContent>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeContent>> call, Response<ArrayList<HomeContent>> response) {

                if (response.isSuccessful()) {

                    ArrayList<HomeContent> model = response.body();

                    for (int i = 0; i < model.size(); i++) {

                        Picasso.with(mContext).load(model.get(i).getLogo()).
                                placeholder(R.drawable.logo_icon).error(
                                R.drawable.logo_icon).into(menuLeft);

                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeContent>> call, Throwable t) {

            }
        });

    }


    private void getData() {

        final ApplicationConfig apiReader = AppClient.getApiService();


        Call<ArrayList<HomeContent>> list = apiReader.getHomeData();

        list.enqueue(new Callback<ArrayList<HomeContent>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeContent>> call, Response<ArrayList<HomeContent>> response) {

                if (response.isSuccessful()) {


                    ArrayList<HomeContent> model = response.body();


                    for (int i = 0; i < model.size(); i++) {


                        Picasso.with(mContext).load(model.get(i).getLogo()).
                                placeholder(R.drawable.logo_icon).error(
                                R.drawable.logo_icon).into(menuLeft);


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


}
