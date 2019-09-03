package com.cosine.cosplan.util;

import java.util.Calendar;
import java.util.Date;

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
        month = date.getMonth();
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

    public boolean validMinute(int hour, int minute){
        if (hour>23 || hour<0) return false;
        if (minute>59 || minute<0) return false;
        return true;
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
