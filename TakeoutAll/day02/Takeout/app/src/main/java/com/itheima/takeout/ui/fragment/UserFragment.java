package com.itheima.takeout.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.takeout.MyApplication;
import com.itheima.takeout.R;
import com.itheima.takeout.presenter.fragment.UserFragmentPresenter;
import com.itheima.takeout.ui.activity.LoginActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 用户信息展示界面
 * 1、判断：用户是否已经登陆，需要要一个变量存储，通常存储用户的唯一标识，其他信息存储在本地数据库中
 * 2、没有登陆：登陆的入口
 * 3、已经登陆：查询用户名和手机号码
 */
public class UserFragment extends BaseFragment {
    @InjectView(R.id.tv_user_setting)
    ImageView tvUserSetting;
    @InjectView(R.id.iv_user_notice)
    ImageView ivUserNotice;
    @InjectView(R.id.login)
    ImageView login;
    @InjectView(R.id.username)
    TextView username;
    @InjectView(R.id.phone)
    TextView phone;
    @InjectView(R.id.ll_userinfo)
    LinearLayout llUserinfo;
    @Inject
    UserFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(MyApplication.getInstance().USREID!=-1)
        {
            // 已经登陆
            // 查询数据库，修改界面
            presenter.load(MyApplication.getInstance().USREID);
        }else{
            // 未登录
            login.setVisibility(View.VISIBLE);
            llUserinfo.setVisibility(View.INVISIBLE);
        }
    }

    public void update(String name,String phoneNum)
    {
        username.setText(name);
        phone.setText(phoneNum);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_user_setting, R.id.login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_user_setting:
                break;
            case R.id.login:
                Intent intent=new Intent(this.getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
