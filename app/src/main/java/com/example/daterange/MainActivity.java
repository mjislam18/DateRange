package com.example.daterange;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Calendar calendar;
    DatePickerDialog dpd;

    TextView txtStartDate, txtEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartDate = findViewById(R.id.btn_startDate);
        Button btnEndDate = findViewById(R.id.btn_EndDate);

        txtStartDate = findViewById(R.id.txt_StartDate);
        txtEndDate = findViewById(R.id.txt_EndDate);

        btnStartDate.setOnClickListener(this);
        btnEndDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        switch (v.getId()) {
            case R.id.btn_startDate:

                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtStartDate.setText("Start Date: " + dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                dpd.show();
        }

        switch (v.getId()) {
            case R.id.btn_EndDate:

                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtEndDate.setText("End Date: " + dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                dpd.show();
        }
    }

}
