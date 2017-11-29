package com.itheima.takeout.ui.observer;

import java.util.HashMap;
import java.util.Observable;

/**
 * 抽象主题角色的子类
 */
public class OrderObserver extends Observable{

    /* 订单状态
     * 1 未支付 2 已提交订单 3 商家接单  4 配送中,等待送达 5已送达 6 取消的订单*/
    public static final String ORDERTYPE_UNPAYMENT = "10";
    public static final String ORDERTYPE_SUBMIT = "20";
    public static final String ORDERTYPE_RECEIVEORDER = "30";
    public static final String ORDERTYPE_DISTRIBUTION = "40";
    // 骑手状态：接单、取餐、送餐
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_RECEIVE = "43";
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_TAKE_MEAL = "46";
    public static final String ORDERTYPE_DISTRIBUTION_RIDER_GIVE_MEAL = "48";

    public static final String ORDERTYPE_SERVED = "50";
    public static final String ORDERTYPE_CANCELLEDORDER = "60";



    private static OrderObserver observer=new OrderObserver();
    private OrderObserver(){}

    public static OrderObserver getObserver() {
        return observer;
    }

    /**
     * 获取服务器推送数据并将数据发给各个观察者
     * @param data
     */
    public void changeOrderInfo(HashMap<String,String> data)
    {
        setChanged();// 修改是否更新的标识
        notifyObservers(data);// 循环容器中所有观察者，将数据data通过观察者的update方法传递给各个观察者
    }
}
