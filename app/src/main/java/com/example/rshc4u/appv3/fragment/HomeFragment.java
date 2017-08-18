package com.example.rshc4u.appv3.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.activities.ScannerActivity;
import com.example.rshc4u.appv3.api.AppClient;
import com.example.rshc4u.appv3.api.ApplicationConfig;
import com.example.rshc4u.appv3.data_model.home_data.HomeContent;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.InternetChecker;
import com.example.rshc4u.appv3.utils.URLParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rshc4u on 8/13/17.
 */

public class HomeFragment extends Fragment implements URLParams {

    private LinearLayout layout_btnGo, layout_qr_scanner;
    private Button btnOrder;
    private ProgressBar progressBar;

    private Context mContext;
    private String go_button_url, order_button_url;

    private TextView welcomeText, txtScanner, txtGo;
    private ImageView ivScanerIcon, ivGo, ivLogo;
    private CoordinatorLayout main_layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_content, container, false);

        mContext = getActivity();

        initAll(view);

        getData();


        layout_qr_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(mContext, ScannerActivity.class));

            }
        });


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetChecker.isNetworkAvailable(getActivity())) {


                    if (!order_button_url.isEmpty()) {

                        Constants.DIRECTION_URL = order_button_url;

                    } else {
                        Constants.DIRECTION_URL = order_url;
                    }
                    setWebFragment(new WebFragment());

                } else {
                    Toast.makeText(mContext, "Internet Connect Error !",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        layout_btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetChecker.isNetworkAvailable(getActivity())) {


                    if (!go_button_url.isEmpty()) {

                        Constants.DIRECTION_URL = go_button_url;

                    } else {
                        Constants.DIRECTION_URL = go_url;

                    }

                    // startActivity(new Intent(mContext, WebActivity.class));
                    setWebFragment(new WebFragment());
                    // Toast.makeText(mContext, "cds" , Toast.LENGTH_LONG).show();
                    //getData();


                } else {
                    Toast.makeText(mContext, "Internet Connect Error !",
                            Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }


    private void initAll(View view) {

        layout_btnGo = (LinearLayout) view.findViewById(R.id.layoutGo);
        layout_qr_scanner = (LinearLayout) view.findViewById(R.id.layoutScan);
        btnOrder = (Button) view.findViewById(R.id.btnOrder);
        progressBar = (ProgressBar) view.findViewById(R.id.homeProgress);
        main_layout = (CoordinatorLayout) view.findViewById(R.id.layout_home);
        welcomeText = (TextView) view.findViewById(R.id.tvWelcome);
        txtScanner = (TextView) view.findViewById(R.id.txtScan);
        txtGo = (TextView) view.findViewById(R.id.txtGo);
        ivScanerIcon = (ImageView) view.findViewById(R.id.scanIcon);
        ivGo = (ImageView) view.findViewById(R.id.ivBtngo);
        ivLogo = (ImageView) view.findViewById(R.id.iv_logo);


    }

    private void setWebFragment(Fragment fragment) {

        FragmentManager fragmentManagerHome = getFragmentManager();
        FragmentTransaction fragmentTransactionHome = fragmentManagerHome.beginTransaction();
        fragmentTransactionHome.replace(R.id.content_frame, fragment);
        fragmentTransactionHome.commit();

    }

    private void getData() {

        final ApplicationConfig apiReader = AppClient.getApiService();


        Call<ArrayList<HomeContent>> list = apiReader.getHomeData();

        list.enqueue(new Callback<ArrayList<HomeContent>>() {
            @Override
            public void onResponse(Call<ArrayList<HomeContent>> call, Response<ArrayList<HomeContent>> response) {

                if (response.isSuccessful()) {


                    ArrayList<HomeContent> model = response.body();

                    // Toast.makeText(mContext, model.get(0).getPullup().get(0).getText(), Toast.LENGTH_LONG).show();

                    for (int i = 0; i < model.size(); i++) {


                        welcomeText.setText(model.get(i).getWelcome_text());

                        Log.e("test", model.get(i).getGo_button().getUrl());

                        Picasso.with(mContext).load(model.get(i).getLogo()).
                                placeholder(R.drawable.logo_icon).error(
                                R.drawable.logo_icon).into(ivLogo);

                        Constants.cart_count = model.get(i).getCart_total();
                        Constants.cart_url= model.get(i).getCart_url();

                        try {

                            main_layout.setBackgroundColor(Color.parseColor(model.get(i).getBackground_color()));

                        } catch (Exception e) {

                        }

                        btnOrder.setText(model.get(i).getBottom_bar_text());

                        try {
                            btnOrder.setBackgroundColor(Color.parseColor(model.get(i).getBottom_bar_bg()));
                            btnOrder.setTextColor(Color.parseColor(model.get(i).getBottom_bar_color()));

                            welcomeText.setTextColor(Color.parseColor(model.get(i).getText_color()));

                        } catch (Exception e) {

                            btnOrder.setBackgroundColor(Color.parseColor("#" + model.get(i).getBottom_bar_bg()));

                            btnOrder.setTextColor(Color.parseColor("#" + model.get(i).getBottom_bar_color()));

                            welcomeText.setTextColor(Color.parseColor("#" + model.get(i).getText_color()));

                        }

                        order_button_url = model.get(i).getBottom_bar_url();


                        /**
                         *  scan button press data
                         */


                        try {
                            layout_qr_scanner.setBackgroundColor(Color.parseColor(model.get(i).getQr_button().getBackground()));

                            txtScanner.setTextColor(Color.parseColor(model.get(i).getQr_button().getColor()));


                        } catch (Exception e) {

                            layout_qr_scanner.setBackgroundColor(Color.parseColor("#" + model.get(i).getBottom_bar_bg()));


                            txtScanner.setTextColor(Color.parseColor("#" + model.get(i).getQr_button().getColor()));

                        }

                        Picasso.with(mContext).load(model.get(i).getQr_button().getIcon()).
                                placeholder(R.drawable.ic_scan).error(
                                R.drawable.ic_scan).into(ivScanerIcon);

                        txtScanner.setText(model.get(i).getQr_button().getText());


                        /**
                         *  go button press data
                         */

                        go_button_url = model.get(i).getGo_button().getUrl();

                        txtGo.setText(model.get(i).getGo_button().getText());

                        Picasso.with(mContext).load(model.get(i).getGo_button().getIcon()).
                                placeholder(R.drawable.ic_go).error(
                                R.drawable.ic_go).into(ivGo);

                        try {

                            layout_btnGo.setBackgroundColor(Color.parseColor(model.get(i).getGo_button().getBackground()));

                            txtGo.setTextColor(Color.parseColor(model.get(i).getGo_button().getColor()));


                        } catch (Exception e) {

                            layout_btnGo.setBackgroundColor(Color.parseColor("#" + model.get(i).getGo_button().getBackground()));

                            txtGo.setTextColor(Color.parseColor("#" + model.get(i).getGo_button().getColor()));

                        }

                    }

                    progressBar.setVisibility(View.GONE);

                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<HomeContent>> call, Throwable t) {

            }
        });


    }


}
