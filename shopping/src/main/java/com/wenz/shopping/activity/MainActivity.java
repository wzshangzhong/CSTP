package com.wenz.shopping.activity;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import com.wenz.shopping.App;
import com.wenz.shopping.R;
import com.wenz.shopping.adapter.ShopAdapter;
import com.wenz.shopping.pojo.GoodsItem;
import com.wenz.shopping.pojo.ShopItem;
import com.wenz.shopping.util.NetWorkTool;
import com.wenz.shopping.util.PermissionsChecker;

import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ShopAdapter mAdapter;
    private ArrayList<ShopItem> shopItemList;
    private ArrayList<GoodsItem> goodsItems;
    private ShopItem shopItem;

    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_PHONE_STATE

    };
    private PermissionsChecker mPermissionsChecker; // 权限检测器


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mPermissionsChecker = new PermissionsChecker(this);
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

    @Override
    protected void onResume() {
        super.onResume();

        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }

    private void initView() {

        setTitle("餐馆");
        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                shopItem = shopItemList.get(i);

                //获取数据
                if (NetWorkTool.isNetworkAvailable(MainActivity.this))
                    NetGoodsData();
                else {
                    Toast.makeText(MainActivity.this, "网络未连接，请重新接入网络", Toast.LENGTH_SHORT).show();
                }
                Log.v("MainActivity is item i:", i + "");
            }
        });
        shopItemList = new ArrayList<>();

    }

    private void initData() {
        if (NetWorkTool.isNetworkAvailable(MainActivity.this))
            NetData();
        else
            Toast.makeText(MainActivity.this, "网络未连接，请重新接入网络", Toast.LENGTH_SHORT).show();
    }

    private void NetData() {

        OkHttpUtils
                .post(App.BASE_URL + "/api/shop/post")
                .cacheKey("app_shop")
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

                        mAdapter = new ShopAdapter(MainActivity.this, shopItemList);
                        mListView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onError(boolean isFromCache,
                                        Call call, @Nullable Response response,
                                        @Nullable Exception e) {
                        Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_LONG).show();
                        Log.v("MainActivity is Error", e.toString());
                        //Log.v("MainActivity is Error", response.toString());
                    }
                });
    }

    private void NetGoodsData() {
        OkHttpUtils
                .post(App.BASE_URL + "/api/shop/post")
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .cacheKey("app_goods")
                .postJson("{'code':'2','msg':'" + shopItem.getId() + "'}")
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        Log.v("ShopAdapter is Goods:", s);
                        Gson gson = new Gson();
                        goodsItems = gson.fromJson(s,
                                new TypeToken<ArrayList<GoodsItem>>() {
                                }.getType());
                    }

                    @Override
                    public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onAfter(isFromCache, s, call, response, e);

                        Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("goodsItems", goodsItems);
                        bundle.putInt("ShopId", shopItem.getId());
                        bundle.putString("name", shopItem.getName());
                        Log.v("ShopAdapter is id", shopItem.getId() + "");
                        Log.v("ShopAdapter is name", shopItem.getName());
                        intent.putExtras(bundle);
                        Log.v("MainActivity", goodsItems.size() + "");
                        startActivity(intent);
                    }
                });
    }
}
