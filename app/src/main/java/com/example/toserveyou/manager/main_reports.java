package com.example.toserveyou.manager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.toserveyou.R;
import com.example.toserveyou.admin.admin_get_new_report;
import com.example.toserveyou.admin.admin_get_reports;
import com.example.toserveyou.admin.reject_reports;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class main_reports extends Fragment {
    private BottomNavigationView bottomNavigation;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_main_reports, container, false);
        bottom_navigation_method(v);
        return v;
    }
    private void bottom_navigation_method(View v)
    {
        move_fragment(new manager_reports());
        bottomNavigation=v.findViewById(R.id.manager_report_bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.report==item.getItemId())
                {
                    move_fragment(new manager_reports());
                }
                else if (R.id.new_report==item.getItemId())
                {
                    move_fragment(new manager_new_reports());
                }
                else if (R.id.reject_report==item.getItemId())
                {
                    move_fragment(new manager_get_reject_report());
                }
                return false;
            }
        });

    }
    private void move_fragment(Fragment Fragment)
    {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.manager_report_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}