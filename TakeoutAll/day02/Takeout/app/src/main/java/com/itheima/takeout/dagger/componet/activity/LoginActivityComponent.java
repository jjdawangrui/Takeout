package com.itheima.takeout.dagger.componet.activity;

import com.itheima.takeout.dagger.module.activity.LoginActivityModule;
import com.itheima.takeout.ui.activity.LoginActivity;

import dagger.Component;

/**
 * Created by itheima.
 */
@Component(modules = LoginActivityModule.class)
public interface LoginActivityComponent {
    void in(LoginActivity activity);
}
