package com.itheima.takeout.presenter.fragment;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itheima.takeout.model.net.bean.ResponseInfo;
import com.itheima.takeout.model.net.bean.Seller;
import com.itheima.takeout.presenter.BasePresenter;
import com.itheima.takeout.ui.fragment.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;

/**
 * Created by itheima.
 * 处理HomeFragment业务操作
 */
public class HomeFragmentPresenter extends BasePresenter {

    HomeFragment fragment;

    public HomeFragmentPresenter(HomeFragment fragment) {
        this.fragment = fragment;
    }

    public void getData() {
        Call<ResponseInfo> homeInfo = responseInfoAPI.getHomeInfo();
        homeInfo.enqueue(new CallbackAdapter());
    }

    @Override
    protected void showError(String message) {
        fragment.showError(message);
    }

    @Override
    protected void parseDestInfo(String data) {
        try {
            JSONObject object = new JSONObject(data);

//            nearbySellerList
//    `       ortherSellerList
            String nearbySellerListInfo = object.getString("nearbySellerList");
            String ortherSellerListInfo = object.getString("ortherSellerList");

            Gson gson = new Gson();
            List<Seller> nearbySellerList = gson.fromJson(nearbySellerListInfo, new TypeToken<List<Seller>>() {
            }.getType());
            List<Seller> ortherSellerList = gson.fromJson(ortherSellerListInfo, new TypeToken<List<Seller>>() {
            }.getType());

            fragment.getAdapter().setNearbySellerList(nearbySellerList);
            fragment.getAdapter().setOrtherSellerList(ortherSellerList);

            fragment.getAdapter().notifyDataSetChanged();
            fragment.closePregress();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
