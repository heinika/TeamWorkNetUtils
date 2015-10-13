package com.apple.mychartview.module;

/**
 * Created by Administrator on 2015/10/13.
 */
public class HistogramData {
    private int percent;
    private String name;

    public HistogramData(String name, int percent) {
        this.percent = percent;
        this.name = name;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
