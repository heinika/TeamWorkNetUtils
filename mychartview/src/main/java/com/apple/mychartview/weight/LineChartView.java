package com.apple.mychartview.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.apple.mychartview.R;
import com.apple.mychartview.Tools;
import com.apple.mychartview.module.LineChartData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class LineChartView extends View {
    private List<LineChartData> datas;
    private Paint mPaintBg;
    private Paint mPaintLine;
    private Paint mPaintCricle;
    private Paint mPaintWhite;
    private Paint mPaintText;
    private Paint mPaintChartShadow;
    private Path mPath;
    private int width;
    private int height;
    private int chartColor=getResources().getColor(R.color.lightGreen);
    private float margin = Tools.Dp2Px(getContext(), 15);
    private float chartToBottom = Tools.Dp2Px(getContext(), 30);
    private float segmentValue = 4;
    private float segmentWidth = Tools.Dp2Px(getContext(), 60);
    private float segmentHeight = Tools.Dp2Px(getContext(), 40);

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaintBg = new Paint();
        mPaintBg.setColor(getResources().getColor(R.color.gray));
        mPaintBg.setStyle(Paint.Style.FILL);

        mPaintLine = new Paint();
        mPaintLine.setColor(getResources().getColor(R.color.deepGray));
        mPaintLine.setStrokeWidth(Tools.Dp2Px(getContext(), 3));

        mPaintCricle = new Paint();
        mPaintCricle.setColor(chartColor);
        mPaintCricle.setStyle(Paint.Style.STROKE);
        mPaintCricle.setAntiAlias(true);
        mPaintCricle.setStrokeWidth(10);

        mPaintWhite = new Paint();
        mPaintWhite.setColor(getResources().getColor(R.color.white));
        mPaintCricle.setAntiAlias(true);
        mPaintWhite.setStyle(Paint.Style.FILL);

        mPaintText = new Paint();
        mPaintText.setColor(chartColor);
        mPaintText.setAntiAlias(true);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        mPaintText.setTextSize(30);
        mPaintText.setStyle(Paint.Style.FILL);

        mPaintChartShadow = new Paint();
        mPaintChartShadow.setColor(chartColor);
        mPaintChartShadow.setStyle(Paint.Style.FILL);
        mPaintChartShadow.setAlpha(55);

        mPath = new Path();
        initDatas();
    }

    public void setDatas(List<LineChartData> datas) {
        this.datas = datas;
        /**
         * 重新设置宽高和重新绘图
         */
        requestLayout();//onMeasure
        invalidate();//onDraw
    }
    public void setChartColor(int chartColor) {
        this.chartColor = chartColor;
        mPaintCricle.setColor(chartColor);
        mPaintText.setColor(chartColor);
        mPaintChartShadow.setColor(chartColor);
        mPaintChartShadow.setAlpha(55);
        /**
         * 重新设置宽高和重新绘图
         */
        requestLayout();//onMeasure
        invalidate();//onDraw
    }

    public void setSegmentValue(float segmentValue) {
        this.segmentValue = segmentValue;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /**
         * 根据数据的长度来设置宽度
         */
        if (datas == null) {
            width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        } else {
            width = (int) ((datas.size() - 1) * segmentWidth + margin * 2);
        }
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, width, height - chartToBottom, mPaintBg);
        mPath.reset();
        mPath.moveTo(margin,
                height - chartToBottom - datas.get(0).getValue() / segmentValue * segmentHeight);
        //画网格线，num表示要画几行（根据view的高度）
        int num = (int) (height / segmentHeight);
        for (int j = 0; j <= num; j++) {
            canvas.drawLine(margin, height - chartToBottom - j * segmentHeight,
                    width - margin, height - chartToBottom - j * segmentHeight, mPaintLine);
        }
        for (int i = 0; i < datas.size(); i++) {
            canvas.drawLine(margin + i * segmentWidth, 0, margin + i * segmentWidth, height - chartToBottom, mPaintLine);
        }
        //画直线
        for (int i = 1; i < datas.size(); i++) {
            LineChartData oldData = datas.get(i - 1);
            LineChartData data = datas.get(i);
            //画路径，为画出下部的阴影
            mPath.lineTo(margin + i * segmentWidth,
                    height - chartToBottom - data.getValue() / segmentValue * segmentHeight);

            canvas.drawLine(margin + (i - 1) * segmentWidth,
                    height - chartToBottom - oldData.getValue() / segmentValue * segmentHeight, margin + i * segmentWidth,
                    height - chartToBottom - data.getValue() / segmentValue * segmentHeight, mPaintCricle);
        }
        mPath.lineTo(width - margin, height - chartToBottom);
        mPath.lineTo(margin, height - chartToBottom);
        mPath.close();
        canvas.drawPath(mPath, mPaintChartShadow);

        //画圆圈及上面的数字
        for (int i = 0; i < datas.size(); i++) {
            LineChartData data = datas.get(i);
            canvas.drawCircle(margin + i * segmentWidth,
                    height - chartToBottom - data.getValue() / segmentValue * segmentHeight, 25, mPaintWhite);
            canvas.drawCircle(margin + i * segmentWidth, height - chartToBottom - data.getValue() / segmentValue * segmentHeight,
                    15, mPaintCricle);
            canvas.drawText(data.getValue() + "", margin + i * segmentWidth,
                    height - chartToBottom - data.getValue() / segmentValue * segmentHeight - 26, mPaintText);
            canvas.drawText(data.getDay() + "", margin + i * segmentWidth,
                    height - chartToBottom / 2, mPaintText);
        }

    }

    private void initDatas() {
        datas = new ArrayList<>();
        LineChartData data0 = new LineChartData(9, 21, 2);
        LineChartData data1 = new LineChartData(9, 22, 3);
        LineChartData data2 = new LineChartData(9, 23, 4);
        LineChartData data3 = new LineChartData(9, 24, 5);
        LineChartData data4 = new LineChartData(9, 25, 6);
        LineChartData data5 = new LineChartData(9, 26, 4);
        LineChartData data6 = new LineChartData(9, 27, 5);
        LineChartData data7 = new LineChartData(9, 28, 2);
        LineChartData data8 = new LineChartData(9, 29, 2);
        LineChartData data9 = new LineChartData(9, 30, 2);
        LineChartData data10 = new LineChartData(10, 1, 2);
        LineChartData data11 = new LineChartData(10, 2, 2);
        LineChartData data12 = new LineChartData(10, 3, 2);
        datas.add(data0);
        datas.add(data1);
        datas.add(data2);
        datas.add(data3);
        datas.add(data4);
        datas.add(data5);
        datas.add(data6);
        datas.add(data7);
        datas.add(data8);
        datas.add(data9);
        datas.add(data10);
        datas.add(data11);
        datas.add(data12);
    }
}
