package com.apple.teamworknetutils.net;

import android.util.Log;

import com.apple.teamworknetutils.BaseApplication;
import com.apple.teamworknetutils.dao.User;
import com.apple.teamworknetutils.tools.ToastUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2015/10/10.
 */
public class ConnectionMethod {
    //单例
    private static ConnectionMethod mConnectionMethod;

    private ConnectionMethod() {
    }

    public static ConnectionMethod newInstance() {
        if (mConnectionMethod == null) {
            mConnectionMethod = new ConnectionMethod();
        }
        return mConnectionMethod;
    }

    //登录
    public void login(String userName, String passWord) {}

    //注册
    public void register(String userName, String passWord) {
        String url = "http://192.168.0.45:8080/ServiceDemo/MyTestServlet";
//              String url = "http://192.168.0.45:8080/ServiceDemo/MyLocation";
        HashMap map = new HashMap();
        final JSONObject obj = new JSONObject();
        try {
            obj.put("type", "register");
            JSONObject data = new JSONObject();
//            data.put("username", "heinikahaha");
//            data.put("password", "1234560000");
            data.put("username", userName);
            data.put("password", passWord);
            obj.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = obj.toString();
        map.put("json", json);
        InternetConnection internetConnection = InternetConnection.newInstance();
        internetConnection.addRequest(url, map, new InternetConnection.OnConnectionListener() {
            @Override
            public void isConnection(boolean commection, String type) {

            }

            @Override
            public void onSuccessConnection(String response) {
                //成功
                ToastUtils.toast("网络连接成功:" + response);
                try {
                    JSONObject object = new JSONObject(response);
                    Gson gson = new Gson();
                    User user = gson.fromJson(object.toString(), User.class);
                    DbUtils.create(BaseApplication.getApplication(), "User").save(user);
                    Log.d("users", "账号" + user.getName() + "密码" + user.getPassword());
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Log.d("请求到的数据", "" + response);
            }

            @Override
            public void onFailConnection(String response, int statusCode) {
                ToastUtils.toast("连接失败");
            }

            @Override
            public void onNullIntent() {
                ToastUtils.toast("无网络连接，请打开");
            }
        });
    }

    //请求报表信息。。。待续
}






