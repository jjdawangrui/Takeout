package com.itheima.takeout.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.itheima.takeout.ui.observer.OrderObserver;
import com.itheima.takeout.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by itheima.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            // SDK 向 JPush Server 注册所得到的注册 全局唯一的 ID ，可以通过此 ID 向对应的客户端发送消息和通知。
//            String id = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            //保存服务器推送下来的消息的标题。
//            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            //保存服务器推送下来的消息内容
//            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            //保存服务器推送下来的附加字段。这是个 JSON 字符串。
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            //保存服务器推送下来的内容类型。
//            String type = bundle.getString(JPushInterface.EXTRA_CONTENT_TYPE);


            String type="";
            String orderId="";

            // 坐标信息
            String lat="";
            String lng="";

            try {
                JSONObject object=new JSONObject(extras);
                type=object.getString("type");
                orderId=object.getString("orderId");

                lat=object.getString(Constant.LAT);
                lng=object.getString(Constant.LNG);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            HashMap<String, String> data = new HashMap<>();

            if (!TextUtils.isEmpty(type)){
                data.put("type",type);
            }
            if (!TextUtils.isEmpty(orderId)){
                data.put("orderId",orderId);
            }
            if (!TextUtils.isEmpty(lat)){
                data.put(Constant.LAT,lat);
            }
            if (!TextUtils.isEmpty(lng)){
                data.put(Constant.LNG,lng);
            }




            if(data.size()>0){
                OrderObserver.getObserver().changeOrderInfo(data);
            }

        }
    }
}
