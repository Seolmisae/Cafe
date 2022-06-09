package com.example.cafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Notice extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_notice, container, false);

        final ListView listView = root.findViewById(R.id.notice_list);

        List<String> list = new ArrayList<>();
        list.add("3.6.1 업데이트 공지");
        list.add("'디어쥬' 에서 디저트가 업데이트 됐어요!");

        ArrayAdapter<String> adpater = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adpater);

        return root;

    }
}