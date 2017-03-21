package com.android.wen.cstp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.wen.cstp.R;
import com.android.wen.cstp.pojo.WFJB;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/21.
 */
public class WFXXCXAdapter extends BaseAdapter {

    private ArrayList<WFJB> wfjbs;

    private LayoutInflater mInflater;
    private Context mContext;
    private WFJB wfjb;
    private String reportsTime = "";

    public WFXXCXAdapter(Context context, ArrayList<WFJB> wfjbs) {
        this.mContext = context;
        this.wfjbs = wfjbs;
        mInflater = LayoutInflater.from(context);
        Log.v("WFXXCXAdapter is wfjbs.size", "" + wfjbs.size());
    }

    @Override
    public int getCount() {
        return wfjbs.size();
    }

    @Override
    public Object getItem(int i) {
        return wfjbs.get(i);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        wfjb = wfjbs.get(i);
        ViewHolder holder = null;
        holder = new ViewHolder();
        view = mInflater.inflate(R.layout.item_selelt, viewGroup, false);
        holder.tvId = (TextView) view.findViewById(R.id.tv_id_item);
        holder.tvNumber = (TextView) view.findViewById(R.id.tv_HPHM);
        holder.tvWfdate = (TextView) view.findViewById(R.id.tv_WFSJ);
        holder.llReportsTime = (LinearLayout) view.findViewById(R.id.ll_reports_time);
        holder.tvReportsTime = (TextView) view.findViewById(R.id.tv_reports_time);

        holder.tvId.setText(String.valueOf(i + 1 + ""));
        holder.tvNumber.setText(wfjb.getFzjg().substring(0, 1) + wfjb.getHphm());
       // holder.tvWfdate.setText(wfjb.getWfsj());
        Log.v("WFXXCXAdapter",wfjb.toString());
        if (reportsTime.equals(wfjb.getWfsj())) {
            holder.llReportsTime.setVisibility(View.GONE);
        } else {
            reportsTime = wfjb.getWfsj();
            holder.llReportsTime.setVisibility(View.VISIBLE);
            holder.tvReportsTime.setText(reportsTime);
        }


        //写上面点点击事件就是为了让这些空间不能点击
        view.setClickable(false);
        return view;
    }

    public static class ViewHolder {
        TextView tvId;
        TextView tvNumber;
        TextView tvWfdate;
        LinearLayout llReportsTime;
        TextView tvReportsTime;

        public ViewHolder() {

        }

    }

}

