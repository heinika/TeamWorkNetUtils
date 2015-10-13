package com.apple.mychartview.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.apple.mychartview.R;
import com.apple.mychartview.module.HistogramData;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class ScrollHistogramView extends HorizontalScrollView {
    private HistogramView histogramView;
    public ScrollHistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.histogramview,null);
        histogramView = (HistogramView) view.findViewById(R.id.histogramview);
        addView(view);
    }

    public void setData(List<HistogramData> mData) {
        histogramView.setDatas(mData);
    }
}
