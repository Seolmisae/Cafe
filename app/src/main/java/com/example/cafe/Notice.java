package com.example.cafe;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Notice extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ListView listView = findViewById(R.id.notice_list);

        List<String> list = new ArrayList<>();
        list.add("3.6.1 업데이트 공지");
        list.add("'디어쥬' 에서 디저트가 업데이트 됐어요!");

        ArrayAdapter<String> adpater = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adpater);

    }
}