package com.itheima.takeout.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by itheima.
 */
public class BaseActivity extends FragmentActivity{
    // TODO 因网络状态不同显示不同界面的处理
    // TODO 因定位状态不同显示不同界面的处理
    // 无法获取定位：没有网络，无GPS信号


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
