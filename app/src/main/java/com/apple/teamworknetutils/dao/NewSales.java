package com.apple.teamworknetutils.dao;

import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by ZhongHang on 2015/10/12.
 */
@Table(name="NewSales")
public class NewSales {
    @Id(column = "id")
    String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String time;
    String num;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
