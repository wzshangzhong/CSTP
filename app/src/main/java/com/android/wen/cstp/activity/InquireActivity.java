package com.android.wen.cstp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.R;
import com.android.wen.cstp.activity.AnnalActivity;
import com.android.wen.cstp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/*违法查询 查询自己*/
public class InquireActivity extends BaseActivity {

    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.tv_cllx_inquire)
    TextView tvCllxInquire;
    @Bind(R.id.et_hphm_inquire)
    EditText etHphmInquire;
    @Bind(R.id.tv_fzjg_inquire)
    TextView tvFzjgInquire;
    @Bind(R.id.tv_hpzl_inquire)
    TextView tvHpzlInquire;
    @Bind(R.id.et_phone_inquire)
    EditText etPhoneInquire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquire);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topTitle.setText("违法查询");//设置头标

    }

    @OnClick({R.id.top_back, R.id.tv_cllx_inquire, R.id.tv_fzjg_inquire, R.id.tv_hpzl_inquire, R.id.bt_submit_inquire})
    public void onClick(View view) {
        AlertDialog.Builder builder;
        switch (view.getId()) {
            case R.id.top_back://返回
                finish();
                break;
            case R.id.tv_cllx_inquire://车辆类型
                builder = new AlertDialog.Builder(this);
                final String[] cllxs = new String[]{"大型汽车", "小型汽车", "普通摩托车", "轻便摩托车", "低速车", "挂车", "教练汽车", "教练摩托车"};
                builder.setItems(cllxs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCllxInquire.setText(cllxs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_fzjg_inquire://发证机关，默认为湘
                builder = new AlertDialog.Builder(this);
                final String[] fzjgs = new String[]{
                        "湘", "粤", "桂", "琼", "川", "贵", "云",
                        "渝", "藏", "陕", "甘", "青", "宁", "新",
                        "京", "津", "冀", "晋", "蒙", "辽", "吉",
                        "黑", "沪", "苏", "浙", "皖", "闽", "赣",
                        "鲁", "豫", "鄂"};

                builder.setItems(fzjgs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvFzjgInquire.setText(fzjgs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_hpzl_inquire://号牌类型，默认为A
                builder = new AlertDialog.Builder(this);
                final String[] hpzls = new String[]{
                        "A", "B", "C", "D", "E", "F", "G",
                        "H", "I", "J", "K", "L", "M", "N",
                        "O", "P", "Q", "R", "S", "T", "U",
                        "V", "W", "X", "Y", "Z",};

                builder.setItems(hpzls, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvHpzlInquire.setText(hpzls[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.bt_submit_inquire://提交
                submitInquire();
                break;
        }
    }

    private void submitInquire() {
        String cllx = tvCllxInquire.getText().toString();
        String hphm = "";
        if (etHphmInquire.getText().toString() != null && etHphmInquire.getText().length() == 5) {
            hphm = tvFzjgInquire.getText().toString() + tvHpzlInquire.getText().toString() + etHphmInquire.getText().toString();
        } else {
            Toast.makeText(this, "请正确输入车牌号码", Toast.LENGTH_SHORT).show();
            return;
        }
        String phone = "";
        if (etPhoneInquire.getText().toString() != null && etPhoneInquire.getText().length() == 11) {
            phone = etPhoneInquire.getText().toString();
        } else {
            Toast.makeText(this, "请正确输入电话号码", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.v("InquireActivity", "车型：" + cllx + "号牌号码：" + hphm + "手机号码：" + phone);
        Intent intent = new Intent(this, AnnalActivity.class);

        intent.putExtra("cllx",cllx);
        intent.putExtra("hphm",hphm);
        intent.putExtra("phone",phone);
        startActivity(intent);
    }
}
