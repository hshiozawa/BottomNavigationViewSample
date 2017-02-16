package com.hjm.bottomnavigationviewsample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hjm.bottomnavigationviewsample.bottomnav.MyBottomNavigationView;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = (TextView) view.findViewById(R.id.text);

        MyBottomNavigationView navView = (MyBottomNavigationView) view.findViewById(R.id.bottomNav);
        navView.setOnNavigationItemSelectedListener(new MyBottomNavigationView.MyOnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                int message;

                if (itemId == R.id.camera) {
                    message = R.string.camera;
                } else if (itemId == R.id.map) {
                    message = R.string.map;
                } else if (itemId == R.id.mail) {
                    message = R.string.mail;
                } else if (itemId == R.id.info) {
                    message = R.string.info;
                } else {
                    message = R.string.error;
                }

                textView.setText(message);
                return true;
            }
        });

        return view;
    }

}

