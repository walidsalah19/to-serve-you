package com.example.toserveyou.manager;

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

public class manager_main extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private Toolbar toolbar;
    private FirebaseAuth auth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main);
        auth= FirebaseAuth.getInstance();
        toolpar_intialize();
        bottom_navigation_method();
        firebase_tools();
    }



    private void toolpar_intialize() {
        toolbar = findViewById(R.id.manager_appbar_main);
        setSupportActionBar(toolbar);
    }
    private void firebase_tools()
    {
        auth= FirebaseAuth.getInstance();
    }
    private void bottom_navigation_method()
    {
        move_fragment(new main_reports());
        bottomNavigation=findViewById(R.id.manager_bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.manager_report==item.getItemId())
                {
                    move_fragment(new main_reports());
                }
                else if (R.id.manager_send_report_==item.getItemId())
                {
                    move_fragment(new manager_send_report());
                }
                else if (R.id.manager_logout==item.getItemId())
                {
                    auth=FirebaseAuth.getInstance();
                    auth.signOut();
                    startActivity(new Intent(manager_main.this, login_activity.class));
                    finish();
                }
                return false;
            }
        });

    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.manager_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
}