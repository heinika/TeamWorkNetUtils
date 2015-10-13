package com.apple.mychartview;

import android.content.Context;

/**
 * Created by Administrator on 2015/10/13.
 */
public class Tools {
    public static float Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return  (dp * scale + 0.5f);
    }

    public static float Px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return  (px / scale + 0.5f);
    }
}
