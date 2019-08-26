package com.cosine.cosplan.record;

import com.cosine.cosplan.util.Time;

public class CalendarEvent {

    boolean isWholeDay;
    Time time;
    String content;

    public boolean isWholeDay() {
        return isWholeDay;
    }

    public void setWholeDay(boolean wholeDay) {
        isWholeDay = wholeDay;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CalendarEvent(){

    }

}
