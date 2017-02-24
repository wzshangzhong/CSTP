package com.wenz.shopping.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wenz.shopping.R;
import com.wenz.shopping.activity.ShoppingCartActivity;
import com.wenz.shopping.pojo.ShopItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<ShopItem> shopItems;

    public ShopAdapter(Activity activity, ArrayList<ShopItem> shopItems) {
        this.activity = activity;
        this.shopItems = shopItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ShopItem shopItem = shopItems.get(position);
        holder.tvName.setText(shopItem.getName());
        holder.tvShopAv.setText(String.format("￥%S/人", shopItem.getPriAv()));
        holder.ratingBar.setRating((float) shopItem.getRating());
        holder.tvLocation.setText(shopItem.getLocation());
        holder.tvLabShop.setText(shopItem.getTypeName());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ShoppingCartActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ShopId", shopItem.getId());
                bundle.putString("name",shopItem.getName());
                intent.putExtras(bundle);
                activity.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return shopItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        private CardView cardView;
        public TextView tvName;
        public RatingBar ratingBar;
        private TextView tvLabShop;
        private TextView tvLocation;
        private TextView tvShopAv;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            cardView = (CardView) view.findViewById(R.id.card_view);
            tvName = (TextView) view.findViewById(R.id.tvName);
            tvShopAv = (TextView) view.findViewById(R.id.shop_av);
            ratingBar = (RatingBar) view.findViewById(R.id.rating_bar);
            tvLocation = (TextView) view.findViewById(R.id.tv_loc_shop);
            tvLabShop = (TextView) view.findViewById(R.id.tv_lab_shop);
        }
    }
}
