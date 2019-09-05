package com.cosine.cosplan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.cosine.cosplan.util.Time;

import java.util.List;

public class Calendar extends View {

    String[] WEEKDAYS = {"S", "M", "T", "W", "T", "F", "S"};

    int backgroundColor = 0xffffffff;
    int dayColor = 0xffff0000;

    float height;
    float weight;

    Time time;


    public Calendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        height = 24;
        weight = 24;
        time = new Time();
        time.currentTime();
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(backgroundColor);

        int x,y;
        int nx = 7;
        int ny = 6;
        int height = 150;
        int width = 140;
        int marginTop = 250;
        int marginLeft = 50;
        int totalWidth = width*nx;

        drawMonth(canvas, marginLeft + totalWidth/2, marginTop/2);

        String text;
        for (x=0;x<nx;x++){
            drawDay(canvas, WEEKDAYS[x], marginLeft+x*width+width/2, marginTop+height/2);
        }

        List<List<String>> calendarTable = time.buildCalendarTable();

        for (y=1;y<ny;y++){
            for (x=0;x<nx;x++){
                text = calendarTable.get(y-1).get(x);
                drawDay(canvas, text, marginLeft+x*width+width/2, marginTop+height+y*height-height/2);
            }
        }
        drawLines(canvas,height,width,nx,ny,marginTop,marginLeft);

    }

    void drawDay(Canvas canvas, String text, int x, int y){
        int textSize = 50;
        Paint dayPaint = new Paint();
        dayPaint.setTextAlign(Paint.Align.CENTER);
        dayPaint.setTextSize(textSize);
        dayPaint.setColor(dayColor);
        canvas.drawText(text,x, y+textSize/4, dayPaint);
    }

    void drawLines(Canvas canvas, int height, int width, int nx, int ny, int marginTop, int marginLeft){
        Paint paint = new Paint();
        paint.setColor(0xff000000);
        paint.setStrokeWidth(3);
        for (int i=0;i<=ny;i++){
            canvas.drawLine(marginLeft, marginTop+i*height,
                    marginLeft+nx*width, marginTop+i*height, paint);
        }
        for (int i=0;i<=nx;i++){
            canvas.drawLine(marginLeft + i*width, marginTop,
                    marginLeft + i*width, marginTop+ny*height, paint);
        }
    }

    void drawMonth(Canvas canvas, int x, int y){
        int textSize = 80;
        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(textSize);
        paint.setColor(0xff0000ff);
        String s = time.getYear() + "/" + String.format("%02d", time.getMonth());
        canvas.drawText(s, x, y, paint);
        paint.setTextSize(textSize + 20);
        canvas.drawText("<", x-400, y, paint);
        canvas.drawText(">", x+400, y, paint);
    }

    public int click(float x, float y){
        int nx = 7;
        int width = 140;
        int marginTop = 250;
        int marginLeft = 50;
        int totalWidth = width*nx;

        float leftx = marginLeft + totalWidth/2 - 400;
        float lefty = marginTop/2 - 50/4;

        float rightx = marginLeft + totalWidth/2 + 400;
        float righty = marginTop/2 - 50/4;

        System.out.println("left: ("+leftx+","+lefty+")");
        System.out.println("right: ("+rightx+","+righty+")");

        float h = 50;
        float v = 50;

        int ans = 0;
        float dh, dv;

        dh = Math.abs(x-leftx);
        dv = Math.abs(y-lefty);
        if (dh<h && dv<v) ans = 1;

        dh = Math.abs(x-rightx);
        dv = Math.abs(y-righty);
        if (dh<h && dv<v) ans = 2;

        return ans;
    }

    boolean isToday(Time time){
        Time today = new Time();
        today.currentTime();
        if (today.getYear() == time.getYear() && today.getMonth() == time.getMonth() &&
            today.getDay() == time.getDay()) return true;
        return false;
    }

    public void prevMonth(){
        time.prevMonth();
    }

    public void nextMonth(){
        time.nextMonth();
    }

}
