package com.android.wen.cstp.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.base.BaseActivity;
import com.android.wen.cstp.pojo.User;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends BaseActivity {
    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.edit_name)
    EditText editName;
    @Bind(R.id.edt_id_card)
    EditText edtIdCard;
    @Bind(R.id.edit_mobile_phone)
    EditText editMobilePhone;
    //发证机关
    @Bind(R.id.tv_fzjg_reg)
    TextView tvFzjg;
    //号牌种类
    @Bind(R.id.tv_hpzl_reg)
    TextView tvHpzl;
    //号牌号码
    @Bind(R.id.et_hphm_reg)
    EditText etHphm;

    @Bind(R.id.tv_unit_reg)
    TextView tvMobileUnit;
    @Bind(R.id.et_mobile_password)
    EditText etMobilePassword;
    @Bind(R.id.et_mobile_passwords)
    EditText etMobilePasswords;
    @Bind(R.id.register_agree)
    CheckBox registerAgree;
    @Bind(R.id.tv_bank_car)
    EditText etBankCar;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;
    @Bind(R.id.tv_location_now)
    TextView tvLocationNow;

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

    public void Submit() {
        if (editName.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写真实姓名", Toast.LENGTH_LONG).show();
            return;
        }
        if (edtIdCard.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写身份证号码", Toast.LENGTH_LONG).show();
            return;
        }
        if (editMobilePhone.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvMobileUnit.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写工作单位", Toast.LENGTH_LONG).show();
            return;
        }
        if (etMobilePassword.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (etMobilePasswords.getText().toString().isEmpty()) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (!etMobilePassword.getText().toString().equals(etMobilePasswords.getText().toString())) {
            Toast.makeText(this, "两次密码不同", Toast.LENGTH_LONG).show();
            return;
        }
        if (registerAgree.getText().toString().isEmpty()) {
            Toast.makeText(this, "请阅读注意事项", Toast.LENGTH_LONG).show();
            return;
        }
        if (etBankCar.getText().toString().isEmpty()) {
            Toast.makeText(this, "银行卡号不能为空（此为举报奖励用）", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvSex.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvBirthday.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择出生日期", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvLocationNow.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择现住址", Toast.LENGTH_LONG).show();
            return;
        }
        //提交数据
        PostData();
    }

    public void PostData() {
        User user = new User();
        //名字
        user.setUsername(getResult(editName));
        //密码
        user.setPassword(getResult(etMobilePassword));
        // 国际移动设备身份码--终端号
        String imei = "";
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        imei = String.valueOf(tm.getSimState());
        user.setDevice(imei);
        //车号
        user.setCarNum(getResult(tvFzjg) + getResult(tvHpzl) + getResult(etHphm));
        //银行卡号
        user.setBcankCar(getResult(etBankCar));
        //电话号码
        user.setPhone(getResult(editMobilePhone));
        //身份证号码
        user.setIdCard(getResult(edtIdCard));
        //性别
        user.setSex(getResult(tvSex));
        //出生日期
        user.setBirthday(getResult(tvBirthday));
        //现住址
        user.setLocationNow(getResult(tvLocationNow));
        //工作单位
        user.setUnit(getResult(tvMobileUnit));
        //创建时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());//获取当前时间
        String nowDate = formatter.format(date);
        user.setTime(nowDate);

        user.toString();
        
        OkHttpUtils.post(GlobalApp.BASE_URL)
                .params("name", user.getUsername())
                .params("password", user.getPassword())
                .params("device", user.getDevice())
                .params("carnum", user.getCarNum())
                .params("bcankcar", user.getBcankCar())
                .params("phone", user.getPhone())
                .params("idcard", user.getIdCadr())
                .params("sex", user.getSex())
                .params("birthday", user.getBirthday())
                .params("locationnow", user.getLocationNow())
                .params("Unit", user.getUnit())
                .params("nowDate", user.getTime()).execute(new StringCallback() {
            @Override
            public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {

            }

            @Override
            public void onAfter(boolean isFromCache, @Nullable String s, Call call, @Nullable Response response, @Nullable Exception e) {
                super.onAfter(isFromCache, s, call, response, e);
                Log.v("RegisterActivity",s);
            }
        });

    }

    private String getResult(EditText et) {
        return et.getText().toString();
    }

    private String getResult(TextView tv) {
        return tv.getText().toString();
    }
}
