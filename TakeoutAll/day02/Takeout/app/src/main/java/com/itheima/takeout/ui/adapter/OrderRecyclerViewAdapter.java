package com.itheima.takeout.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.takeout.R;
import com.itheima.takeout.model.net.bean.Order;
import com.itheima.takeout.ui.activity.OrderDetailActivity;
import com.itheima.takeout.ui.observer.OrderObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by itheima.
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Observer {

    private static final String TAG = "OrderRecyclerViewAdapter";
    List<Order> orderList;

    public OrderRecyclerViewAdapter() {
        // 必须将观察者添加到集合中
        OrderObserver.getObserver().addObserver(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_order_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ItemViewHoler itemViewHoler = new ItemViewHoler(view);
        return itemViewHoler;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Order order = orderList.get(position);
        ((ItemViewHoler) holder).setInfo(order);
        ((ItemViewHoler) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = order.getSeller().getName();
                String type = order.getType();
                String id = order.getId();

                Bundle bundle = new Bundle();
                bundle.putString("type", type);
                bundle.putString("name", name);
                bundle.putString("orderId", id);

                Intent intent = new Intent(view.getContext(), OrderDetailActivity.class);
                intent.putExtras(bundle);
                view.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (orderList != null && orderList.size() > 0) {
            return orderList.size();
        }
        return 0;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i(TAG, data.toString());


        String orderId = ((HashMap<String, String>) data).get("orderId");
        String type = ((HashMap<String, String>) data).get("type");
        int position = -1;

        // 依据推送的订单标号，查询出对应的Order信息，找到对应的Item

        for (int index = 0; index < orderList.size(); index++) {
            Order order = orderList.get(index);
            if (order.getId().equals(orderId)) {
                position = index;
                order.setType(type);
                break;
            }
        }

        if(position!=-1){
            notifyItemChanged(position);
        }


    }

    class ItemViewHoler extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_order_item_seller_logo)
        ImageView ivOrderItemSellerLogo;
        @InjectView(R.id.tv_order_item_seller_name)
        TextView tvOrderItemSellerName;
        @InjectView(R.id.tv_order_item_type)
        TextView tvOrderItemType;
        @InjectView(R.id.tv_order_item_time)
        TextView tvOrderItemTime;
        @InjectView(R.id.tv_order_item_foods)
        TextView tvOrderItemFoods;
        @InjectView(R.id.tv_order_item_money)
        TextView tvOrderItemMoney;
        @InjectView(R.id.tv_order_item_multi_function)
        TextView tvOrderItemMultiFunction;

        public ItemViewHoler(View itemView) {
            super(itemView);
            // this 是指ItemViewHoler类中有InjectView的控件需要初始化
            // itemView是指从那个容器中进行findViewById的操作
            ButterKnife.inject(this, itemView);
        }

        public void setInfo(Order order) {
            // 商家的名字
            // 订单的状态
            String name = order.getSeller().getName();
            String type = order.getType();
            String typeInfo = getOrderTypeInfo(type);

            tvOrderItemSellerName.setText(name);
            tvOrderItemType.setText(typeInfo);

        }

        private String getOrderTypeInfo(String type) {
            /**
             * 订单状态
             * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单
             */
//            public static final String ORDERTYPE_UNPAYMENT = "10";
//            public static final String ORDERTYPE_SUBMIT = "20";
//            public static final String ORDERTYPE_RECEIVEORDER = "30";
//            public static final String ORDERTYPE_DISTRIBUTION = "40";
//            public static final String ORDERTYPE_SERVED = "50";
//            public static final String ORDERTYPE_CANCELLEDORDER = "60";

            String typeInfo = "";
            switch (type) {
                case OrderObserver.ORDERTYPE_UNPAYMENT:
                    typeInfo = "未支付";
                    break;
                case OrderObserver.ORDERTYPE_SUBMIT:
                    typeInfo = "已提交订单";
                    break;
                case OrderObserver.ORDERTYPE_RECEIVEORDER:
                    typeInfo = "商家接单";
                    break;
                case OrderObserver.ORDERTYPE_DISTRIBUTION:
                    typeInfo = "配送中";
                    break;
                case OrderObserver.ORDERTYPE_SERVED:
                    typeInfo = "已送达";
                    break;
                case OrderObserver.ORDERTYPE_CANCELLEDORDER:
                    typeInfo = "取消的订单";
                    break;
            }
            return typeInfo;
        }
    }
}
