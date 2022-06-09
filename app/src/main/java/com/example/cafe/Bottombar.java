package com.example.cafe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Fragments.HomeFragment;
import com.example.gps.Gps;

public class Bottombar extends AppCompatActivity {

    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_bar);

        bottom();
    }

    public void bottom() {

        Button cakeOpen = findViewById(R.id.map);
        cakeOpen.setOnClickListener(v -> {
         Intent cakeintent = new Intent(getApplicationContext(), com.example.cafe.Customorder.class);
         startActivity(cakeintent);
         });

         Button searchOpen = findViewById(R.id.search);
         searchOpen.setOnClickListener(v -> {
         Intent searchintent = new Intent(getApplicationContext(), com.example.Fragments.HomeFragment.class);
         startActivity(searchintent);
         });

         Button homeOpen = findViewById(R.id.home);
         homeOpen.setOnClickListener(v -> {
         Intent homeintent = new Intent(getApplicationContext(), com.example.cafe.MainActivity.class);
         startActivity(homeintent);
         });
         Button favoriteOpen = findViewById(R.id.favorite);
         favoriteOpen.setOnClickListener(v -> {
         Intent favoriteintent = new Intent(getApplicationContext(), com.example.Fragments.DashboardFragment.class);
         startActivity(favoriteintent);
         });
         }
}


