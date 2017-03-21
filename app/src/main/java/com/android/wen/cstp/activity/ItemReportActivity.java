package com.android.wen.cstp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.pojo.CSTPReportList;
import com.android.wen.cstp.pojo.CstpWfjb;
import com.android.wen.cstp.pojo.WFJB;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;

/*举报信息详情页面*/
public class ItemReportActivity extends AppCompatActivity {
    private WFJB wfjb;
    @Bind(R.id.tv_wfcp_item)
    TextView tvWfcp;
    @Bind(R.id.tv_wfdz_item)
    TextView tvWfdz;
    @Bind(R.id.tv_qksm_item)
    TextView tvQksm;
    @Bind(R.id.tv_wfsj_item)
    TextView tvWfsj;
    @Bind(R.id.tv_wfbm_item)
    TextView tvWfbm;
    @Bind(R.id.image0)
    ImageView imageView0;
    @Bind(R.id.image1)
    ImageView imageView1;
    @Bind(R.id.image2)
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_report);
        ButterKnife.bind(this);
        wfjb = (WFJB) getIntent()
                .getSerializableExtra("wfjb_reports");
        //if (dataBean != null && dataBean.getFZJGDM().length() > 0) {

        tvWfcp.setText(wfjb.getFzjg().substring(0, 1) + wfjb.getHphm());
        tvWfdz.setText("违法地址：" + wfjb.getWfdd());
        tvWfsj.setText("违法时间：" + wfjb.getWfsj());
        //tvWfbm.setText("违法编码：" + wfjb.getWFBH());
        tvQksm.setText("情况说明：" + wfjb.getXxms());

        Glide.with(this).load(GlobalApp.USER_URL + wfjb.getTplj().split(",")[0]).into(imageView0);
        Glide.with(this).load(GlobalApp.USER_URL + wfjb.getTplj().split(",")[1]).into(imageView1);
        Glide.with(this).load(GlobalApp.USER_URL + wfjb.getTplj().split(",")[2]).into(imageView2);

      /*  } else if (cstpWfjb != null && cstpWfjb.getWfch().length() > 0) {

            tvWfcp.setText("" + cstpWfjb.getWfch());
            tvWfdz.setText("违法地址：" + cstpWfjb.getWfld());
            tvWfsj.setText("违法时间：" + cstpWfjb.getWfsj());
            tvWfbm.setText("联系电话：" + cstpWfjb.getLxdh());

            *//*Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=0").into(imageView0);
            Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=1").into(imageView1);
            Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=2").into(imageView2);
            Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=3").into(imageView3);*//*
            Glide.with(this).load(cstpWfjb.getImage1_path()).into(imageView0);
            Glide.with(this).load(cstpWfjb.getImage2_path()).into(imageView1);
            Glide.with(this).load(cstpWfjb.getImage3_path()).into(imageView2);
        }*/
    }
}
