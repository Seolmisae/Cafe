package com.example.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cafe.R;

public class SearchableActivity extends AppCompatActivity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        mTextView = findViewById(R.id.text_view);

        //SearchableInfo 객체를 가져오기 위해 SearchManager 객체를 만듭니다.
        //SearchableInfo는 searchable.xml을 manifest에서 추가한 <meta-data>를 통해 가져옵니다.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = findViewById(R.id.search_view);
        //SearchManager를 통해 SearchableInfo를 가져와 SearchView에 적용합니다.
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            search(query);
        }


    }

    void search(String query) {
        String[] cafeNames = getResources().getStringArray(R.array.cafe_names);
        for (String cafeName : cafeNames) {
            if (cafeName.toLowerCase().contains(query.toLowerCase())) {
                mTextView.setText(cafeName);
                return;
            } else {
                mTextView.setText("검색 결과가 없습니다.");
            }
        }
    }
}