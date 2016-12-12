package com.android.wen.cstp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.wen.cstp.R;
import com.android.wen.cstp.activity.ItemReportActivity;
import com.android.wen.cstp.pojo.CSTPReportList;

import java.util.ArrayList;


/**
 * Created by zheng on 2016/8/8.
 */
public class CSTPReportAdapter extends RecyclerView.Adapter<CSTPReportAdapter.ViewHolder> {

    private ArrayList<CSTPReportList.DataBean> dataBeenList ;
    private LayoutInflater inflater;
    private Context context;
    private String reportsTime="";

    public CSTPReportAdapter(Context context, ArrayList<CSTPReportList.DataBean> dataBeenList) {
        this.dataBeenList = dataBeenList;
        this.context = context;
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
        final CSTPReportList.DataBean dataBean = dataBeenList.get(position);
        //违法车牌
        final String wfcp = dataBean.getFZJGDM() + dataBean.getHPHM().substring(1, dataBean.getHPHM().length());
        holder.tvId.setText(String.valueOf(position + 1 + ""));
        holder.tvNumber.setText(wfcp);
        holder.tvWfdate.setText(dataBean.getWFSJ());
        if(reportsTime.equals(dataBeenList.get(position).getWFSJ())){
            holder.tvReportsTime.setVisibility(View.GONE);
        }else {
            reportsTime=dataBeenList.get(position).getWFSJ();
            holder.tvReportsTime.setVisibility(View.VISIBLE);
            holder.tvReportsTime.setText(reportsTime);
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context,ItemReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataBean",dataBean);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return dataBeenList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView tvId;
        TextView tvNumber;
        TextView tvWfdate;
        TextView tvReportsTime;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvId = (TextView) view.findViewById(R.id.tv_id);
            tvNumber = (TextView) view.findViewById(R.id.tv_HPHM);
            tvWfdate = (TextView) view.findViewById(R.id.tv_WFSJ);
            tvReportsTime= (TextView) view.findViewById(R.id.reports_time);
        }
    }
}
