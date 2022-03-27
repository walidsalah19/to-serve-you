package com.example.toserveyou.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.toserveyou.R;
import com.example.toserveyou.user_acces.login_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class reports extends Fragment {

    private BottomNavigationView bottomNavigation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_reports, container, false);

        bottom_navigation_method(v);
        return v;
    }
    private void bottom_navigation_method(View v)
    {
        move_fragment(new admin_get_reports());
        bottomNavigation=v.findViewById(R.id.report_bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.report==item.getItemId())
                {
                    move_fragment(new admin_get_reports());
                }
                else if (R.id.new_report==item.getItemId())
                {
                    move_fragment(new admin_get_new_report());
                }
                else if (R.id.reject_report==item.getItemId())
                {
                    move_fragment(new reject_reports());
                }
                return false;
            }
        });

    }
    private void move_fragment(Fragment Fragment)
    {
       getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.admin_report_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}