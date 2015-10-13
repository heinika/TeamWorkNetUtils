package com.apple.mychartview.module;

/**
 * Created by Administrator on 2015/10/13.
 */
public class LineChartData {
    private int month;
    private int day;
    private float value;

    public LineChartData(int month, int day, float value) {
        this.month = month;
        this.day = day;
        this.value = value;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
