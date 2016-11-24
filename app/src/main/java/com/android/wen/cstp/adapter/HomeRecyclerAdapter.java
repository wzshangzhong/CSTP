package com.android.wen.cstp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wen.cstp.R;
import com.android.wen.cstp.activity.InquireActivity;
import com.android.wen.cstp.activity.ReportActivity;
import com.android.wen.cstp.activity.ReportsActivity;

/**
 * Created by Administrator on 2016/11/1.
 */
public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.ListHolder> {

    private Context mContext;
    int icon[] = {R.drawable.home_xxcx_btn, R.drawable.home_wfbj_btn,
            R.drawable.home_daiding_btn, R.drawable.home_daiding_btn, R.drawable.home_daiding_btn,
            R.drawable.home_daiding_btn, R.drawable.home_daiding_btn, R.drawable.home_daiding_btn,
            R.drawable.home_more_btn};
    String title[] = {"信息查询", "违法举报",
            "待定", "待定", "待定", "待定", "待定", "待定", "待定",
            "更多"};


    public HomeRecyclerAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(parent.getContext(), R.layout.item_grid_home, null);
        return new ListHolder(view);
    }


    @Override
    public void onBindViewHolder(ListHolder holder, final int position) {

        holder.icon.setImageResource(icon[position]);
        holder.title.setText(title[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("已被点击", "位置:" + position);

                switch (position) {
                    case 0:
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                        final String[] wfcxs = new String[]{
                                 "举报信息查询","违法信息查询"};
                        builder.setItems(wfcxs, new DialogInterface.OnClickListener() {
                            Intent intent = new Intent();

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    //举报别人的信息
                                    intent.setClass(mContext, ReportsActivity.class);
                                    mContext.startActivity(intent);

                                } else {
                                    //自己违法信息
                                    intent.setClass(mContext, InquireActivity.class);
                                    mContext.startActivity(intent);
                                }
                            }
                        });
                        builder.create().show();


                        break;
                    case 1:
                        Intent intent = new Intent();
                        intent.setClass(mContext, ReportActivity.class);
                        mContext.startActivity(intent);
                        break;
                    default:
                        AlertDialog.Builder b = new AlertDialog.Builder(mContext);
                        b.setTitle("提示");
                        b.setMessage("此功能未开放，请耐心等待");
                        b.setNegativeButton("确定", null);
                        b.show();
                        break;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return icon.length;
    }


    public class ListHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;

        public ListHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.pic);
            title = (TextView) itemView.findViewById(R.id.tv_title_pic);
        }

    }
}
