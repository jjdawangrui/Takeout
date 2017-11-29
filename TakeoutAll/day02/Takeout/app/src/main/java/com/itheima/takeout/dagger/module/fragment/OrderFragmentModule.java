package com.itheima.takeout.dagger.module.fragment;

import com.itheima.takeout.presenter.fragment.OrderFragmentPresenter;
import com.itheima.takeout.ui.fragment.OrderFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by itheima.
 */
@Module
public class OrderFragmentModule {


    OrderFragment frament;

    public OrderFragmentModule(OrderFragment frament) {
        this.frament = frament;
    }

    @Provides
    OrderFragmentPresenter provideOrderFragmentPresenter(){
        return new OrderFragmentPresenter(frament);
    }
}
