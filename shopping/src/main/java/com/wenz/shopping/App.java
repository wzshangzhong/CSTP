package com.wenz.shopping;

import android.app.Application;
import android.os.Environment;

import com.lzy.okhttputils.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2017/2/27.
 */
public class App extends Application {
    //内网服务器测试
    public static final String BASE_URL = "http://192.168.0.113:8082/";
   // public static  String json = "[{'id':2,'rating':3,'name':'商品1','typeName':'种类1','count':10,'priAv':20,'phone':'0731-83990928','location':'文轩路','longitude':113,'latitude':29,'pic':'APP_SHOP/02_24.png'},{'id':3,'rating':3,'name':'商品1','typeName':'种类1','count':10,'priAv':20,'phone':'0731-83990928','location':'文轩路','longitude':113,'latitude':29,'pic':'APP_SHOP/02_24.png'},{'id':4,'rating':3,'name':'商品1','typeName':'种类1','count':10,'priAv':20,'phone':'0731-83990928','location':'文轩路','longitude':113,'latitude':29,'pic':'02_24.png'}]";


    public App() {
        //创建app目录
        File file = new File(Environment.getExternalStorageDirectory() + "/CSTP");
        if (!file.exists()) {
            file.mkdir();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor('TAG'))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.init(this);


    }
}