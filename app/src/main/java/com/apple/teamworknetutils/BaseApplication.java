package com.apple.teamworknetutils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2015/10/10.
 */
public class BaseApplication extends Application {
    private static BaseApplication mBaseApplication;

    public static Context getApplication(){
        return mBaseApplication.getApplicationContext();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
    }
}
