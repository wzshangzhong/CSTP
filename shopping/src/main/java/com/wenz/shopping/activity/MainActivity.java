package com.wenz.shopping.activity;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.wenz.shopping.App;
import com.wenz.shopping.R;
import com.wenz.shopping.adapter.ShopAdapter;
import com.wenz.shopping.pojo.ShopItem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ShopAdapter mAdapter;
    private ArrayList<ShopItem> shopItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();


      /*  for (int i = 0; i < 10; i++) {

            shopItemList.add(new ShopItem(i, (int) (Math.random() * i),
                    String.format("%d号店", i + 1), "小炒肉，香干回锅肉...",
                    (int) (Math.random() * i * 100), (int) (Math.random() * i * 100),
                    "0731-73990928", "平川路", 113d, 29d,
                    ContextCompat.getDrawable(this, R.mipmap.ic_launcher)));
        }*/


    }

    private void initView() {
        setTitle("餐馆");
        mListView = (ListView) findViewById(R.id.list_view);
        shopItemList = new ArrayList<>();

    }

    private void initData() {
        NetData();
    }

    private void NetData() {

        OkHttpUtils
                .post(App.BASE_URL+"/api/shop/post")
                .postJson("{'code':'1'}")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(boolean isFromCache,
                                           String s, Request request,
                                           @Nullable Response response) {


                        Log.v("MainActivity is json:", s);
                        Log.v("MainActivity request:", request.toString());
                        Log.v("MainActivity response:", response.toString());
                        Log.v("MainActivity isFromCache:", isFromCache + "");


                        Gson gson = new Gson();
                        shopItemList = gson.fromJson(s,
                                new TypeToken<ArrayList<ShopItem>>() {
                                }.getType());

                        Log.v("MainActivity is json:", s);
                        Log.v("MainActivity shopItem:", shopItemList.get(0).getName());
                        mAdapter = new ShopAdapter(MainActivity.this, shopItemList);
                        mListView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(boolean isFromCache,
                                        Call call, @Nullable Response response,
                                        @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.v("MainActivity is Error", e.toString());
                        Log.v("MainActivity is Error", response.toString());
                    }
                });
    }
}
