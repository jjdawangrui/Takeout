package com.itheima.takeout.dagger.componet.fragment;

import com.itheima.takeout.dagger.module.fragment.HomeFragmentModule;
import com.itheima.takeout.dagger.module.fragment.OrderFragmentModule;
import com.itheima.takeout.ui.fragment.HomeFragment;
import com.itheima.takeout.ui.fragment.OrderFragment;

import dagger.Component;

/**
 * Created by itheima.
 */
@Component(modules = OrderFragmentModule.class)
public interface OrderFragmentComponent {
    void in(OrderFragment fragment);
}
