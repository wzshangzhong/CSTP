package com.android.wen.cstp.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.pojo.CSTPReportList;
import com.android.wen.cstp.pojo.Version;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.callback.StringCallback;

import java.io.File;
import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

public class VersionActivity extends AppCompatActivity {
    private Version version;
    private String versionName;
    private File apkFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        init();
        //获取app版本信息

        OkHttpUtils.post(GlobalApp.BASE_URL + "appUrl").execute(new StringCallback() {
            @Override
            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                Gson gson = new Gson();
                version = gson.fromJson(s, Version.class);
            }
        });

    }

    private void init() {
        ImageButton topBack = (ImageButton) findViewById(R.id.top_back);
        TextView topTitle = (TextView) findViewById(R.id.top_title);
        topTitle.setText("更新程序");
        topBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        PackageManager pm = getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(this.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionName = pi.versionName;
        Button userHelp = (Button) findViewById(R.id.user_help);
        userHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (version.getCode() == versionName) {
                    Toast.makeText(VersionActivity.this, "已经是最新版本", Toast.LENGTH_LONG).show();
                } else {
                    OkHttpUtils.post("").execute(new FileCallback("CSTP.apk") {
                        @Override
                        public void onResponse(boolean isFromCache, File file, Request request, @Nullable Response response) {
                            apkFile=file;
                        }
                    });
                    installApk(apkFile);
                }
            }
        });

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
