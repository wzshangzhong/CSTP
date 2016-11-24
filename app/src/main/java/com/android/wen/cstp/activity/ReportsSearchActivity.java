package com.android.wen.cstp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.R;
import com.android.wen.cstp.pojo.CSTPReportList;
import com.android.wen.cstp.view.DateTimePickDialogUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReportsSearchActivity extends AppCompatActivity {
    @Bind(R.id.top_title)
    TextView topTitle;//本页标题
    @Bind(R.id.et_hphm_search)
    EditText etHphmSearch;//输入的车牌号码
    @Bind(R.id.tv_fzjg_search)
    TextView tvFzjgSearch;//发证机关 湘
    @Bind(R.id.tv_hpzl_search)
    TextView tvHpzlSearch; //号牌类型 A
    @Bind(R.id.tv_wfxw_search)
    TextView tvWfxwSearch; //违法行为
    @Bind(R.id.tv_time_search)
    TextView tvTimeSearch; //违法时间
    @Bind(R.id.tv_place_search)
    TextView tvPlaceSearch; //违法地址
    @Bind(R.id.tv_cllx_search)
    TextView tvCllxSearch; //车型
    @Bind(R.id.tv_csys_search)
    TextView tvCsysSearch;//车身颜色
    private ArrayList<CSTPReportList.DataBean> dataBeenList;//需要显示的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_search);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        dataBeenList = (ArrayList<CSTPReportList.DataBean>) bundle.get("dataBeenList");
        Log.e("ReportsSearchActivity", "显示数据长度为：" + dataBeenList.size());
        init();
    }

    private void init() {
        topTitle.setText("举报信息查询");

    }

    @OnClick({R.id.top_back, R.id.tv_fzjg_search, R.id.tv_hpzl_search, R.id.tv_wfxw_search, R.id.tv_time_search, R.id.tv_place_search, R.id.tv_cllx_search, R.id.tv_csys_search, R.id.btn_search})
    public void onClick(View view) {
        AlertDialog.Builder builder;
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.tv_fzjg_search:
                //发证机关
                builder = new AlertDialog.Builder(this);
                final String[] fzjgs = new String[]{
                        "空","湘", "粤", "桂", "琼", "川", "贵", "云",
                        "渝", "藏", "陕", "甘", "青", "宁", "新",
                        "京", "津", "冀", "晋", "蒙", "辽", "吉",
                        "黑", "沪", "苏", "浙", "皖", "闽", "赣",
                        "鲁", "豫", "鄂"};

                builder.setItems(fzjgs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvFzjgSearch.setText(fzjgs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_hpzl_search:
                builder = new AlertDialog.Builder(this);
                final String[] hpzls = new String[]{
                        "空","A", "B", "C", "D", "E", "F", "G",
                        "H", "I", "J", "K", "L", "M", "N",
                        "O", "P", "Q", "R", "S", "T", "U",
                        "V", "W", "X", "Y", "Z",};

                builder.setItems(hpzls, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvHpzlSearch.setText(hpzls[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_wfxw_search:
                builder = new AlertDialog.Builder(this);
                final String[] wfxws = new String[]{
                        "空","故意遮挡机动车号牌", "故意污损机动车号牌", "使用其他机动车号牌",
                        "使用伪造、变造机动车号牌", "无证或驾驶证被扣留期间驾驶",
                        "未按规定安装机动车号牌", "工程车装载超出栏版", "工程车闯红灯",
                        "工程车逆向行驶"};

                builder.setItems(wfxws, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvWfxwSearch.setText(wfxws[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_time_search:
                //时间得改 输入框、时间框、确定按钮
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this);
                dateTimePicKDialog.dateTimePicKDialog(tvTimeSearch);
                break;
            case R.id.tv_place_search:
                //不知道怎么操作(手动输入还是定位)，手动输入吧
                break;
            case R.id.tv_cllx_search:
                builder = new AlertDialog.Builder(this);
                final String[] cllxs = new String[]{
                        "空","小型汽车", "大型汽车", "普通摩托车",
                        "挂车", "低速车", "轻便摩托车",
                        "教练汽车", "教练摩托车"};

                builder.setItems(cllxs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCllxSearch.setText(cllxs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_csys_search:
                builder = new AlertDialog.Builder(this);
                final String[] csyss = new String[]{
                        "空","灰色", "黄色", "粉色",
                        "紫色", "绿色", "红色",
                        "蓝色", "棕色", "黑色",
                        "白色", "橙色", "不确定",};

                builder.setItems(csyss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvCsysSearch.setText(csyss[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.btn_search:
                //查询 加转跳  先判断是不是从查询页面跳转的，如果是则加载本次调转数据
                Intent intent = new Intent(this,SearchItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ReportsSearch_dataBeenList",dataBeenList);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 监控返回键
            finish();
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            // 监控菜单键  长按菜单键
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
