package com.itheima.takeout;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by itheima.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    // 用户是否登陆的标识，如果为-1表示没有登陆
    public int USREID = -1;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        JPushInterface.init(this);
    }
}
