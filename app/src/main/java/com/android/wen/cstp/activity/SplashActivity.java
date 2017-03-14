package com.android.wen.cstp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.util.UserUtils;

public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.activity_splash, null);
        setContentView(view);

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
                        Class<?> cls = null;
                        // 判断用户是否已成功登录
                        if (UserUtils.getUser(SplashActivity.this).getYhm().length() > 0) {
                            cls = MainActivity.class; // 已成功登录
                        } else {
                            cls = LoginActivity.class; // 未登录
                        }
                        Intent intent = new Intent(SplashActivity.this, cls);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }}, SPLASH_DISPLAY_LENGHT);

    }

}

