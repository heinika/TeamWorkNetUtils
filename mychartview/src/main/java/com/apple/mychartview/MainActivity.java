package com.apple.mychartview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.apple.mychartview.module.HistogramData;
import com.apple.mychartview.module.LineChartData;
import com.apple.mychartview.weight.LineChartView;
import com.apple.mychartview.weight.ScrollHistogramView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    @ViewInject(R.id.scrollhistorgramview)
    private ScrollHistogramView mScrollHistogramView;
    @ViewInject(R.id.button_refresh_scrollhistorgramview)
    private Button mButtonRefreshScrollHistogramView;
    @ViewInject(R.id.LineChartView)
    private LineChartView mLineChartView;
    @ViewInject(R.id.LineChartView_blue)
    private LineChartView mLineChartViewBlue;
    private List<HistogramData> histogramDataList;
    private List<LineChartData> lineChartDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);
        mLineChartViewBlue.setChartColor(getResources().getColor(R.color.lightBlue));
        mButtonRefreshScrollHistogramView.setOnClickListener(this);

    }

    @OnClick({R.id.button_refresh_scrollhistorgramview})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_refresh_scrollhistorgramview:
                histogramDataList = new ArrayList<>();
                initHistogramlineChartDataList();
                mScrollHistogramView.setData(histogramDataList);
                lineChartDataList = new ArrayList<>();
                initLineChartlineChartDataList();
                mLineChartView.setDatas(lineChartDataList);
                break;
            default:
                break;
        }
    }

    private void initLineChartlineChartDataList() {
        LineChartData data0 = new LineChartData(9, 21, 2);
        LineChartData data1 = new LineChartData(9, 22, 10);
        LineChartData data2 = new LineChartData(9, 23, 4);
        LineChartData data3 = new LineChartData(9, 24, 7);
        LineChartData data4 = new LineChartData(9, 25, 6);
        LineChartData data5 = new LineChartData(9, 26, 4);
        LineChartData data6 = new LineChartData(9, 27, 5);
        LineChartData data7 = new LineChartData(9, 28, 2);
        LineChartData data8 = new LineChartData(9, 29, 2);
        LineChartData data9 = new LineChartData(9, 30, 12);
        LineChartData data10 = new LineChartData(10, 1, 8);
        LineChartData data11 = new LineChartData(10, 2, 2);
        LineChartData data12 = new LineChartData(10, 3, 6);
        lineChartDataList.add(data0);
        lineChartDataList.add(data1);
        lineChartDataList.add(data2);
        lineChartDataList.add(data3);
        lineChartDataList.add(data4);
        lineChartDataList.add(data5);
        lineChartDataList.add(data6);
        lineChartDataList.add(data7);
        lineChartDataList.add(data8);
        lineChartDataList.add(data9);
        lineChartDataList.add(data10);
        lineChartDataList.add(data11);
        lineChartDataList.add(data12);
    }


    private void initHistogramlineChartDataList() {
        HistogramData data0 = new HistogramData("福建", 15);
        HistogramData data1 = new HistogramData("莆田", 25);
        HistogramData data2 = new HistogramData("山东", 35);
        HistogramData data3 = new HistogramData("青岛", 45);
        HistogramData data4 = new HistogramData("北京", 55);
        HistogramData data5 = new HistogramData("内蒙古", 65);
        HistogramData data6 = new HistogramData("上海", 75);
        HistogramData data7 = new HistogramData("南京", 85);
        HistogramData data8 = new HistogramData("常州", 95);
        histogramDataList.add(data0);
        histogramDataList.add(data1);
        histogramDataList.add(data2);
        histogramDataList.add(data3);
        histogramDataList.add(data4);
        histogramDataList.add(data5);
        histogramDataList.add(data6);
        histogramDataList.add(data7);
        histogramDataList.add(data8);
        histogramDataList.add(data0);
        histogramDataList.add(data1);
        histogramDataList.add(data2);
        histogramDataList.add(data3);
        histogramDataList.add(data4);
        histogramDataList.add(data5);
        histogramDataList.add(data6);
        histogramDataList.add(data7);
        histogramDataList.add(data8);
    }


}
