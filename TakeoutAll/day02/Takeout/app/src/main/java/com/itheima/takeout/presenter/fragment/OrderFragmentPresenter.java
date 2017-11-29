package com.itheima.takeout.presenter.fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.takeout.MyApplication;
import com.itheima.takeout.model.net.bean.Order;
import com.itheima.takeout.model.net.bean.ResponseInfo;
import com.itheima.takeout.presenter.BasePresenter;
import com.itheima.takeout.ui.fragment.OrderFragment;

import java.lang.reflect.TypeVariable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by itheima.
 */
public class OrderFragmentPresenter extends BasePresenter {

    private OrderFragment fragment;

    public OrderFragmentPresenter(OrderFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * 获取用户的订单列表数据
     */
    public void getOrderInfo() {
        Call<ResponseInfo> order = responseInfoAPI.order(1);
        order.enqueue(new CallbackAdapter());
    }


    @Override
    protected void showError(String message) {

    }

    @Override
    protected void parseDestInfo(String data) {
        Gson gson = new Gson();
//        Order order = gson.fromJson(data, Order.class);

        List<Order> orderList = gson.fromJson(data, new TypeToken<List<Order>>() {
        }.getType());

        // 更新界面：adapter
        fragment.getAdapter().setOrderList(orderList);
        fragment.getAdapter().notifyDataSetChanged();
        // 隐藏滚动条
        fragment.closeRefresh();
    }
}
