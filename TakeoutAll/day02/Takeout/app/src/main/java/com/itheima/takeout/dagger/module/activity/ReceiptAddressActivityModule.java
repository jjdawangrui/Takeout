package com.itheima.takeout.dagger.module.activity;

import com.itheima.takeout.presenter.activity.AddressPresenter;
import com.itheima.takeout.presenter.activity.LoginActivityPresenter;
import com.itheima.takeout.ui.activity.ReceiptAddressActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ywf on 2017/7/18.
 */
@Module
public class ReceiptAddressActivityModule {

    private ReceiptAddressActivity activity;

    public ReceiptAddressActivityModule(ReceiptAddressActivity activity) {
        this.activity = activity;
    }

    @Provides
    AddressPresenter provideLoginActivityPresenter(){
        return new ReceiptAddressActivity(activity);
    }
}

