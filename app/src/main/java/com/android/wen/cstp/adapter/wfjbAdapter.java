package com.android.wen.cstp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.wen.cstp.R;
import com.android.wen.cstp.activity.ItemReportActivity;
import com.android.wen.cstp.pojo.CstpWfjb;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/12.
 */
public class WfjbAdapter extends RecyclerView.Adapter<WfjbAdapter.ViewHolder> {

    private ArrayList<CstpWfjb> wfjbDatas ;
    private LayoutInflater inflater;
    private Context mContext;
    private String reportsTime="";

    public WfjbAdapter(Context context, ArrayList<CstpWfjb> cstpWfjbs) {
        this.wfjbDatas = cstpWfjbs;
        this.mContext = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_selelt, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CstpWfjb wfjbData =wfjbDatas.get(position);
        //违法车牌
        final String wfcp = wfjbData.getWfch();
        holder.tvId.setText(String.valueOf(position + 1 + ""));
        holder.tvNumber.setText(wfcp);
        holder.tvWfdate.setText(wfjbData.getWfsj());
        if(reportsTime.equals(wfjbData.getWfsj())){
            holder.llReportsTime.setVisibility(View.GONE);
        }else {
            reportsTime=wfjbData.getWfsj();
            holder.llReportsTime.setVisibility(View.VISIBLE);
            holder.tvReportsTime.setText(reportsTime);
        }
    holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(mContext,ItemReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("cstpWfjb",wfjbData);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return wfjbDatas.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        TextView tvId;
        TextView tvNumber;
        TextView tvWfdate;
        LinearLayout llReportsTime;
        TextView tvReportsTime;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvId = (TextView) view.findViewById(R.id.tv_id);
            tvNumber = (TextView) view.findViewById(R.id.tv_HPHM);
            tvWfdate = (TextView) view.findViewById(R.id.tv_WFSJ);
            llReportsTime= (LinearLayout) view.findViewById(R.id.ll_reports_time);
            tvReportsTime = (TextView) view.findViewById(R.id.tv_reports_time);
        }
    }
}
