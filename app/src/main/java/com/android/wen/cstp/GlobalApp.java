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
    public static final String USER_URL = "http://192.168.0.113:8082/api/user/post";
    public static final String WFJB_URL = "http://192.168.0.113:8082/api/wfxx/post";
    public static final String WFJBCX_URL = "http://192.168.0.113:8082/api/wfjbcx/post";
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
    public static String[] wfxws = new String[]{
            "故意遮挡机动车号牌", "故意污损机动车号牌", "使用其他机动车号牌",
            "使用伪造、变造机动车号牌", "无证或驾驶证被扣留期间驾驶",
            "未按规定安装机动车号牌", "工程车装载超出栏版", "工程车闯红灯",
            "工程车逆向行驶"};
    public static String[] xsfxs = new String[]{
            "东到西", "东到南", "东到北",
            "西到东", "西到南", "西到北",
            "南到东", "南到西", "南到北",
            "北到东", "北到西", "北到南",};
    public static String[] cllxs = new String[]{
            "小型汽车", "大型汽车", "普通摩托车",
            "挂车", "低速车", "轻便摩托车",
            "教练汽车", "教练摩托车"};
    public static String[] csyss = new String[]{
            "灰色", "黄色", "粉色",
            "紫色", "绿色", "红色",
            "蓝色", "棕色", "黑色",
            "白色", "橙色", "不确定",};
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


