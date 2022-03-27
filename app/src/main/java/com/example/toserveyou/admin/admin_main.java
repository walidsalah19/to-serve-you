package com.example.toserveyou.admin;

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

public class admin_main extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigation;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        auth=FirebaseAuth.getInstance();
        move_fragment(new reports());
        toolpar_intialize();
        bottom_navigation_method();
       //chack_login();
    }

    @Override
    protected void onStart() {
        super.onStart();
        chack_login();
    }

    private void toolpar_intialize() {
        toolbar = findViewById(R.id.appbar_main);
        setSupportActionBar(toolbar);
    }
    private void bottom_navigation_method()
    {
       bottomNavigation=findViewById(R.id.bottomNavigationView);
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (R.id.admin_reports==item.getItemId())
                {
                    move_fragment(new reports());
                }
                else if (R.id.add_employee==item.getItemId())
                {
                    move_fragment(new add_employee());
                }
                else if (R.id.search_device==item.getItemId())
                {
                    move_fragment(new find_device());
                }
                else if (R.id.admin_logout==item.getItemId())
                {

                    auth.signOut();
                    startActivity(new Intent(admin_main.this, login_activity.class));
                    finish();
                }
                return false;
            }
        });

    }
    private void move_fragment(Fragment Fragment)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_framelayout,Fragment).addToBackStack(null).commitAllowingStateLoss();
    }
    private void chack_login()
    {
        FirebaseUser user=auth.getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(admin_main.this, login_activity.class));
            finish();
        }
    }
}