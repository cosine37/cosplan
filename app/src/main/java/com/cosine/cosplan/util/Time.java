package com.cosine.cosplan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Time {

    int year;
    int month;
    int day;
    int hour;
    int minute;

    Date date;

    public Time(){

    }

    public void currentTime(){
        date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        date = calendar.getTime();
        year = date.getYear() + 1900;
        month = date.getMonth() + 1;
        day = date.getDate();
    }

    public boolean validDate(int year, int month, int day){
        if (month>12 || month<1) return false;
        if (year<1) return false;
        if (day<1) return false;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            if (day>31) return false;
        } else if (month == 4 || month == 6 || month == 9 || month == 11){
            if (day>30) return false;
        } else if (month == 2){
            if (year % 4 == 0){
                if (year % 100 == 0){
                    if (year % 400 == 0){
                        if (day>29) return false;
                    } else {
                        if (day>28) return false;
                    }
                } else {
                    if (day>29) return false;
                }
            }
        }
        return true;
    }

    public List<List<String>> buildCalendarTable(){
        List<List<String>> calendarTable = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = year + "-" + month + "-" + 1;
        System.out.println(s);
        Date date;
        try {
            date = sdf.parse(s);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> line;
        int nx = 7;
        int ny = 5;
        int i,j;
        for (i=0;i<ny;i++){
            line = new ArrayList<>();
            for (j=0;j<nx; j++){
                line.add("");
            }
            calendarTable.add(line);
        }
        int numDays = getNumDays(year,month);
        int x = calendar.get(Calendar.DAY_OF_WEEK)-1;
        System.out.println(x);
        int y = 0;
        for (i=1;i<=numDays;i++){
            calendarTable.get(y).set(x, Integer.toString(i));
            x = x+1;
            if (x == 7){
                x = 0;
                y = (y+1)%5;
            }
        }
        return calendarTable;
    }

    public int getNumDays(int year, int month){
        int ans = 0;
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
            month == 8 || month == 10 || month == 12){
            ans = 31;
        } else if (month == 4 || month == 6 || month == 9 || month == 11){
            ans = 30;
        } else if (month == 2){
            if (year % 4 == 0){
                if (year % 100 == 0){
                    if (year % 400 == 0){
                        ans = 29;
                    } else {
                        ans = 28;
                    }
                } else {
                    ans = 29;
                }
            } else {
                ans = 28;
            }
        }
        return ans;
    }

    public boolean validMinute(int hour, int minute){
        if (hour>23 || hour<0) return false;
        if (minute>59 || minute<0) return false;
        return true;
    }

    public void prevMonth(){
        month = month - 1;
        if (month == 0){
            month = 12;
            year = year - 1;
        }
    }

    public void nextMonth(){
        month = month + 1;
        if (month == 13){
            month = 1;
            year = year + 1;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
