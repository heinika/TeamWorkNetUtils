package com.apple.teamworknetutils.tools;

import android.widget.Toast;

import com.apple.teamworknetutils.BaseApplication;

/**
 * Created by Administrator on 2015/10/12.
 */
public class ToastUtils {
    public static void toast(String text){
        Toast.makeText(BaseApplication.getApplication(),text, Toast.LENGTH_SHORT).show();
    }
}
