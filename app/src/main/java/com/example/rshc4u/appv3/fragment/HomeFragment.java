package com.example.rshc4u.appv3.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rshc4u.appv3.R;
import com.example.rshc4u.appv3.activities.ScannerActivity;
import com.example.rshc4u.appv3.utils.Constants;
import com.example.rshc4u.appv3.utils.InternetChecker;
import com.example.rshc4u.appv3.utils.URLParams;

/**
 * Created by rshc4u on 8/13/17.
 */

public class HomeFragment extends Fragment implements URLParams {

    LinearLayout btnGo, btnScan;
    Button btnOrder;


    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_content, container, false);

        mContext = getActivity();

        btnGo = (LinearLayout) view.findViewById(R.id.layoutGo);
        btnScan = (LinearLayout) view.findViewById(R.id.layoutScan);
        btnOrder = (Button) view.findViewById(R.id.btnOrder);


        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(mContext, ScannerActivity.class));

            }
        });


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetChecker.isNetworkAvailable(getActivity())) {

                    Constants.DIRECTION_URL = order_url;

                    //startActivity(new Intent(mContext, WebActivity.class));


                    setWebFragment(new WebFragment());

                } else {
                    Toast.makeText(mContext, "Internet Connect Error !",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (InternetChecker.isNetworkAvailable(getActivity())) {

                    Constants.DIRECTION_URL = go_url;

                    // startActivity(new Intent(mContext, WebActivity.class));
                    setWebFragment(new WebFragment());

                } else {
                    Toast.makeText(mContext, "Internet Connect Error !",
                            Toast.LENGTH_LONG).show();
                }

            }
        });


        return view;
    }


    private void setWebFragment(Fragment fragment) {

        FragmentManager fragmentManagerHome = getFragmentManager();
        FragmentTransaction fragmentTransactionHome = fragmentManagerHome.beginTransaction();
        fragmentTransactionHome.replace(R.id.content_frame, fragment);
        fragmentTransactionHome.commit();

    }

}
