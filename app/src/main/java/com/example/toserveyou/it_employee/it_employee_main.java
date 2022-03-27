package com.example.toserveyou.it_employee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.toserveyou.R;
import com.example.toserveyou.user_acces.login_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class it_employee_main extends AppCompatActivity {


    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it_employee_main);
        toolpar_intialize();
        bottom_navigation_method();

    }

    private void toolpar_intialize() {
        toolbar = findViewById(R.id.it_appbar_main);
        setSupportActionBar(toolbar);
    }
    private void bottom_navigation_method()
    {
        move_fragment(new it_employee_report());
        bottomNavigation=findViewById(R.id.it_bottomNavigationView);
        bottomNavigation.inflateMenu(R.menu.it_employee);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.it_employee_reports==item.getItemId())
                {
                    move_fragment(new it_employee_report());
                }
                else if (R.id.it_device_managment==item.getItemId())
                {
                    move_fragment(new devices_show());
                }
                else if (R.id.it_logout==item.getItemId())
                {
                    auth=FirebaseAuth.getInstance();
                    auth.signOut();
                    startActivity(new Intent(it_employee_main.this, login_activity.class));
                    it_employee_main.this.finish();
                }
                return false;
            }
        });

    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.it_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}