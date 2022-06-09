package com.example.cafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.register.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_favorite, R.id.navigation_search, R.id.navigation_order, R.id.navigation_notice)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        this.Drawer();
        //this.topBar();
    }


    public void Drawer() {

        NavigationView navigationView = findViewById(R.id.navigationView);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.mypage:
                    Intent mypageintent = new Intent(this, com.example.cafe.MypageActivity.class);
                    startActivity(mypageintent);
                    break;
                case R.id.review:
                    Intent reviewintent = new Intent(this, com.example.cafe.ReviewActivity.class);
                    startActivity(reviewintent);
                    break;
                case R.id.location:
                    Intent locationintent = new Intent(this, com.example.gps.Gps.class);
                    startActivity(locationintent);
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

    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**public void topBar() {
        Button menuOpen = findViewById(R.id.menu);
        menuOpen.setOnClickListener(v -> {
            DrawerLayout drawer = findViewById(R.id.drawerLayout);
            if (!drawer.isDrawerOpen(Gravity.LEFT)) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        Button loginOpen = findViewById(R.id.login);
        loginOpen.setOnClickListener(v -> {
            Intent loginintent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginintent);
        });

    }**/

    public boolean onCreateOptionsMenu(Menu action_menu) {
        getMenuInflater().inflate(R.menu.action_menu, action_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        if (item.getItemId() == R.id.login_button) {
            Intent loginintent = new Intent(this, LoginActivity.class);
            startActivity(loginintent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
