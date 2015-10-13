package com.apple.teamworknetutils.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apple.teamworknetutils.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/10.
 */
public class InternetConnection {
    /**
     * 单例网络连接
     */
    private static InternetConnection mInternetConnection;
    private InternetConnection(){}
    public static synchronized InternetConnection newInstance(){
        if(mInternetConnection == null){
            mInternetConnection = new InternetConnection();
        }
        return mInternetConnection;
    }

    /**
     * 监听网络连接上的接口
     */
    public interface OnConnectionListener{
        /**网络连接的状态*/
        void isConnection(boolean commection,String type);
        /**网络连接成功*/
        void onSuccessConnection(String response);
        /**网络连接失败*/
        void onFailConnection(String response,int statusCode);
        /**网络连接未开启*/
        void onNullIntent();
    }

    public  void addRequest(String url,final HashMap<String,String> map, final OnConnectionListener onConnectionListener){
        if(!NetWorkUtils.isConnection()){
            onConnectionListener.onNullIntent();
        }else{
            //存在网络连接
            onConnectionListener.isConnection(true,NetWorkUtils.getConnectionType());
        }

        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        onConnectionListener.onSuccessConnection(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse==null){
                    onConnectionListener.onFailConnection("网络连接失败",404);
                }else {
                    onConnectionListener.onFailConnection("网络连接失败", error.networkResponse.statusCode);
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        //创建请求队列并将请求添加到请求队列中
        VolleySingleton.newInstance(BaseApplication.getApplication()).addToRequestQueue(request);
    }
}
