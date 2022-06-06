package com.example.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.Adapters.CoffeeAdapter;
import com.example.Helpers.CoffeeItem;
import com.example.cafe.R;

public class HomeFragment extends Fragment {

    private ArrayList<CoffeeItem> coffeeItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new CoffeeAdapter(coffeeItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        coffeeItems.add(new CoffeeItem(R.drawable.coffee01, "어도라블96","0","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee02, "오버더문", "1","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee03, "떄론347","2","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee04, "바다페","3","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee05, "카페 사직동","4","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee06, "디어쥬","5","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee07, "청년 마카롱","6","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee08, "카페 오이사","7","0"));
        coffeeItems.add(new CoffeeItem(R.drawable.coffee09, "쁠레노 케이크","8","0"));

        return root;
    }
}