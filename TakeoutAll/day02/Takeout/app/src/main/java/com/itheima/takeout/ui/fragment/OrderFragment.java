package com.itheima.takeout.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.takeout.R;
import com.itheima.takeout.dagger.componet.fragment.DaggerOrderFragmentComponent;
import com.itheima.takeout.dagger.module.fragment.OrderFragmentModule;
import com.itheima.takeout.presenter.fragment.OrderFragmentPresenter;
import com.itheima.takeout.ui.adapter.OrderRecyclerViewAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * 订单列表
 */
public class OrderFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.rv_order_list)
    RecyclerView rvOrderList;
    @InjectView(R.id.srl_order)
    SwipeRefreshLayout srlOrder;

    OrderRecyclerViewAdapter adapter;
    @Inject
    OrderFragmentPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerOrderFragmentComponent.builder().orderFragmentModule(new OrderFragmentModule(this)).build().in(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter=new OrderRecyclerViewAdapter();
        rvOrderList.setAdapter(adapter);
        rvOrderList.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));

        srlOrder.setOnRefreshListener(this);

        srlOrder.setRefreshing(true);
        presenter.getOrderInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        presenter.getOrderInfo();
    }

    public void closeRefresh(){
        if(srlOrder.isRefreshing()){
            srlOrder.setRefreshing(false);
        }
    }

    public OrderRecyclerViewAdapter getAdapter() {
        return adapter;
    }
}
