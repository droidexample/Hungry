package com.example.rshc4u.appv3.activities;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.fragment.HomeFragment;
import com.example.rshc4u.appv3.fragment.ItemCartFragment;
import com.example.rshc4u.appv3.fragment.LocationFragment;
import com.example.rshc4u.appv3.fragment.Login_fragment;
import com.example.rshc4u.appv3.fragment.MenuFragment;
import com.example.rshc4u.appv3.fragment.ReviewFragment;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.URLParams;

public class MainContainerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, URLParams {

    DrawerLayout drawer;
    ImageView menuLeft;
    ImageView menuRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container_acitivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        menuLeft = (ImageView) findViewById(R.id.menuLeft);
        menuRight = (ImageView) findViewById(R.id.menuRight);

        menuLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                    //menuRight.setImageDrawable(getResources().getDrawable(R.drawable.ic_go));
                } else {
                    drawer.openDrawer(GravityCompat.START);
                    //menuRight.setImageDrawable(getResources().getDrawable(R.drawable.icd_menu));
                }
            }
        });

        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                    //menuLeft.setImageDrawable(getResources().getDrawable(R.drawable.icd_menu));
                } else {
                    drawer.openDrawer(GravityCompat.END);


                    //  menuLeft.setImageDrawable(getResources().getDrawable(R.drawable.ic_go));
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

        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view_left);
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view_right);

        navigationView1.setNavigationItemSelectedListener(this);
        navigationView2.setNavigationItemSelectedListener(this);


        View header = navigationView2.getHeaderView(0);
        TextView pull_title = (TextView) header.findViewById(R.id.pull_title);
        TextView pull_details = (TextView) header.findViewById(R.id.pull_details);


        setHomePage();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer.isDrawerOpen(GravityCompat.END)) {
            drawer.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        String text = "";
        if (id == R.id.user_home) {

            fragmentSetForHome(new HomeFragment());


        } else if (id == R.id.user_login_register) {

            Constants.DIRECTION_URL = login_register_url;
            fragmentSetForHome(new Login_fragment());


        } else if (id == R.id.user_shopping_card) {

            Constants.DIRECTION_URL = Constants.cart_url;
            fragmentSetForHome(new ItemCartFragment());


        } else if (id == R.id.user_menu) {
            Constants.DIRECTION_URL = menu_url;
            fragmentSetForHome(new MenuFragment());


        } else if (id == R.id.location) {

            Constants.DIRECTION_URL = location_url;
            fragmentSetForHome(new LocationFragment());


        } else if (id == R.id.user_rewards) {

            Constants.DIRECTION_URL = reward_url;
            fragmentSetForHome(new ReviewFragment());


        } else if (id == R.id.user_review) {

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

        final Handler mDrawerHandler = new Handler();


        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentSetForHome(new HomeFragment());
            }
        }, 240);

    }
}
