package com.example.rshc4u.appv3.activities;

import android.graphics.Color;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.fragment.HomeFragment;
import com.example.rshc4u.appv3.fragment.Login_fragment;
import com.example.rshc4u.appv3.fragment.MenuFragment;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.InternetChecker;
import com.example.rshc4u.appv3.utils.URLParams;


public class MainActivity extends AppCompatActivity implements URLParams {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private boolean currentState = false;
    NavigationView navigationView;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    String TAG = "webview";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));

        toolbar.setLogo(R.drawable.ic_logo);

        initNavigationDrawer();

        TextView slideshow = (TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.user_shopping_card));

        slideshow.setText("0");
        slideshow.setGravity(Gravity.CENTER_VERTICAL);
        slideshow.setTypeface(null, Typeface.BOLD);


        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);


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

                        currentState = false;

                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {


                            Constants.DIRECTION_URL = login_register_url;
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
                        currentState = false;
                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {


                            Constants.DIRECTION_URL = card_shopping_url;
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

                    case R.id.user_menu:
                        currentState = false;
                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {

                            Constants.DIRECTION_URL = menu_url;

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
                        currentState = false;
                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {


                            Constants.DIRECTION_URL = location_url;
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
                    case R.id.user_review:
                        currentState = false;
                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {


                            Constants.DIRECTION_URL = reviews_url;
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
                    case R.id.user_rewards:
                        currentState = false;
                        if (InternetChecker.isNetworkAvailable(getApplicationContext())) {


                            Constants.DIRECTION_URL = reward_url;
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


                }
                return true;
            }

        });

//        View header = navigationView.getHeaderView(0);
//        TextView tv_email = (TextView) header.findViewById(R.id.tv_email);
//        tv_email.setText("info@tutorialsee.com");


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

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


    private void setHomePage() {
        currentState = true;
        final Handler mDrawerHandler = new Handler();


        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fragmentSetForHome(new HomeFragment());
            }
        }, 240);

    }

    @Override
    public void onBackPressed() {

        if (currentState == true) {
            super.onBackPressed();
        } else {
            setHomePage();
        }
    }
}