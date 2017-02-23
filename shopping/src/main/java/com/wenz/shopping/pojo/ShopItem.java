package com.wenz.shopping.pojo;


import android.media.Image;

import com.wenz.shopping.R;
import com.wenz.shopping.activity.MainActivity;


/**
 * Created by Administrator on 2017/2/23.
 */

public class ShopItem {
    public int id;//id
    public int rating;//评分
    public String name;//店名
    public String typeName;//商品标签
    public double price;//人均消费价格
    public int count;//人气
    public int shopAv;
    public String phone;//电话
    public String location;//位置标签
    public double longitude;//经度
    public double latitude;//纬度
    public Image pic;//图片

    public ShopItem(int id, int rating, String name, String typeName, double price, int count, int shopAv, String phone, String location, double longitude, double latitude, Image pic) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.typeName = typeName;
        this.price = price;
        this.count = count;
        this.shopAv = shopAv;
        this.phone = phone;
        this.location = location;
        this.longitude = longitude;
        this.latitude = latitude;
        this.pic = pic;
    }

    public ShopItem() {
    }

    public int getId() {
        return id;
    }

    public void setShopAv(int shopAv) {
        this.shopAv = shopAv;
    }

    public int getShopAv() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Image getPic() {
        return pic;
    }

    public void setPic(Image pic) {
        this.pic = pic;
    }
}
