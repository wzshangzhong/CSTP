package com.wenz.shopping.pojo;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GoodsItem {

    public int id;//id
    public int typeId;//商品种类id
    public int rating;//评分
    public String name;//商品名字
    public String typeName;//种类名字
    public double price;//商品价格
    public int count;//总数
    public Image pic;//图片

    public GoodsItem(int id, double price, String name, int typeId, String typeName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        rating = new Random().nextInt(5) + 1;
    }

    private static ArrayList<GoodsItem> goodsList;
    private static ArrayList<GoodsItem> typeList;

    private static void initData() {
        goodsList = new ArrayList<>();
        typeList = new ArrayList<>();
        GoodsItem item = null;
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 10; j++) {
                item = new GoodsItem(100 * i + j, Math.random() * 100, "商品" + (100 * i + j), i, "种类" + i);
                goodsList.add(item);
            }
            typeList.add(item);
        }
    }

    public static ArrayList<GoodsItem> getGoodsList() {
        if (goodsList == null) {
            initData();
        }
        return goodsList;
    }

    public static ArrayList<GoodsItem> getTypeList() {
        if (typeList == null) {
            initData();
        }
        return typeList;
    }
}
