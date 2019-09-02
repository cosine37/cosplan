package com.cosine.cosplan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.cosine.cosplan.util.Time;

public class Calendar extends View {

    String[] WEEKDAYS = {"S", "M", "T", "W", "T", "F", "S"};

    Time time;

    int backgroundColor = 0xffff0000;
    int dayColor = 0xffffff00;

    float height;
    float weight;

    Paint dayPaint;

    public Calendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        height = 24;
        weight = 24;
        dayPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(backgroundColor);
        int x,y;
        int nx = 7;
        int ny = 5;
        int height = 50;
        int width = 50;
        String text;
        for (y=0;y<ny;y++){
            for (x=0;x<nx;x++){
                text = Integer.toString(y*nx+x);
                drawDay(canvas, text, width+x*width,height+y*height);
            }
        }

    }

    void drawDay(Canvas canvas, String text, int x, int y){
        dayPaint.setTextSize(20);
        dayPaint.setColor(dayColor);
        canvas.drawText(text,x, y, dayPaint);
    }

}
