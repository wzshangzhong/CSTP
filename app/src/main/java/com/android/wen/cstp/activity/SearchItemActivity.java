package com.android.wen.cstp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.android.wen.cstp.R;
import com.android.wen.cstp.adapter.CSTPReportAdapter;
import com.android.wen.cstp.pojo.CSTPReportList;
import java.util.ArrayList;

public class SearchItemActivity extends AppCompatActivity {
    private ArrayList<CSTPReportList.DataBean> dataBeenList;
    private RecyclerView rvItemReports;
    private CSTPReportAdapter mCSTPReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);
        initView();
        Bundle bundle = getIntent().getExtras();
        dataBeenList = (ArrayList<CSTPReportList.DataBean>) bundle.get("ReportsSearch_dataBeenList");
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

        dataBeenList = new ArrayList<>();
        mCSTPReportAdapter = new CSTPReportAdapter(this, dataBeenList);
        rvItemReports = (RecyclerView) findViewById(R.id.rv_item_reports);
        rvItemReports.setLayoutManager(new LinearLayoutManager(this));
        rvItemReports.setAdapter(mCSTPReportAdapter);
    }
}
