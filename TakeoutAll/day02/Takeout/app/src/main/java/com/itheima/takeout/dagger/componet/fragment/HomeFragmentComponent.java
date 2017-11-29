package com.itheima.takeout.dagger.componet.fragment;

import com.itheima.takeout.dagger.module.fragment.HomeFragmentModule;
import com.itheima.takeout.ui.fragment.HomeFragment;

import dagger.Component;

/**
 * Created by itheima.
 */
@Component(modules = HomeFragmentModule.class)
public interface HomeFragmentComponent {
    void in(HomeFragment fragment);
}
