package com.itheima.takeout.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.itheima.takeout.R;
import com.itheima.takeout.model.net.bean.Seller;
import com.itheima.takeout.ui.activity.BusinessActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by itheima.
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 位置信息

    // 1、多少种Item类型（3种）
    // 2、position对应数据展示
    //    position：0                       复杂的item
    //    position：1到附近商家集合的size   附近商家
    //    position: size+1                  第一个分割线（附近商家和普通商家）
    //    假设其他商家每隔20添加一个分割线
    //    position: size+2                  第一组普通商家开始的位置
    //    position: size+2+20               第二个分割线位置
    //    position: size+2+20+1             第一组普通商家开始的位置
    //    position: size+2+20+1+20          第三个分割线


    private static final int TYPE_TITLE = 0;
    private static final int TYPE_DIVISION = 1;
    private static final int TYPE_SELLER = 2;

    private List<Seller> nearBySellers;
    private List<Seller> ortherSellers;

    public void setNearbySellerList(List<Seller> nearbySellerList) {
        this.nearBySellers = nearbySellerList;
    }

    public void setOrtherSellerList(List<Seller> ortherSellerList) {
        this.ortherSellers = ortherSellerList;
    }

    public HomeRecyclerViewAdapter() {
    }

//    public HomeRecyclerViewAdapter(List<String> nearBySellers, List<String> ortherSellers) {
//        this.nearBySellers = nearBySellers;
//        this.ortherSellers = ortherSellers;
//    }

    /**
     * viewType就是下面getItemViewType方法里返回的type
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TITLE:
                View title = View.inflate(parent.getContext(), R.layout.item_title, null);
                TitleHoler titleHoler = new TitleHoler(title);
                return titleHoler;
            case TYPE_DIVISION:
                View division = View.inflate(parent.getContext(), R.layout.item_division, null);
                DivisionHoler divisionHoler = new DivisionHoler(division);
                return divisionHoler;
            case TYPE_SELLER:
                View seller = View.inflate(parent.getContext(), R.layout.item_seller, null);
                SellerHoler sellerHoler = new SellerHoler(seller);
                return sellerHoler;
        }
        return null;
    }

    private int divisionSum = 0;

    private HashMap<Integer, Integer> positionToIndex = new HashMap<>();

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_TITLE:
                break;
            case TYPE_DIVISION:
                divisionSum++;
                break;
            case TYPE_SELLER:
                // 商家：附近商家和其他商家
                // 依据position判断是从附近商家集合获取数据还是其他商家集合获取数据
                // 1到附近商家集合的size
                if (position - 1 < nearBySellers.size()) {
                    // nearBySellers有个数据 index：0-4
                    int index = position - 1;

//                    ((SellerHoler) holder).tv.setText(nearBySellers.get(index).getName());
                    ((SellerHoler) holder).setSeller(nearBySellers.get(index));
                } else {
                    // 从其他商家中集合中获取数据，position与集合的index对应关系
                    // 分割的数量

                    // position: size+2                  第一组普通商家开始的位置
//                    int index =position-nearBySellers.size()-1-divisionSum;

//                    ((SellerHoler)holder).tv.setText(ortherSellers.get(index));


                    // 判断：如果positionToIndex中能够通过position获取到index信息，直接到其他商家集合获取数据
                    // 获取不到，计算index，并建立position与index的对应关系
                    int index = 0;
                    if (positionToIndex.containsKey(position)) {
                        index = positionToIndex.get(position);
                    } else {
                        index = position - nearBySellers.size() - 1 - divisionSum;
                        positionToIndex.put(position, index);
                    }

//                    ((SellerHoler) holder).tv.setText(ortherSellers.get(index).getName());
                    ((SellerHoler) holder).setSeller(ortherSellers.get(index));
                }
                break;
        }
    }

    private int ortherSellerPreGroupNum = 20;

    @Override
    public int getItemCount() {
        ;

        if (nearBySellers == null && ortherSellers == null) {
            return 0;
        }
        // 如果附近商家或其他商家有一个不为null，可以添加头信息
        int count = 1;

        if (nearBySellers != null) {
            count += nearBySellers.size();
        }
        if (ortherSellers != null) {
            count += 1;// 第一个分割线
            //  20-39     +1
            //  40-59     +2
            count += ortherSellers.size();
            count += ortherSellers.size() / ortherSellerPreGroupNum;
        }
        return count;
    }

    /**
     * 通过position获取类型
     */
    @Override
    public int getItemViewType(int position) {
        int type = -1;
        if (position == 0) {
            type = TYPE_TITLE;
        } else if (position == nearBySellers.size() + 1) {
            type = TYPE_DIVISION;
        } else if ((position - (nearBySellers.size() + 1)) % (ortherSellerPreGroupNum + 1) == 0) {
            type = TYPE_DIVISION;
        } else {
            type = TYPE_SELLER;
        }

        return type;
    }


    /**
     * 下面三种Holder
     */
    class TitleHoler extends RecyclerView.ViewHolder {
        SliderLayout sliderShow;

        public TitleHoler(View itemView) {
            super(itemView);
            sliderShow = (SliderLayout) itemView.findViewById(R.id.slider);

            testData(itemView);
        }

        private void testData(View itemView) {

            HashMap<String, String> url_maps = new HashMap<String, String>();
            url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
            url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
            url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
            url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

            for (String desc : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(itemView.getContext());
                textSliderView
                        .description(desc)
                        .image(url_maps.get(desc));
                sliderShow.addSlider(textSliderView);
            }
        }
    }

    class DivisionHoler extends RecyclerView.ViewHolder {
        TextView tv;

        public DivisionHoler(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    class SellerHoler extends RecyclerView.ViewHolder {
        TextView tv;

        public SellerHoler(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_title);
        }

        public void setSeller(final Seller seller) {
            tv.setText(seller.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(itemView.getContext(), BusinessActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("sellerId", seller.getId());
                    //获取配送起的价格
                    bundle.putInt("startDispatchPrice", seller.getSendPrice());
                    //获取商铺的名称
                    bundle.putString("sellerName", seller.getName());
                    intent.putExtras(bundle);

                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
