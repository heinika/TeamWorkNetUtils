package com.apple.teamworknetutils.db;

import com.apple.teamworknetutils.BaseApplication;
import com.lidroid.xutils.DbUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/10/10.
 */
public class TableOperate{
    public List getList(String tableName){
        DbUtils dbUtils = DbUtils.create(BaseApplication.getApplication(),TableConfig.DB_NAME);
        return null;
    }
}
