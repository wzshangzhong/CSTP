package com.android.wen.cstp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.adapter.HomeRecyclerAdapter;
import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.pojo.DataWeather;
import com.android.wen.cstp.pojo.User;
import com.android.wen.cstp.util.UserUtils;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import java.util.Calendar;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private DrawerLayout drawerLayout;//抽屉
    private ImageView ivMineHome;
    private TextView tvTimeHome;//时分秒  未几十刷新
    private TextView tvDateHome;//月日星期
    private TextView tvWeatherHome;//天气和温度
    private TextView tvAirHome;//空气质量
    //线程msgKey值
    private static final int msgKey1 = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    long sysTime = System.currentTimeMillis();
                    CharSequence sysTimeStr = DateFormat.format("hh:mm:ss", sysTime);
                    tvTimeHome.setText(sysTimeStr);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);//初始化抽屉
        // 8.1 导航视图
        NavigationView navView = (NavigationView) findViewById(R.id.nv_view);
        if (navView != null) {
            setNavView(navView);
        }
        ivMineHome = (ImageView) findViewById(R.id.iv_mine_home);
        ivMineHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);//打开左侧抽屉
            }
        });

        //时间
        tvTimeHome = (TextView) findViewById(R.id.tv_time_home);
        new TimeThread().start();//一秒刷新一次
        tvDateHome = (TextView) findViewById(R.id.tv_date_home);
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);//年
        int month = c.get(Calendar.MONTH);//月
        int week = c.get(Calendar.DAY_OF_WEEK);//星期
        int day = c.get(Calendar.DAY_OF_MONTH);//日

        String strWeek = "";
        if (week == 1) strWeek = "日";
        if (week == 2) strWeek = "一";
        if (week == 3) strWeek = "二";
        if (week == 4) strWeek = "三";
        if (week == 5) strWeek = "四";
        if (week == 6) strWeek = "五";
        if (week == 7) strWeek = "六";
        String date = String.format("%d月%d日 星期%s", month, day, strWeek);
        //  tvTimeHome.setText(time);
        tvDateHome.setText(date);

        //设置天气
        tvWeatherHome = (TextView) findViewById(R.id.tv_weather_home);
        tvAirHome = (TextView) findViewById(R.id.tv_air_home);
        getWeather();

        //菜单数据
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_content_home);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new HomeRecyclerAdapter(this));
    }

    private void setNavView(NavigationView navView) {
        View view = navView.getHeaderView(0);
        //-- 左侧抽屉导航视图 (用户名 / 图片) ------------------------------
        ImageView imgView = (ImageView) view.findViewById(R.id.image_view);
        TextView tvUsername = (TextView) view.findViewById(R.id.tv_username);
        RelativeLayout rlReportNh = (RelativeLayout) view.findViewById(R.id.rl_report_nh);//举报记录
        RelativeLayout rlInquireNh = (RelativeLayout) view.findViewById(R.id.rl_inquire_nh);//违法记录
        RelativeLayout rlCheckUpdateNh = (RelativeLayout) view.findViewById(R.id.rl_checkupdate_nh);
        RelativeLayout rlLoginOutNh = (RelativeLayout) view.findViewById(R.id.rl_loginout_nh);
        imgView.setImageResource(R.mipmap.ic_launcher);
        tvUsername.setText("未设置");

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("显示文字");
                builder.setMessage("干嘛点我");
                builder.create().show();
            }
        });
        //违法举报
        rlReportNh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ReportsActivity.class));
            }
        });
        //违法记录
        rlInquireNh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InquireActivity.class));
            }
        });
        //检查更新
        rlCheckUpdateNh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VersionActivity.class));
            }
        });
        //注销登陆
        rlLoginOutNh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserUtils.save(MainActivity.this, new User(0, "", "", ""));
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        // 关闭抽屉
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void getWeather() {
        OkHttpUtils.post("http://op.juhe.cn/onebox/weather/query")
                .params("cityname", "长沙")
                .params("key", "f269253ceba3112d77bf38afc77edcd5")
                .params("dtype", "json")
                .execute(new StringCallback() {
                             @Override
                             public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                                 Gson gson = new Gson();
                                 DataWeather dataWeather = gson.fromJson(s, DataWeather.class);
                                 Log.e("MainActivity", dataWeather.getResult().getData().getDate());
                                 tvWeatherHome.setText(dataWeather.getResult().getData().getRealtime().getWeather().getInfo() + " " + dataWeather.getResult().getData().getRealtime().getWeather().getTemperature() + "℃");//天气和温度

                                 Log.d("MainActivity", dataWeather.getResult().getData().getDate());

                                 /*tvAirHome.setText("pm2.5：" + dataWeather.getResult().getData().getPm25Data().getPm25().getPm25()+"\n"
                                         +dataWeather.getResult().getData().getPm25Data().getPm25().getQuality() );//*/
                             }

                             @Override
                             public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                                 Log.e("MainActivity", "有错误，错误代码为：" + e);
                                 super.onError(isFromCache, call, response, e);
                             }
                         }
                );
    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 监控返回键
            new AlertDialog.Builder(this).setTitle("提示")
                    .setIconAttribute(android.R.attr.alertDialogIcon)
                    .setMessage("确定要退出吗?")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create().show();
            return false;
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            // 监控菜单键
            Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

}
