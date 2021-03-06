package com.android.wen.cstp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.adapter.CSTPReportAdapter;
import com.android.wen.cstp.adapter.WFXXCXAdapter;
import com.android.wen.cstp.adapter.WfjbAdapter;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.pojo.CSTPReportList;
import com.android.wen.cstp.pojo.CstpWfjb;
import com.android.wen.cstp.pojo.WFJB;
import com.android.wen.cstp.util.ComparableUtil;
import com.android.wen.cstp.util.NetWorkTool;
import com.android.wen.cstp.util.UserUtils;
import com.android.wen.cstp.view.LoadDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cache.CacheMode;
import com.lzy.okhttputils.callback.StringCallback;
import com.sivan.greendaopractice.DaoMaster;
import com.sivan.greendaopractice.DaoSession;
import com.sivan.greendaopractice.cstp_wfjbDao;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.ButterKnife;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/*举报别人的信息*/
public class ReportsActivity extends BaseActivity {
    private ListView mListView;
    private WFXXCXAdapter mWFXXCXAdapter;
    // private LoadDialog dialog;
    private RecyclerView rvItemReports;
    private CSTPReportAdapter mCSTPReportAdapter;
    private ArrayList<WFJB> wfjbs;
    private WFJB wfjb;
    private CSTPReportList mCSTPReportList;//网络数据
    private ArrayList<CSTPReportList.DataBean> dataBeenList;//需要显示的数据

    //下拉刷新的
    private SwipeRefreshLayout swipeRefreshLayout;


