package com.cosine.cosplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cosine.cosplan.view.Calendar;

public class CalendarActivity extends AppCompatActivity {

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = findViewById(R.id.CalendarWidget);
    }
}
