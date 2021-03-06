package com.wenz.shopping.activity;


import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.wenz.shopping.view.DividerDecoration;
import com.wenz.shopping.R;
import com.wenz.shopping.adapter.GoodsAdapter;
import com.wenz.shopping.adapter.SelectAdapter;
import com.wenz.shopping.adapter.TypeAdapter;
import com.wenz.shopping.pojo.GoodsItem;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgCart;//购物车
    private ViewGroup anim_mask_layout;
    private RecyclerView rvType, rvSelected;
    private TextView tvCount, tvCost, tvSubmit, tvTips;
    private BottomSheetLayout bottomSheetLayout;
    private View bottomSheet;
    private StickyListHeadersListView listView;
    private ArrayList<GoodsItem> goodsItems, dataList, typeList;
    private SparseArray<GoodsItem> selectedList;
    private SparseIntArray groupSelect;
    private GoodsAdapter myAdapter;
    private SelectAdapter selectAdapter;
    private TypeAdapter typeAdapter;
    private NumberFormat nf;
    private Handler mHanlder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        initData();
        initView();
    }

    private void initData() {

        Bundle bundle = getIntent().getExtras();
        int shopId = bundle.getInt("ShopId");
        String name = bundle.getString("name");
        Log.v("ShoppingCartActivity is name",name);
        goodsItems =  bundle.getParcelableArrayList("goodsItems");
        NetData(shopId);

        setTitle(name);
        nf = NumberFormat.getCurrencyInstance();//输出货币类型的值
        nf.setMaximumFractionDigits(2); //设置最大分数位为2
        mHanlder = new Handler(getMainLooper()); //getMainLooper()返回应用主线程中的 Looper
       /* dataList = GoodsItem.getGoodsList();//设置具体商品数据
        typeList = GoodsItem.getTypeList();//设置种类1种类2的--左边目录*/
        selectedList = new SparseArray<>();//SparseArray：更高效的数据集合来替代HashMap在android中的使用
        groupSelect = new SparseIntArray();
        //SparseArra和SparseIntArray解释
        // http://www.voidcn.com/blog/stzy00/article/p-65828.html
    }

    private void NetData(int shopId) {


        dataList = new ArrayList<>();
        typeList = new ArrayList<>();
        //所有数据添加至dataList 不同类型数据添加至typeList
        int count = goodsItems.size();
        for (int i = 0; i < count; i++) {
            dataList.add(goodsItems.get(i));

            if (i != 0 && goodsItems.get(i).typeId != goodsItems.get(i - 1).typeId) {
                typeList.add(goodsItems.get(i));
            } else if(i==0) {
                typeList.add(goodsItems.get(0));
            }
        }
       /* for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 10; j++) {
                item = new GoodsItem(100 * i + j, Math.random() * 100, "商品" + (100 * i + j), i, "种类" + i);
                dataList.add(item);
            }
            typeList.add(item);
        }*/
    }

    private void initView() {
        tvCount = (TextView) findViewById(R.id.tvCount);//购物车上显示总数
        tvCost = (TextView) findViewById(R.id.tvCost);//显示共多少钱
        tvTips = (TextView) findViewById(R.id.tvTips);//显示多少钱才送
        tvSubmit = (TextView) findViewById(R.id.tvSubmit);//结算
        rvType = (RecyclerView) findViewById(R.id.typeRecyclerView);//左边目录

        imgCart = (ImageView) findViewById(R.id.imgCart);//购物车
        anim_mask_layout = (RelativeLayout) findViewById(R.id.containerLayout);//动画掩码？不知道什么鬼
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomSheetLayout);//底部表

        listView = (StickyListHeadersListView) findViewById(R.id.itemListView);//右边食品表

        rvType.setLayoutManager(new LinearLayoutManager(this));//设置左边目录为线性布局
        typeAdapter = new TypeAdapter(this, typeList);//设置左边种类数据
        rvType.setAdapter(typeAdapter);
        rvType.addItemDecoration(new DividerDecoration(this));//动画

        myAdapter = new GoodsAdapter(dataList, this);//右边数据
        listView.setAdapter(myAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //listView滑动状态改变时
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                GoodsItem item = dataList.get(firstVisibleItem);
                if (typeAdapter.selectTypeId != item.typeId) {
                    typeAdapter.selectTypeId = item.typeId;
                    typeAdapter.notifyDataSetChanged();
                    rvType.smoothScrollToPosition(getSelectedGroupPosition(item.typeId));
                }
            }
        });

    }


    public void playAnimation(int[] start_location) {
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.button_add);
        setAnim(img, start_location);
    }

    private Animation createAnim(int startX, int startY) {
        int[] des = new int[2];
        imgCart.getLocationInWindow(des);

        AnimationSet set = new AnimationSet(false);

        Animation translationX = new TranslateAnimation(0, des[0] - startX, 0, 0);
        translationX.setInterpolator(new LinearInterpolator());
        Animation translationY = new TranslateAnimation(0, 0, 0, des[1] - startY);
        translationY.setInterpolator(new AccelerateInterpolator());
        Animation alpha = new AlphaAnimation(1, 0.5f);
        set.addAnimation(translationX);
        set.addAnimation(translationY);
        set.addAnimation(alpha);
        set.setDuration(500);

        return set;
    }

    private void addViewToAnimLayout(final ViewGroup vg, final View view,
                                     int[] location) {

        int x = location[0];
        int y = location[1];
        int[] loc = new int[2];
        vg.getLocationInWindow(loc);
        view.setX(x);
        view.setY(y - loc[1]);
        vg.addView(view);
    }

    private void setAnim(final View v, int[] start_location) {

        addViewToAnimLayout(anim_mask_layout, v, start_location);
        Animation set = createAnim(start_location[0], start_location[1]);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(final Animation animation) {
                mHanlder.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_mask_layout.removeView(v);
                    }
                }, 100);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v.startAnimation(set);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom:
                //点击购物车的时候显示？
                showBottomSheet();
                break;
            case R.id.clear://清空
                clearCart();
                break;
            case R.id.tvSubmit://结算
                Toast.makeText(ShoppingCartActivity.this, "结算", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    //添加商品
    public void add(GoodsItem item, boolean refreshGoodList) {

        int groupCount = groupSelect.get(item.typeId);
        if (groupCount == 0) {
            groupSelect.append(item.typeId, 1);
        } else {
            groupSelect.append(item.typeId, ++groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if (temp == null) {
            item.count = 1;
            selectedList.append(item.id, item);
        } else {
            temp.count++;
        }
        update(refreshGoodList);
    }

    //移除商品
    public void remove(GoodsItem item, boolean refreshGoodList) {

        int groupCount = groupSelect.get(item.typeId);
        if (groupCount == 1) {
            groupSelect.delete(item.typeId);
        } else if (groupCount > 1) {
            groupSelect.append(item.typeId, --groupCount);
        }

        GoodsItem temp = selectedList.get(item.id);
        if (temp != null) {
            if (temp.count < 2) {
                selectedList.remove(item.id);
            } else {
                item.count--;
            }
        }
        update(refreshGoodList);
    }

    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList) {
        int size = selectedList.size();
        int count = 0;
        double cost = 0;
        for (int i = 0; i < size; i++) {
            GoodsItem item = selectedList.valueAt(i);
            count += item.count;
            cost += item.count * item.price;
        }

        if (count < 1) {
            tvCount.setVisibility(View.GONE);
        } else {
            tvCount.setVisibility(View.VISIBLE);
        }

        tvCount.setText(String.valueOf(count));

        if (cost > 99.99) {
            tvTips.setVisibility(View.GONE);
            tvSubmit.setVisibility(View.VISIBLE);
        } else {
            tvSubmit.setVisibility(View.GONE);
            tvTips.setVisibility(View.VISIBLE);
        }

        tvCost.setText(nf.format(cost));

        if (myAdapter != null && refreshGoodList) {
            myAdapter.notifyDataSetChanged();
        }
        if (selectAdapter != null) {
            selectAdapter.notifyDataSetChanged();
        }
        if (typeAdapter != null) {
            typeAdapter.notifyDataSetChanged();
        }
        if (bottomSheetLayout.isSheetShowing() && selectedList.size() < 1) {
            bottomSheetLayout.dismissSheet();
        }
    }

    //清空购物车
    public void clearCart() {
        selectedList.clear();
        groupSelect.clear();
        update(true);

    }

    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(int id) {
        GoodsItem temp = selectedList.get(id);
        if (temp == null) {
            return 0;
        }
        return temp.count;
    }

    //根据类别Id获取属于当前类别的数量
    public int getSelectedGroupCountByTypeId(int typeId) {
        return groupSelect.get(typeId);
    }

    //根据类别id获取分类的Position 用于滚动左侧的类别列表
    public int getSelectedGroupPosition(int typeId) {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeId == typeList.get(i).typeId) {
                return i;
            }
        }
        return 0;
    }

    public void onTypeClicked(int typeId) {
        listView.setSelection(getSelectedPosition(typeId));
    }

    private int getSelectedPosition(int typeId) {
        int position = 0;
        for (int i = 0; i < dataList.size(); i++) {
            if (dataList.get(i).typeId == typeId) {
                position = i;
                break;
            }
        }
        return position;
    }

    private View createBottomSheetView() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet, (ViewGroup) getWindow().getDecorView(), false);
        rvSelected = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        rvSelected.setLayoutManager(new LinearLayoutManager(this));
        TextView clear = (TextView) view.findViewById(R.id.clear);
        clear.setOnClickListener(this);
        selectAdapter = new SelectAdapter(this, selectedList);
        rvSelected.setAdapter(selectAdapter);
        return view;
    }

    private void showBottomSheet() {
        if (bottomSheet == null) {
            bottomSheet = createBottomSheetView();
        }
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        } else {
            if (selectedList.size() != 0) {
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }
}
