package com.android.wen.cstp.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.util.UserUtils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒
    private Class<?> cls = null;
    private int code;
    private String fileApkPath;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.activity_splash, null);
        setContentView(view);

        Log.v("SplashActivity ", "asfasfasdfasdfafda");

        // 渐变动画 (从模糊至清晰)
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        // 动画持续时长
        animation.setDuration(1500);
        // 启动动画
        view.setAnimation(animation);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        // 判断用户是否已成功登录
                        if (UserUtils.getUser(SplashActivity.this).getYhm().length() > 0) {
                            cls = MainActivity.class; // 已成功登录
                        } else {
                            cls = LoginActivity.class; // 未登录
                        }
                        //获取版本号
                        PackageManager packageManager = getPackageManager();
                        // getPackageName()是你当前类的包名，0代表是获取版本信息
                        PackageInfo packInfo = null;
                        try {
                            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }

                        int versionCode = packInfo.versionCode;

                        OkHttpUtils.post(GlobalApp.USER_URL)
                                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                                .postJson("{'code':'7','msg':{'version':'" + versionCode + "'}}")
                                .execute(new StringCallback() {
                                    @Override
                                    public void onResponse(boolean isFromCache, String s, Request request,
                                                           @Nullable Response response) {
                                        try {
                                            JSONObject jo = new JSONObject(s);
                                            code = Integer.valueOf(jo.getString("code"));

                                            Log.v("SplashActivity is code:", " " + code);
                                            Log.v("SplashActivity is a:", " " + s);
                                            if (code > 0) {
                                                UpDataApk();
                                            } else {
                                                Log.v("SplashActivity ", "asfasfasdfasdfafda");
                                                Intent intent = new Intent(SplashActivity.this, cls);
                                                startActivity(intent);
                                                SplashActivity.this.finish();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                    private void UpDataApk() {
                        ShowDialog();
                        OkHttpUtils
                                .get(GlobalApp.FLIE_URL)
                                .execute(new FileCallback(Environment.getExternalStorageDirectory() + "/CSTP", "CSTP.apk") {
                                    @Override
                                    public void onResponse(boolean isFromCache, File file, Request request,
                                                           @Nullable Response response) {
                                        fileApkPath = file.getPath();
                                        Log.v("SplashActivity is FilePath", file.getPath());
                                        Log.v("SplashActivity is F ileName", file.getName());
                                        Log.v("SplashActivity is FileAbsolutePath", file.getAbsolutePath());

                                        Log.v("SplashActivity is request", request.toString());
                                        Log.v("SplashActivity is response", response.toString());

                                    }

                                    @Override
                                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                                        super.downloadProgress(currentSize, totalSize, progress, networkSpeed);

                                        mProgressDialog.setProgress((int) (100 * progress));
                                        Log.v("SplashActivity is progress", String.valueOf(100 * progress));
                                        Log.v("SplashActivity is currentSize", String.valueOf(100 * currentSize));
                                        Log.v("SplashActivity is totalSize", String.valueOf(100 * totalSize));
                                        Log.v("SplashActivity is networkSpeed", String.valueOf(100 * networkSpeed));

                                    }

                                    @Override
                                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                                        Log.v("SplashActivity", "onError :" + e.getMessage());
                                        super.onError(isFromCache, call, response, e);
                                    }
                                });
                    }
                }, SPLASH_DISPLAY_LENGHT);
        Log.v("SplashActivity ", "asfasfasdfasdfafda");
    }

    private void ShowDialog() {
        mProgressDialog = new ProgressDialog(SplashActivity.this);
        // 设置进度条风格，风格为水平
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 标题
        mProgressDialog.setTitle("下载安装文件");
        // 设置ProgressDialog 的进度条是否不明确
        //mProgressDialog.setIndeterminate(false);

        // 设置ProgressDialog 是否可以按退回按键取消
        mProgressDialog.setCancelable(false);
        //mProgressDialog.setProgress(100);
        // 设置ProgressDialog 的一个Button
        mProgressDialog.setButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                //点击“确定按钮”安装
                if (mProgressDialog.getProgress() == 100) {
                    File file = new File(fileApkPath);
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(file);
                        Log.v("SplashActivity FileSize:", fis.available() + "");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    installApk(file);
                    dialog.cancel();
                }
            }
        });
        // 让ProgressDialog显示
        mProgressDialog.show();
    }

    //安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

}

