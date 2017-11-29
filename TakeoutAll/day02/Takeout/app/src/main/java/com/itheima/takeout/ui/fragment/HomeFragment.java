package com.itheima.takeout.ui.fragment;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.takeout.R;
import com.itheima.takeout.dagger.componet.fragment.DaggerHomeFragmentComponent;
import com.itheima.takeout.dagger.module.fragment.HomeFragmentModule;
import com.itheima.takeout.presenter.fragment.HomeFragmentPresenter;
import com.itheima.takeout.ui.adapter.HomeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 工作内容：
 * 1、布局
 * 2、头容器的处理
 *  a、需要侵入到状态栏中
 *  b、状态栏为透明
 *  c、随着RecyclerView的滑动，头的透明度会变动
 * 3、RecyclerView数据加载
 *  a、简单数据加载
 *  b、复杂数据加载
 */
public class HomeFragment extends BaseFragment {


    @InjectView(R.id.rv_home)
    RecyclerView rvHome;
    @InjectView(R.id.home_tv_address)
    TextView homeTvAddress;
    @InjectView(R.id.ll_title_search)
    LinearLayout llTitleSearch;
    @InjectView(R.id.ll_title_container)
    LinearLayout llTitleContainer;

    @Inject
    HomeFragmentPresenter presenter;
    private HomeRecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHomeFragmentComponent
                .builder()
                .homeFragmentModule(new HomeFragmentModule(this))
                .build().in(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        testData();
        adapter = new HomeRecyclerViewAdapter();
        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        rvHome.addOnScrollListener(listener);

        presenter.getData();
        showProgress();
    }

    /**
     * 耗时操作开启滚动条
     */
    private void showProgress() {
    }

    /**
     * 关闭提示框
     */
    public void closePregress(){

    }

    private void testData() {
        List<String> nearBySellers=new ArrayList<>();
        List<String> ortherSellers=new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            nearBySellers.add("附近商家"+i);
        }

        for(int i= 9 ;i<100;i++){
            ortherSellers.add("普通商家"+i);
        }


//        rvHome.setAdapter(new HomeRecyclerViewAdapter(nearBySellers,ortherSellers));
    }



    private int sumY=0;
    private float duration=150.0f;//在0-150之间去改变头部的透明度
    private ArgbEvaluator evaluator=new ArgbEvaluator();
    private RecyclerView.OnScrollListener listener=new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

//            System.out.println("recyclerView = [" + recyclerView + "], dx = [" + dx + "], dy = [" + dy + "]");

            sumY+=dy;

            // 滚动的总距离相对0-150之间有一个百分比，头部的透明度也是从初始值变动到不透明，通过距离的百分比，得到透明度对应的值
            // 如果小于0那么透明度为初始值，如果大于150为不透明状态

            int bgColor=0X553190E8;
            if(sumY<0){
                bgColor=0X553190E8;
            }else if(sumY>150){
                bgColor=0XFF3190E8;
            }else{
                bgColor = (int) evaluator.evaluate(sumY / duration, 0X553190E8, 0XFF3190E8);
            }

            llTitleContainer.setBackgroundColor(bgColor);

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void showError(String message) {
        Toast.makeText(HomeFragment.this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public HomeRecyclerViewAdapter getAdapter() {
        return adapter;
    }
}
