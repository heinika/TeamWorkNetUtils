package com.apple.teamworknetutils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/10/10.
 */
public class DbOpenHelper extends SQLiteOpenHelper{
    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name,factory, version);
    }

    public DbOpenHelper(Context context, String name){
        this(context, name,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists user("
                + "id integer primary key,"
                + "name varchar(20),"
                + "password integer(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
