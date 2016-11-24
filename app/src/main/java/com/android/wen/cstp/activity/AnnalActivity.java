package com.android.wen.cstp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;

public class AnnalActivity extends BaseActivity {
    private String cllx;//车型
    private String hphm;//车牌
    private String phone;//电话

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annal);

        init();

    }

    private void init() {
        cllx = (String) getIntent().getSerializableExtra("cllx");
        hphm = (String) getIntent().getSerializableExtra("hphm");
        phone = (String) getIntent().getSerializableExtra("phone");


    }
}
