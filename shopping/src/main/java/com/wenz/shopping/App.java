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
    public static final String BASE_URL = "http://192.168.0.113:8082/api/";


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
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.init(this);


    }
}