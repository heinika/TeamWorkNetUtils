package com.apple.mychartview.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.apple.mychartview.Tools;
import com.apple.mychartview.module.HistogramData;

import java.util.ArrayList;
import java.util.List;

/**
 * 柱形图
 */
public class HistogramView extends View {
    private int width;
    private int height;
    private Paint mPaintBackground;
    private Paint mPaintText;
    private Paint mPaintBar;
    private Paint mPaintBarBackground;
    private float barWidth = Tools.Dp2Px(getContext(),10);
    private float barBgToTop=Tools.Dp2Px(getContext(),30);
    private float barBgToBottom=Tools.Dp2Px(getContext(),40);
    private float barSpace=Tools.Dp2Px(getContext(),20);
    private List<HistogramData> datas;

    public void setDatas(List<HistogramData> datas) {
        this.datas = datas;
        /**
         * 重新设置宽高和重新绘图
         */
        requestLayout();//onMeasure
        invalidate();//onDraw
    }

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaintBackground = new Paint();
        mPaintText = new Paint();
        mPaintBackground = new Paint();
        mPaintBar = new Paint();
        mPaintBarBackground = new Paint();

        mPaintBackground.setColor(bgColor);
        mPaintBackground.setStyle(Paint.Style.FILL);

        mPaintBarBackground.setColor(histogramBgColor);
        mPaintBarBackground.setStyle(Paint.Style.FILL);

        mPaintBar.setColor(histogramColor);
        mPaintBar.setStyle(Paint.Style.FILL);

        mPaintText.setColor(textColor);
        mPaintText.setTextSize(Tools.Dp2Px(getContext(), 9));
        mPaintText.setTextAlign(Paint.Align.CENTER);

        datas = new ArrayList<HistogramData>();
        initDatas();
    }

    private void initDatas() {
        HistogramData data0 = new HistogramData("黑泥卡",47);
        HistogramData data1 = new HistogramData("黑泥卡",23);
        HistogramData data2 = new HistogramData("卡卡",67);
        HistogramData data3 = new HistogramData("飞利浦",30);
        HistogramData data4 = new HistogramData("黑泥卡",55);
        datas.add(data0);
        datas.add(data1);
        datas.add(data3);
        datas.add(data4);
        datas.add(data2);
        datas.add(data3);
        datas.add(data0);
        datas.add(data1);
        datas.add(data3);
        datas.add(data4);
        datas.add(data4);
        datas.add(data3);

    }


    private int bgColor = Color.argb(255, 249, 249, 249);
    private int histogramBgColor = Color.argb(255,238, 238, 238);
    private int histogramColor = Color.argb(255,240,141, 77);
    private int textColor = Color.argb(255,168, 168, 168);

    /**
     * Color.argb(255,250, 250, 250),
     Color.argb(255,238, 238, 238),
     Color.argb(255,240,141, 77),
     Color.argb(255,168, 168, 168)
     * @param bgColor
     * @param histogramBgColor
     * @param histogramColor
     * @param textColor
     */
    public void setPaintsColor(int bgColor,int histogramBgColor,int histogramColor,int textColor){

        mPaintBackground.setColor(bgColor);
        mPaintBarBackground.setColor(histogramBgColor);
        mPaintBar.setColor(histogramColor);
        mPaintText.setColor(textColor);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if(datas==null){
            width=   getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }else {
            width = (int) (datas.size()  * (barSpace + barWidth)+barWidth);
        }
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,barBgToTop,width,height-barBgToBottom,mPaintBackground);
        for (int i=0;i<datas.size();i++){
            HistogramData data = datas.get(i);
            canvas.drawText(data.getPercent()+"%",((i+1)*barSpace+i*barWidth+barWidth/2),barBgToTop/1.5f,mPaintText);
            canvas.drawRect((i + 1) * barSpace + i * barWidth, barBgToTop, (i + 1) * (barSpace + barWidth), height - barBgToBottom, mPaintBarBackground);
            canvas.drawRect((i + 1) * barSpace + i * barWidth, barBgToTop + (height - barBgToTop - barBgToBottom) * (1 - data.getPercent() / 100.0f), (i + 1) * (barSpace + barWidth), height - barBgToBottom, mPaintBar);
            canvas.save();
            canvas.rotate(-45,(i+1)*barSpace+i*barWidth,height-barBgToBottom);
            canvas.drawText(data.getName(),(i+1)*barSpace+i*barWidth-barWidth/2,height-barBgToBottom/2,mPaintText);
            canvas.restore();
        }
    }
}

