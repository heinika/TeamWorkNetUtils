package com.apple.teamworknetutils.db;

/**
 * Created by Administrator on 2015/10/10.
 */
public class DbManger {
    //单例
    private static DbManger dbManger;
    private DbManger(){}
    public static synchronized DbManger newInstance(){
        if(dbManger == null){
            dbManger = new DbManger();
        }
        return dbManger;
    }
}
