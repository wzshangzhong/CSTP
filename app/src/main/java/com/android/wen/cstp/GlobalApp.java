package com.android.wen.cstp;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.android.wen.cstp.pojo.AppInfo;
import com.lzy.okhttputils.OkHttpUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/11/2.
 */
public class GlobalApp extends Application {
    //http://192.168.1.102/ 本机电脑测试
    //public static final String BASE_URL = "http://192.168.1.102:9068/CarInfo/";
    public static final String BASE_URL = "http://192.168.0.113:8082/api/user/post";
    public static final String FLIE_URL = "http://192.168.0.113:8082/api/downloadapk/GetFileFromWebApi";
    // public static final String BASE_URL = "http://211.149.209:80/CarInfo/";

    //http://192.168.1.102:9068/CarInfo/getCarInfo
    //http://192.168.1.102:9068/CarInfo/
    // public static final String BASE_URL_UP = "http://192.168.1.102:9068/CarInfo/";

    public static final String[] fzjgs = new String[]{
            "湘", "粤", "桂", "琼", "川", "贵", "云",
            "渝", "藏", "陕", "甘", "青", "宁", "新",
            "京", "津", "冀", "晋", "蒙", "辽", "吉",
            "黑", "沪", "苏", "浙", "皖", "闽", "赣",
            "鲁", "豫", "鄂"};
    public static String[] hpzls = new String[]{
            "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z"};
    public static String[] units = new String[]{
            "岳麓区交警大队", "芙蓉区交警大队", "天心区交警大队", "开福区交警大队", "雨花区交警大队", "长沙县交警大队",
            "望城区交警大队", "浏阳县交警大队", "宁乡县交警大队", "长沙市交警大队", "其他"
    };
    public static String[] sexs = new String[]{"男", "女", "其他"};
    public static String userPro = "12345";//用户协议



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

        android_info();
    }

    public void android_info() {
        AppInfo appInfo = new AppInfo();
        // appInfo.setPSN("");

        TelephonyManager tm = (TelephonyManager) this
                .getSystemService(Context.TELEPHONY_SERVICE);//
        String str = "";
        str += "DeviceId(IMEI) = " + tm.getDeviceId() + "/n";
        str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion()
                + "/n";
        str += "Line1Number = " + tm.getLine1Number() + "/n";
        str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "/n";
        str += "NetworkOperator = " + tm.getNetworkOperator() + "/n";
        str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "/n";
        str += "NetworkType = " + tm.getNetworkType() + "/n";
        str += "honeType = " + tm.getPhoneType() + "/n";
        str += "SimCountryIso = " + tm.getSimCountryIso() + "/n";
        str += "SimOperator = " + tm.getSimOperator() + "/n";
        str += "SimOperatorName = " + tm.getSimOperatorName() + "/n";
        str += "SimSerialNumber = " + tm.getSimSerialNumber() + "/n";
        str += "SimState = " + tm.getSimState() + "/n";
        str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "/n";
        str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "/n";
        //textView01.setText(str);
    }

}


