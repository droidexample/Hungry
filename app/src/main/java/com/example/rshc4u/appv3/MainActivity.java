package com.example.rshc4u.appv3;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rshc4u.appv3.fragment.HomeFragment;
import com.example.rshc4u.appv3.fragment.Login_fragment;
import com.example.rshc4u.appv3.fragment.MenuFragment;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.InternetChecker;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    public static int lp = 0;

    NavigationView navigationView;


    String TAG = "webview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        toolbar.setLogo(R.drawable.ic_logo);

        initNavigationDrawer();

        TextView slideshow = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.user_shopping_card));

        slideshow.setText("0");
        slideshow.setGravity(Gravity.CENTER_VERTICAL);
        slideshow.setTypeface(null, Typeface.BOLD);


        /**
         *  set home fragment
         */
        setHomePage();

    }

    public void initNavigationDrawer() {

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                //  menuItem.setChecked(true);
                final Handler mDrawerHandler = new Handler();


                switch (id) {
                    case R.id.user_home:

                        setHomePage();


                        mDrawerHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fragmentSetForHome(new HomeFragment());
                            }
                        }, 240);


                        drawerLayout.closeDrawers();
                        //setSupportActionBar(toolbar);
                        break;
                    case R.id.user_login_register:


                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {


                            Constants.url = "https://rrordering.247chow.com/demo/u-login/view-mobile/app_use-true/";
                            mDrawerHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    fragmentSetForHome(new Login_fragment());
                                }
                            }, 240);

                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Connect Error !",
                                    Toast.LENGTH_LONG).show();
                        }


                        drawerLayout.closeDrawers();
                        break;
                    case R.id.user_shopping_card:

                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {

                            Constants.url = "https://rrordering.247chow.com/demo/u-cart/view-mobile/app_use-true/";

                            mDrawerHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    fragmentSetForHome(new MenuFragment());
                                }
                            }, 240);



                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Connect Error !",
                                    Toast.LENGTH_LONG).show();
                        }

                        drawerLayout.closeDrawers();
                        break;
                    case R.id.location:

                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {

                            Constants.url = "https://rrordering.247chow.com/demo/u-locations/view-mobile/app_use-true/";
                            startActivity(new Intent(MainActivity.this, WebActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Connect Error !",
                                    Toast.LENGTH_LONG).show();
                        }

                        drawerLayout.closeDrawers();
                        break;
                    case R.id.user_review:

                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {

                            Constants.url = "https://rrordering.247chow.com/demo/u-reviews/view-mobile/app_use-true/";
                            startActivity(new Intent(MainActivity.this, WebActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Connect Error !",
                                    Toast.LENGTH_LONG).show();
                        }

                        drawerLayout.closeDrawers();
                        break;
                    case R.id.user_rewards:

                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {

                            Constants.url = "https://rrordering.247chow.com/demo/u-rewards/view-mobile/";
                            startActivity(new Intent(MainActivity.this, WebActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Connect Error !",
                                    Toast.LENGTH_LONG).show();
                        }


                        drawerLayout.closeDrawers();
                        break;
                    case R.id.user_menu:

                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {

                            Constants.url = "https://rrordering.247chow.com/demo/u-menu/view-mobile/app_use-true/";


                        } else {
                            Toast.makeText(getApplicationContext(), "Internet Connect Error !",
                                    Toast.LENGTH_LONG).show();
                        }


                        drawerLayout.closeDrawers();
                        break;


                }
                return true;
            }

        });

//        View header = navigationView.getHeaderView(0);
//        TextView tv_email = (TextView) header.findViewById(R.id.tv_email);
//        tv_email.setText("info@tutorialsee.com");


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View v) {
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void fragmentSetForHome(android.support.v4.app.Fragment fragment) {

        FragmentManager fragmentManagerHome = getSupportFragmentManager();
        FragmentTransaction fragmentTransactionHome = fragmentManagerHome.beginTransaction();
        fragmentTransactionHome.replace(R.id.content_frame, fragment);
        fragmentTransactionHome.commit();

    }


    private void addMenuItemInNavMenuDrawer() {
        NavigationView navView = (NavigationView) findViewById(R.id.navigation_view);

        Menu menu = navView.getMenu();
        Menu submenu = menu.addSubMenu("New Super SubMenu");

        submenu.add("Super Item1");


        submenu.add("Super Item2");
        submenu.add("Super Item3");

        navView.invalidate();
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