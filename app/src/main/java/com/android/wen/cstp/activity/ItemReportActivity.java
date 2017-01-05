package com.android.wen.cstp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.pojo.CSTPReportList;
import com.android.wen.cstp.pojo.CstpWfjb;
import com.bumptech.glide.Glide;

/*举报信息详情页面*/
public class ItemReportActivity extends AppCompatActivity {
    private CSTPReportList.DataBean dataBean;
    private CstpWfjb cstpWfjb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_report);
        dataBean = (CSTPReportList.DataBean) getIntent()
                .getSerializableExtra("dataBean");
        cstpWfjb = (CstpWfjb) getIntent()
                .getSerializableExtra("cstpWfjb");
        /*"发证机关:" + information.FZJGDM,
                "号牌号码:" + information.HPHM,
                "违法时间:" + information.WFSJ,
                "违法编码:" + information.WFBH,
                "违法地点:" + information.WFDDDM,*/
        TextView tvWfcp = (TextView) findViewById(R.id.tv_wfcp_item);//违法车牌
        TextView tvWfdz = (TextView) findViewById(R.id.tv_wfdz_item);//违法地点
        TextView tvQksm = (TextView) findViewById(R.id.tv_qksm_item);//情况说明
        TextView tvWfsj = (TextView) findViewById(R.id.tv_wfsj_item);//违法时间
        TextView tvWfbm = (TextView) findViewById(R.id.tv_wfbm_item);//违法编码
        ImageView imageView0 = (ImageView) findViewById(R.id.image0);
        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        if(dataBean!=null&&dataBean.getFZJGDM().length()>0){
        String wfcp = dataBean.getFZJGDM() + dataBean.getHPHM().substring(1, dataBean.getHPHM().length());
        tvWfcp.setText(""+wfcp);
        tvWfdz.setText("违法地址："+dataBean.getWFDDDM());
        tvWfsj.setText("违法时间："+dataBean.getWFSJ());
        tvWfbm.setText("违法编码："+dataBean.getWFBH());

        Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                + dataBean.getWFBH() + "&num=0").into(imageView0);
        Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                + dataBean.getWFBH() + "&num=1").into(imageView1);
        Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                + dataBean.getWFBH() + "&num=2").into(imageView2);
        Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                + dataBean.getWFBH() + "&num=3").into(imageView3);

        }else if(cstpWfjb!=null&&cstpWfjb.getWfch().length()>0) {

            tvWfcp.setText(""+cstpWfjb.getWfch());
            tvWfdz.setText("违法地址："+cstpWfjb.getWfld());
            tvWfsj.setText("违法时间："+cstpWfjb.getWfsj());
            tvWfbm.setText("联系电话："+cstpWfjb.getLxdh());

            /*Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=0").into(imageView0);
            Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=1").into(imageView1);
            Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=2").into(imageView2);
            Glide.with(this).load(GlobalApp.BASE_URL + "FileContentResult?wfbh="
                    + dataBean.getWFBH() + "&num=3").into(imageView3);*/
            Glide.with(this).load(cstpWfjb.getImage1_path()).into(imageView1);
            Glide.with(this).load(cstpWfjb.getImage2_path()).into(imageView2);
            Glide.with(this).load(cstpWfjb.getImage3_path()).into(imageView3);
        }
    }
}
