package com.itheima.takeout.dagger.componet.activity;

import com.itheima.takeout.dagger.module.activity.ReceiptAddressActivityModule;
import com.itheima.takeout.ui.activity.ReceiptAddressActivity;

import dagger.Component;

/**
 * Created by ywf on 2017/7/18.
 */
@Component(modules = ReceiptAddressActivityModule.class)
public class ReceiptAddressActivityComponent {
    void in(ReceiptAddressActivity activity);
}
