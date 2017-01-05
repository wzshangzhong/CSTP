package com.android.wen.cstp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.edt_id_card_no)
    EditText edtIdCardNo;
    @Bind(R.id.edit_mobile_phone)
    EditText editMobilePhone;
    @Bind(R.id.tv_mobile_unit)
    TextView tvMobileUnit;
    @Bind(R.id.et_mobile_password)
    EditText etMobilePassword;
    @Bind(R.id.et_mobile_passwords)
    EditText etMobilePasswords;
    @Bind(R.id.register_agree)
    CheckBox registerAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        topTitle.setText("注册");
    }

    @OnClick({R.id.top_back, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.btn_register:
                Submit();
                break;
        }
    }

    private void Submit() {
        if(editName.getText().toString().isEmpty()){
            Toast.makeText(this,"请填写真实姓名",Toast.LENGTH_LONG).show();
            return;
        }
        if(edtIdCardNo.getText().toString().isEmpty()){
            Toast.makeText(this,"请填写身份证号码",Toast.LENGTH_LONG).show();
            return;
        }if(editMobilePhone.getText().toString().isEmpty()){
            Toast.makeText(this,"请填写手机号码",Toast.LENGTH_LONG).show();
            return;
        }
        if(tvMobileUnit.getText().toString().isEmpty()){
            Toast.makeText(this,"请填写工作单位",Toast.LENGTH_LONG).show();
            return;
        }
        if(etMobilePassword.getText().toString().isEmpty()){
            Toast.makeText(this,"请填写密码",Toast.LENGTH_LONG).show();
            return;
        }
        if(etMobilePasswords.getText().toString().isEmpty()){
            Toast.makeText(this,"请确认密码",Toast.LENGTH_LONG).show();
            return;
        }
        if(!etMobilePassword.getText().toString().equals(etMobilePasswords.getText().toString())){
            Toast.makeText(this,"两次密码不同",Toast.LENGTH_LONG).show();
            return;
        }
        if(registerAgree.getText().toString().isEmpty()){
            Toast.makeText(this,"请阅读注意事项",Toast.LENGTH_LONG).show();
            return;
        }
        //提交数据
    }
}
