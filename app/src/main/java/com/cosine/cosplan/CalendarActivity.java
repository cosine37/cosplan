package com.cosine.cosplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.cosine.cosplan.util.Time;
import com.cosine.cosplan.view.Calendar;

public class CalendarActivity extends AppCompatActivity {

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = findViewById(R.id.CalendarWidget);

        Time time = new Time();
        time.currentTime();
        time.buildCalendarTable();

        calendar.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    float x = event.getX();
                    float y = event.getY();
                    //Toast.makeText(CalendarActivity.this, x + "," + y, Toast.LENGTH_SHORT).show();
                    int c = calendar.click(x,y);
                    if (c == 1){
                        //Toast.makeText(CalendarActivity.this, "Click left", Toast.LENGTH_SHORT).show();
                        calendar.prevMonth();
                        calendar.invalidate();
                    } else if (c == 2){
                        //Toast.makeText(CalendarActivity.this, "Click right", Toast.LENGTH_SHORT).show();
                        calendar.nextMonth();
                        calendar.invalidate();
                    }
                    /*
                    Toast.makeText(CalendarActivity.this, "Touch coordinates : " +
                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()), Toast.LENGTH_SHORT).show();
                    */
                }
                return true;
            }
        });

    }
}
