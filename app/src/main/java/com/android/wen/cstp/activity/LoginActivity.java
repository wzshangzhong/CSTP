package com.android.wen.cstp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.pojo.User;
import com.android.wen.cstp.util.UserUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.et_user_name)
    EditText etUserName;
    @Bind(R.id.et_user_psaaword)
    EditText etUserPsaaword;
    private User user;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        etUserName.setText(UserUtils.getUser(this).getYhm());//默认用户名为缓存用户名
    }

    @OnClick({R.id.login, R.id.login_register, R.id.login_forget_pss})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:

                username = etUserName.getText().toString();
                password = etUserPsaaword.getText().toString();

                if (TextUtils.isEmpty(username) || password.trim().length() == 0) {
                    Toast.makeText(this, "用户名或密码不能为空.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //获取网络数据，首选项缓存，转调至主活动
                NetPost();

                break;
            case R.id.login_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login_forget_pss:
                //忘记密码
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;

        }
    }

    private void NetPost() {
        OkHttpUtils
                .post(GlobalApp.USER_URL)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                 .postJson("{'code':'2','msg':{'yhm':'" + username + "','password':'" + password + "'}}")
                //.postJson("{'code':'2','msg':{'yhm':'1111','password':'1234'}}")
                //{'code':'2','msg':{'yhm':'1111','password':'1234'}}
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        Gson gson = new Gson();
                        user = gson.fromJson(s,
                                new TypeToken<User>() {
                                }.getType());
                        Log.v("LoginActivity is onResponse:", user.toString());

                        try {
                            JSONObject jo = new JSONObject(s);
                            // Log.v("LoginActivity is onResponse:","yhm:"+jo.getString("yhm"));

                            if (!jo.getString("yhm").isEmpty()) {
                                // 1.保存用户设置到 选项存储
                                UserUtils.save(LoginActivity.this, user);
                                // 2.跳转至主活动 (MainActivity)
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                LoginActivity.this.finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "错误的用户名", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.v("LoginActivity is onResponse:", s);
                        Log.v("LoginActivity is onResponse:", request.toString());
                        Log.v("LoginActivity is onResponse:", response.toString());

                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Log.v("LoginActivity is error:", response.toString());

                    }
                });

    }
}