    //这是添加数据库数据
    private ArrayList<CstpWfjb> cstpWfjbs;
    private WfjbAdapter wfjbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_reports);
        initView();
        //获取数据库数据
        NetData();
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
        TextView topSearch = (TextView) findViewById(R.id.top_search);
        topSearch.setVisibility(View.VISIBLE);
      /*  topSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wfjbs.size() <= 0) {
                    // Toast.makeText(ReportsActivity.this, "暂无违法举报数据", Toast.LENGTH_SHORT).show();
                    UserUtils.showToast(ReportsActivity.this, "暂无违法举报数据");
                    return;
                }
                //传递集合
                Intent intent = new Intent(ReportsActivity.this, ReportsSearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wfjbs", wfjbs);
                intent.putExtras(bundle);
                startActivity(intent);

              *//*  //传递集合
                Intent intent = new Intent(ReportsActivity.this, ReportsSearchActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataBeenList", dataBeenList);
                intent.putExtras(bundle);
                startActivity(intent);*//*
            }
        });*/
        wfjbs = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.lv_item_reports);
        mWFXXCXAdapter = new WFXXCXAdapter(ReportsActivity.this, wfjbs);
        mListView.setAdapter(mWFXXCXAdapter);

        // mWFXXCXAdapter.notifyDataSetChanged();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                wfjb = wfjbs.get(i);
                Intent intent = new Intent(ReportsActivity.this, ItemReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("wfjb_reports", wfjb);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private void NetData() {
        OkHttpUtils.post(GlobalApp.WFJBCX_URL)
                .postJson("{'jbrxm':'" + UserUtils.getUser(ReportsActivity.this).getXm() + "'}")
                .cacheKey("WFJBCX")//添加缓存,可能是从缓存中读取值
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)//貌似要选缓存模式
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        Log.v("ReportsActivity is NetData", "s:" + s);
                        Log.v("ReportsActivity is NetData", "Request:" + request);
                        Log.v("ReportsActivity is NetData", "Response:" + response);
                        //json解析  转换为ArrayList集合
                        Gson gson = new Gson();
                        ArrayList<WFJB> wfjbArrayList = gson.fromJson(s, new TypeToken<ArrayList<WFJB>>() {
                        }.getType());
                        for (WFJB wfjb : wfjbs) {
                            Log.v("ReportsActivity",wfjb.toString());
                        }
                        wfjbs.addAll(wfjbArrayList);
                        mWFXXCXAdapter.notifyDataSetChanged();
                        Log.v("ReportsActivity", "wfjbs length：" + wfjbs.size());
                        //按照时间排序
                        ComparableUtil sort = new ComparableUtil();
                        Collections.sort(wfjbs, sort);
                        //数据添加至显示集合
//                        int netDataSize = mCSTPReportList.getData().size();//网络加载数据长度
                        //                      int viewDataSize = dataBeenList.size();//显示数据长度
                        /*for (int i = 0; i < mCSTPReportList.getData().size(); i++) {
                            dataBeenList.add(mCSTPReportList.getData().get(i));
                            Log.e("ReportsActivity", "违法时间：" + dataBeenList.get(i).getWFSJ());
                            mCSTPReportAdapter.notifyDataSetChanged();
                        }*/
                        //缓存集合
                        if (isFromCache) {
                            //缓存了
                            Log.e("ReportsActivity", "isFromCache:" + isFromCache);
                        } else {
                            //有网不需要缓存
                            Log.e("ReportsActivity", "isFromCache:" + isFromCache);
                        }

                        //关掉下拉刷新
//                        swipeRefreshLayout.setRefreshing(false);
                        Log.e("ReportsActivity", "获取集合长度：" + wfjbs.size());
                        Log.e("ReportActivity", s + "");


                        if (!wfjbs.isEmpty()) {
                            // Toast.makeText(ReportsActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                            UserUtils.showToast(ReportsActivity.this, "加载成功");
                        } else {
                            UserUtils.showToast(ReportsActivity.this, "加载失败");
                            //Toast.makeText(ReportsActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                        // dialog.dismiss();
                        //tvRecordReports.setText("");
                        Log.v("ReportsActivity", s + "");
                        super.onAfter(isFromCache, s, call, response, e);
                    }

                    @Override
                    public void onError(boolean isFromCache, Call call,
                                        @Nullable Response response, @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.v("ReportsActivity is NetData", "Exception:" + e);
                        Log.v("ReportsActivity is NetData", "Response:" + response);
                        //获取数据库数据
                        //*   DaoGenerator daoGenerator = new DaoGenerator(ReportsActivity.this);
                        //cstpReportList = daoGenerator.search();
                        // mCSTPReportAdapter.notifyDataSetChanged();*//*
//                        dialog.dismiss();


                    }
                });

    }

   /* private void postData() {
        OkHttpUtils.get(GlobalApp.USER_URL + "getCarInfo")
                .cacheKey("getCarInfo")//添加缓存,可能是从缓存中读取值
                .cacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)//貌似要选缓存模式
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        //json解析  转换为ArrayList集合
                        Gson gson = new Gson();
                        mCSTPReportList = gson.fromJson("{'code':'1','data':" + s + "}", CSTPReportList.class);

                        //按照时间排序
                        ComparableUtil sort = new ComparableUtil();
                        Collections.sort(mCSTPReportList.getData(), sort);
                        //数据添加至显示集合
                        int netDataSize = mCSTPReportList.getData().size();//网络加载数据长度
                        int viewDataSize = dataBeenList.size();//显示数据长度
                        for (int i = 0; i < mCSTPReportList.getData().size(); i++) {
                            dataBeenList.add(mCSTPReportList.getData().get(i));
                            Log.e("ReportsActivity", "违法时间：" + dataBeenList.get(i).getWFSJ());
                            mCSTPReportAdapter.notifyDataSetChanged();
                        }
                        //缓存集合
                        if (isFromCache) {
                            //缓存了
                            Log.e("ReportsActivity", "isFromCache:" + isFromCache);
                        } else {
                            //有网不需要缓存
                            Log.e("ReportsActivity", "isFromCache:" + isFromCache);
                        }

                        //关掉下拉刷新
                        swipeRefreshLayout.setRefreshing(false);
                        Log.e("ReportsActivity", "获取集合长度：" + mCSTPReportList.getData().size());
                        Log.e("ReportActivity", s + "");

                        //网络数据和线上数据不相同时加载成功
                        if (netDataSize != viewDataSize) {
                            Toast.makeText(ReportsActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ReportsActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                        dialog.dismiss();
                        //tvRecordReports.setText("");
                        Log.v("ReportsActivity", s + "");
                        super.onAfter(isFromCache, s, call, response, e);
                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        //获取数据库数据
                     *//*   DaoGenerator daoGenerator = new DaoGenerator(ReportsActivity.this);
                        cstpReportList = daoGenerator.search();
                        mCSTPReportAdapter.notifyDataSetChanged();*//*
                        dialog.dismiss();
                        super.onError(isFromCache, call, response, e);

                    }
                });

    }*/


     /*  *//* //刷新加载
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_cont);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //tv.setText("正在刷新");
                // postData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //tv.setText("刷新完成");
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(ReportsActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    }
                }, 6000);
            }
        });


        dialog = new LoadDialog(ReportsActivity.this, "", "正在获取数据...");
        dialog.show();
*//*

       *//* dataBeenList = new ArrayList<>();
        mCSTPReportAdapter = new CSTPReportAdapter(this, dataBeenList);
       *//*
        cstpWfjbs = new ArrayList<>();
        wfjbAdapter = new WfjbAdapter(this, cstpWfjbs);
        rvItemReports = (RecyclerView) findViewById(R.id.rv_item_reports);
        rvItemReports.setLayoutManager(new LinearLayoutManager(this));
        rvItemReports.setAdapter(wfjbAdapter);
        //rvItemReports.setAdapter(mCSTPReportAdapter);
    }

    private void initDate() {
        //postData();
        SQLiteData();
    }

    private void SQLiteData() {
        mHelper = new DaoMaster.DevOpenHelper(this, "test-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
        // 得到 Dao 对象，数据库的 CRUD 操作都是通过此对象来进行
        mWfjbDao = mDaoSession.getCstp_wfjbDao();

        cursor = db.query(mWfjbDao.getTablename(), mWfjbDao.getAllColumns(), null, null, null, null, null);
        // 通过 PersonDao 的静态内部类得到字段所对应的 列名

        // 通过构建 QueryBuilder 来实现查询功能
        QueryBuilder<cstp_wfjb> queryBuilder = mWfjbDao.queryBuilder().where(cstp_wfjbDao.Properties.Mark.eq("mark"));
        // .list() 方法会返回实体类集合
        Log.v("ReportsActivity", "SQLiteData");
        Log.v("ReportsActivity", "SQLiteData "+queryBuilder.list().size());


        for (int i = 0; i < queryBuilder.list().size(); i++) {
            CstpWfjb cstpWfjb = new CstpWfjb();
            cstpWfjb.setId(queryBuilder.list().get(i).getId());
            cstpWfjb.setMark("mark");
            cstpWfjb.setWfsj(queryBuilder.list().get(i).getWfsj());
            cstpWfjb.setWfld(queryBuilder.list().get(i).getWfld());
            cstpWfjb.setWfch(queryBuilder.list().get(i).getWfch());
            cstpWfjb.setQksm(queryBuilder.list().get(i).getQksm());
            cstpWfjb.setJbr(queryBuilder.list().get(i).getJbr());
            cstpWfjb.setSfhm(queryBuilder.list().get(i).getSfhm());
            cstpWfjb.setLxdh(queryBuilder.list().get(i).getLxdh());
            cstpWfjb.setZqdw(queryBuilder.list().get(i).getZqdw());
            cstpWfjb.setImage1_path(queryBuilder.list().get(i).getImage1_path());
            cstpWfjb.setImage2_path(queryBuilder.list().get(i).getImage2_path());
            cstpWfjb.setImage3_path(queryBuilder.list().get(i).getImage3_path());
            cstpWfjbs.add(cstpWfjb);
            Log.v("ReportsActivity", cstpWfjb.getJbr());
            wfjbAdapter.notifyDataSetChanged();
        }


    }*/


    /**
     * 刷新
     */
    private void refresh() {
        finish();
        Intent intent = new Intent(this, ReportsActivity.class);
        startActivity(intent);
    }

}
//D:\java\jdk1.8.0_65\bin