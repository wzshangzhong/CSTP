package com.android.wen.cstp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.android.wen.cstp.R;
import com.android.wen.cstp.adapter.CSTPReportAdapter;
import com.android.wen.cstp.adapter.WFXXCXAdapter;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.pojo.CSTPReportList;
import com.android.wen.cstp.pojo.WFJB;

import java.util.ArrayList;
//违法查询中搜索的
public class SearchItemActivity extends BaseActivity {
   // private ArrayList<CSTPReportList.DataBean> dataBeenList;
    private ListView lvItemReports;
    //private CSTPReportAdapter mCSTPReportAdapter;
    private WFXXCXAdapter mWFXXCXAdapter;
    private ArrayList<WFJB> wfjbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);
        initView();
        Bundle bundle = getIntent().getExtras();
        wfjbs = (ArrayList<WFJB>) bundle.get("wfjbs");
       // dataBeenList = (ArrayList<CSTPReportList.DataBean>) bundle.get("ReportsSearch_dataBeenList");
    }

    private void initView() {
        //top设置
        ImageButton topBack = (ImageButton) findViewById(R.id.top_back);
        topBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TextView tvTop = (TextView) findViewById(R.id.top_title);
        tvTop.setText("举报信息查询");

        //dataBeenList = new ArrayList<>();
        wfjbs = new ArrayList<>();
        mWFXXCXAdapter = new WFXXCXAdapter(this,wfjbs);
        //mCSTPReportAdapter = new CSTPReportAdapter(this, dataBeenList);
        lvItemReports = (ListView) findViewById(R.id.lv_item_reports);
        //lvItemReports.setLayoutManager(new LinearLayoutManager(this));
        lvItemReports.setAdapter(mWFXXCXAdapter);
    }
}
