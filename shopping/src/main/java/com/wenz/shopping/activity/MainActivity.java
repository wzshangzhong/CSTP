package com.wenz.shopping.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wenz.shopping.R;
import com.wenz.shopping.adapter.ShopAdapter;
import com.wenz.shopping.pojo.ShopItem;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<ShopItem> shopItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("餐馆");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        shopItemList = new ArrayList<>();
        for (int i=0; i < 10; i++) {

            shopItemList.add(new ShopItem(i,(int) (Math.random()*i),
                    String.format("%d号店",i+1),"小炒肉，香干回锅肉...",
                    (int)(Math.random()*i*100),(int)(Math.random()*i*100),
                    "0731-73990928","平川路",113d,29d,
                    ContextCompat.getDrawable(this,R.mipmap.ic_launcher)));
        }
        // 设置ItemAnimator
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 设置固定大小
        mRecyclerView.setHasFixedSize(true);
        //mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于gridview
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShopAdapter(this, shopItemList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }
}
