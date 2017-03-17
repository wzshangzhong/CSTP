package com.android.wen.cstp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;

public class ResetPasswordActivity extends BaseActivity {
    final String TAG = "ResetPasswordActivity";

    @Bind(R.id.btn_code)
    Button btnCode;
    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.et_mobile_username)
    EditText etMobileUsername;
    @Bind(R.id.et_password_reset)
    EditText etPasswordReset;
    @Bind(R.id.et_password_check)
    EditText etPasswords;
    String btnTime = "";

    //线程msgKey值
    private static final int msgKey1 = 1;
    int i = 60;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    btnTime=String.format("%ds",i);
                    btnCode.setText(btnTime);
                    Log.v(TAG,"btnTime:"+btnTime);
                    btnTime = "";
                    i--;

                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        topTitle.setText("忘记密码");
    }

    @OnClick({R.id.btn_code,R.id.top_back, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                new TimeThread().start();//一秒刷新一次
                break;
            case R.id.top_back:
                finish();
                break;
            case R.id.btn_submit:
                SubmitData();//提交
                break;
        }
    }

    private void SubmitData() {
        String username = etMobileUsername.getText().toString();
        String password = etPasswordReset.getText().toString();
        String passwords = etPasswords.getText().toString();
        if (username.isEmpty() && password.isEmpty() && passwords.isEmpty()) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();

        } else if (!password.equals(passwords)) {
            Toast.makeText(this, "两次密码不相同", Toast.LENGTH_SHORT).show();
        } else {
            OkHttpUtils
                    .post(GlobalApp.BASE_URL)
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .postJson("{'code':'4','msg':{'yhm':'" + username + "','password':'" + password + "'}}")
                    .execute(new StringCallback() {
                        @Override
                        public void onResponse(boolean isFromCache, String s,
                                               Request request, @Nullable Response response) {
                            Log.v(TAG,"s:"+s);
                            Log.v(TAG,"request:"+request.toString());
                            Log.v(TAG,"response:"+response.toString());
                            try {
                                JSONObject jo = new JSONObject(s);
                                int code = Integer.valueOf(jo.getString("code"));
                                if (code == 0) {
                                    Toast.makeText(ResetPasswordActivity.this, "用户名不存在", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ResetPasswordActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(boolean isFromCache, Call call, @Nullable Response response,
                                            @Nullable Exception e) {
                            super.onError(isFromCache, call, response, e);
                            Log.v(TAG,"response:"+response.toString());
                            Log.v(TAG,"Exception:"+e.getMessage());
                            Toast.makeText(ResetPasswordActivity.this, "网络异常，请重新连接网络", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;

                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

}
