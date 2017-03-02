package com.wenz.shopping.pojo;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class GoodsItem implements Parcelable {

    public int id;//id
    public int typeId;//商品种类id
    public int rating;//评分
    public String name;//商品名字
    public String typeName;//种类名字
    public double price;//商品价格
    public int count;//总数
    public String pic;//图片

   /* public GoodsItem(int id, double price, String name, int typeId, String typeName) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.typeId = typeId;
        this.typeName = typeName;
        rating = new Random().nextInt(5) + 1;
    }

    public GoodsItem() {
    }*/

    public static final Parcelable.Creator<GoodsItem> CREATOR = new Creator() {

        @Override
        public GoodsItem createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            GoodsItem goodsItem = new GoodsItem();
            goodsItem.setId(source.readInt());
            goodsItem.setTypeId(source.readInt());
            goodsItem.setRating(source.readInt());
            goodsItem.setName(source.readString());
            goodsItem.setTypeName(source.readString());
            goodsItem.setPrice(source.readDouble());
            goodsItem.setCount(source.readInt());
            goodsItem.setPic(source.readString());

            return goodsItem;
        }

        @Override
        public GoodsItem[] newArray(int size) {
            // TODO Auto-generated method stub
            return new GoodsItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        // 1.必须按成员变量声明的顺序封装数据，不然会出现获取数据出错
        // 2.序列化对象
        parcel.writeInt(id);
        parcel.writeInt(typeId);
        parcel.writeInt(rating);
        parcel.writeString(name);
        parcel.writeString(typeName);
        parcel.writeDouble(price);
        parcel.writeInt(count);
        parcel.writeString(pic);

    }


}
