package com.wenz.shopping.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.wenz.shopping.App;
import com.wenz.shopping.R;
import com.wenz.shopping.activity.ShoppingCartActivity;
import com.wenz.shopping.pojo.GoodsItem;
import com.wenz.shopping.pojo.ShopItem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/23.
 */
public class ShopAdapter extends BaseAdapter {

    private ArrayList<GoodsItem> goodsItems;
    private ArrayList<ShopItem> shopItems;
    private LayoutInflater mInflater;
    private Context mContext;

    public ShopAdapter(Context context, ArrayList<ShopItem> shopItems) {
        this.mContext = context;
        this.shopItems = shopItems;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return shopItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ShopItem shopItem = shopItems.get(i);
        ViewHolder holder = null;
        if (view == null) {

            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_card, null);
            holder.cardView = (CardView) view.findViewById(R.id.card_view);
            holder.tvName = (TextView) view.findViewById(R.id.tv_name);
            holder.tvShopAv = (TextView) view.findViewById(R.id.shop_av);
            holder.ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
            holder.tvLocation = (TextView) view.findViewById(R.id.tv_loc_shop);
            holder.tvLabShop = (TextView) view.findViewById(R.id.tv_lab_shop);
            holder.IvShop = (ImageView) view.findViewById(R.id.iv_shop);
            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }


        holder.tvName.setText(shopItem.getName());
        holder.tvShopAv.setText(String.format("￥%S/人", shopItem.getPriAv()));
        holder.ratingBar.setRating((float) shopItem.getRating());
        holder.tvLocation.setText(shopItem.getLocation());
        holder.tvLabShop.setText(shopItem.getTypeName());
        Glide.with(mContext).load(App.BASE_URL + shopItem.getPic()).into(holder.IvShop);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取数据
                OkHttpUtils
                        .post(App.BASE_URL+"/api/shop/post")
                        .postJson("{'code':'1','msg':"+shopItem.getId()+"}")
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .execute(new StringCallback() {
                            @Override
                            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                Gson gson = new Gson();
                                goodsItems = gson.fromJson(s,
                                        new TypeToken<ArrayList<GoodsItem>>() {
                                        }.getType());

                            }
                        });

                Intent intent = new Intent(mContext, ShoppingCartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("goodsItems",goodsItems);
                bundle.putInt("ShopId", shopItem.getId());
                bundle.putString("name", shopItem.getName());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });


        return view;
    }

    public static class ViewHolder {
        public View view;
        private CardView cardView;
        public TextView tvName;
        public RatingBar ratingBar;
        private TextView tvLabShop;
        private TextView tvLocation;
        private TextView tvShopAv;
        private ImageView IvShop;

        public ViewHolder() {

        }

    }

}
