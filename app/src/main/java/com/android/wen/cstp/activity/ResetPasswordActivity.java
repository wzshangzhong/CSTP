package com.android.wen.cstp.activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends BaseActivity {

    @Bind(R.id.btn_code)
    Button btnCode;
    @Bind(R.id.top_title)
    TextView topTitle;
    //线程msgKey值
    private static final int msgKey1 = 1;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    int i = 60;
                    btnCode.setText(String.format("获取验证码（%is）",i));
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

    @OnClick({R.id.btn_code,R.id.top_back,R.id.btn_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_code:
                new TimeThread().start();//一秒刷新一次
                break;
            case R.id.top_back:
                finish();
                break;
            case R.id.btn_reset:
                //提交
                break;
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
