package com.wenz.shopping.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

import okhttp3.Call;
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
    private ShopItem shopItem;

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
        return shopItems.get(i);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        shopItem = shopItems.get(i);
        ViewHolder holder = null;
        holder = new ViewHolder();
        view = mInflater.inflate(R.layout.item_card, viewGroup, false);
        holder.cardView = (CardView) view.findViewById(R.id.card_view);
        holder.tvName = (TextView) view.findViewById(R.id.tv_name);
        holder.tvShopAv = (TextView) view.findViewById(R.id.shop_av);
        holder.ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
        holder.tvLocation = (TextView) view.findViewById(R.id.tv_loc_shop);
        holder.tvLabShop = (TextView) view.findViewById(R.id.tv_lab_shop);
        holder.IvShop = (ImageView) view.findViewById(R.id.iv_shop);


        holder.tvName.setText(shopItem.getName());
        holder.tvShopAv.setText(String.format("￥%S/人", shopItem.getPriAv()));
        holder.ratingBar.setRating((float) shopItem.getRating());
        holder.tvLocation.setText(shopItem.getLocation());
        holder.tvLabShop.setText(shopItem.getTypeName());
        Glide.with(mContext).load(App.SHOP_URL + shopItem.getPic()).into(holder.IvShop);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        //写上面点点击事件就是为了让这些空间不能点击
        view.setClickable(false);

       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,shopItem.getName(),Toast.LENGTH_LONG).show();
                //获取数据
                OkHttpUtils
                        .post(App.BASE_URL + "/api/shop/post")
                        .postJson("{'code':'2','msg':'" + shopItem.getId() + "'}")
                        .mediaType(MediaType.parse("application/json; charset=utf-8"))
                        .execute(new StringCallback() {
                            @Override
                            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                Log.v("ShopAdapter is Goods:", s);
                                Gson gson = new Gson();
                                goodsItems = gson.fromJson(s,
                                        new TypeToken<ArrayList<GoodsItem>>() {
                                        }.getType());
                            }

                            @Override
                            public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                                super.onAfter(isFromCache, s, call, response, e);

                                Intent intent = new Intent(mContext, ShoppingCartActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("goodsItems", goodsItems);
                                bundle.putInt("ShopId", shopItem.getId());
                                bundle.putString("name", shopItem.getName());
                                Log.v("ShopAdapter is id",shopItem.getId()+"");
                                Log.v("ShopAdapter is name",shopItem.getName());
                                intent.putExtras(bundle);
                                Log.v("MainActivity", goodsItems.size() + "");
                                mContext.startActivity(intent);
                            }
                        });
            }
        });*/

        return view;
    }

    public static class ViewHolder {
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
