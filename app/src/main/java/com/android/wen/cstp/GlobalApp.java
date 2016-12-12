package com.android.wen.cstp;

import android.app.Application;
import android.os.Environment;

import com.android.wen.cstp.util.DaoGenerator;
import com.lzy.okhttputils.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/11/2.
 */
public class GlobalApp extends Application {
    //http://192.168.1.102/ 本机电脑测试
    public static final String BASE_URL = "http://192.168.1.102:9068/CarInfo/";
   // public static final String BASE_URL = "http://211.149.209:80/CarInfo/";

    //http://192.168.1.102:9068/CarInfo/getCarInfo
    //http://192.168.1.102:9068/CarInfo/
   // public static final String BASE_URL_UP = "http://192.168.1.102:9068/CarInfo/";

    //查询所有数据
    //public static final String BASE_URL = "http://192.168.1.111:9068/CarInfo/";
    //上传数据
    //public static final String BASE_URL_UP = "http://211.149.209.80:8011/CarInfo/";

    public GlobalApp() {
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
                .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                .readTimeout(5000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.init(this);


    }

}


