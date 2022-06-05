package com.example.cafe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {

    @SuppressLint("RtlHardcoded")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button menuOpen = findViewById(R.id.menu);
        menuOpen.setOnClickListener(v -> {
            DrawerLayout drawer = findViewById(R.id.drawerLayout);
            if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        Button loginOpen = findViewById(R.id.login);
        loginOpen.setOnClickListener(v -> {
            Intent loginintent = new Intent(getApplicationContext(), com.example.register.LoginActivity.class);
            startActivity(loginintent);
        });


        this.Drawer();
        this.bottomButton();

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void Drawer() {
        NavigationView navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.mypage:
                    Intent mypageintent = new Intent(this, com.example.cafe.MypageActivity.class);
                    startActivity(mypageintent);
                    break;
                case R.id.review:
                    Intent reviewintent = new Intent(this, com.example.cafe.MypageActivity.class);
                    startActivity(reviewintent);
                    break;
                case R.id.location:
                    Toast.makeText(getApplicationContext(), "LOCATION", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.setting:
                    Toast.makeText(getApplicationContext(), "SETTING", Toast.LENGTH_SHORT).show();
                    break;

            }
            DrawerLayout drawer = findViewById(R.id.drawerLayout);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        });
    }

    public void bottomButton() {
        Button cakeOpen = findViewById(R.id.map);
        cakeOpen.setOnClickListener(v -> {
            Intent cakeintent = new Intent(getApplicationContext(), com.example.cafe.Customorder.class);
            startActivity(cakeintent);
        });

        Button searchOpen = findViewById(R.id.search);
        searchOpen.setOnClickListener(v -> {
            Intent searchintent = new Intent(getApplicationContext(), com.example.search.SearchableActivity.class);
            startActivity(searchintent);
        });

        Button homeOpen = findViewById(R.id.home);
        homeOpen.setOnClickListener(v -> {
            Intent homeintent = new Intent(getApplicationContext(), com.example.cafe.MainActivity.class);
            startActivity(homeintent);
        });
    }
}
