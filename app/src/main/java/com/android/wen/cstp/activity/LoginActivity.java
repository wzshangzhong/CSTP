package com.android.wen.cstp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.wen.cstp.R;
import com.android.wen.cstp.pojo.User;
import com.android.wen.cstp.util.UserUtils;

import java.util.HashSet;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.et_user_name)
    EditText etUserName;
    @Bind(R.id.et_user_psaaword)
    EditText etUserPsaaword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etUserName.setText(UserUtils.getUser(this).getUsername());//默认用户名为缓存用户名
    }

    @OnClick({R.id.login, R.id.login_register,R.id.login_forget_pss})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                //判断是否登陆成功
                // 模拟数据库中的用户
                Set<String> names = new HashSet<>();
                names.add("15007311111");
                names.add("13007311111");
                names.add("17007311111");
                String username = etUserName.getText().toString();
                String password = etUserPsaaword.getText().toString();
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                if (TextUtils.isEmpty(username) || password.trim().length() == 0) {
                    Toast.makeText(this, "用户名或密码不能为空.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (names.contains(username)) {
                    // 1.保存用户设置到 选项存储
                    UserUtils.save(this, user);
                    // 2.跳转至主活动 (MainActivity)
                    startActivity(new Intent(this, MainActivity.class));
                    this.finish();
                } else {
                    Toast.makeText(this, "错误的用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
            case R.id.login_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.login_forget_pss:
                //忘记密码
                startActivity(new Intent(this,ResetPasswordActivity.class));
                break;

        }
    }
}
