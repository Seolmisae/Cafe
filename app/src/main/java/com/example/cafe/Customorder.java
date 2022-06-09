package com.example.cafe;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class Customorder extends Fragment {

    private DatePickerDialog.OnDateSetListener callbackMethod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_customorder, container, false);
        final TextView textView_Date = root.findViewById(R.id.textView_date);

        callbackMethod = (view, year, monthOfYear, dayOfMonth) -> textView_Date.setText(year + "년" + monthOfYear + "월" + dayOfMonth + "일");

        OnClickHandler();
        return root;
    }

    public void OnClickHandler()
    {
        DatePickerDialog dialog = new DatePickerDialog(getContext(), callbackMethod, 2022, 5, 23);

        dialog.show();
    }
}
