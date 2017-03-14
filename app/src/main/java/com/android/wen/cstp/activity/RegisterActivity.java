package com.android.wen.cstp.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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
import com.android.wen.cstp.view.DateTimePickDialogUtil;
import com.google.gson.Gson;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.MediaType;
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
    @Bind(R.id.tv_location_now)
    TextView tvLocationNow;
    @Bind(R.id.edit_siren)
    EditText etSiren;//警号或用户名
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_birthday)
    TextView tvBirthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        topTitle.setText("注册");
    }

    @OnClick({R.id.top_back, R.id.btn_register, R.id.tv_fzjg_reg, R.id.tv_hpzl_reg, R.id.tv_unit_reg, R.id.tv_sex, R.id.tv_birthday, R.id.txt_register_protocol})
    public void onClick(View view) {
        AlertDialog.Builder builder;
        switch (view.getId()) {
            case R.id.top_back://退回
                finish();
                break;
            case R.id.btn_register://提交
                if (registerAgree.isChecked()) {
                    Submit();
                } else {
                    Toast.makeText(RegisterActivity.this,"请确认用户协议是否阅读",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.tv_fzjg_reg://发证机关
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.fzjgs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvFzjg.setText(GlobalApp.fzjgs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_hpzl_reg://选择号牌类型
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.hpzls, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvHpzl.setText(GlobalApp.hpzls[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_unit_reg://选择单位
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.units, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvMobileUnit.setText(GlobalApp.units[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_sex://选择性别
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.sexs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvSex.setText(GlobalApp.sexs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_birthday://选择生日
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this);
                dateTimePicKDialog.dateTimePicKDialog(tvBirthday);
                break;
            case R.id.txt_register_protocol://显示协议
                builder = new AlertDialog.Builder(this);
                builder.setTitle("用户协议");
                builder.setMessage(GlobalApp.userPro);
                builder.create().show();
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
        NetPostData();
    }

    public void NetPostData() {
        User user = new User();
        //警号或用户名
        user.setYhm(getResult(etSiren));
        //名字
        user.setXm(getResult(editName));
        //密码
        user.setPassword(getResult(etMobilePassword));
        // 国际移动设备身份码--终端号
        String imei = "";
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        imei = String.valueOf(tm.getSimState());
        user.setImsi(imei);
        //号牌号码   A+12345
        user.setHphm(getResult(tvHpzl) + getResult(etHphm));
        //发证机关
        user.setFzjg(getResult(tvFzjg) + getResult(tvHpzl));
        //银行卡号
        user.setYhkh(getResult(etBankCar));
        //电话号码
        user.setLxdh(getResult(editMobilePhone));
        //身份证号码
        user.setSfzh(getResult(edtIdCard));
        //性别
        user.setXb(getResult(tvSex));
        //出生日期
        user.setCsrq(getResult(tvBirthday));
        //现住址
        user.setXzz(getResult(tvLocationNow));
        //工作单位
        user.setGzdw(getResult(tvMobileUnit));
        //创建时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());//获取当前时间
        String nowDate = formatter.format(date);
        user.setRbsj(nowDate);

        final Gson gson = new Gson();
        Log.v("RegisterActivity is User", "{'code':'1','msg':{user:" + gson.toJson(user) + "}}");
        OkHttpUtils.post(GlobalApp.BASE_URL)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .postJson("{'code':'1','msg':{user:" + gson.toJson(user) + "}}")
                .execute(new StringCallback() {
                    @Override
                    public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                        Log.v("RegisterActivity is post", s);
                        Log.v("RegisterActivity is request", request.toString());
                        Log.v("RegisterActivity is response", response.toString());

                        try {
                            //解读Json
                            JSONObject jo= new JSONObject(s);
                            Toast.makeText(RegisterActivity.this, jo.getString("msg"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response,
                                        @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Toast.makeText(RegisterActivity.this, "网络异常，请选择合适网络环境", Toast.LENGTH_LONG).show();
                        Log.v("RegisterActivity is error", response.toString());
                        Log.v("RegisterActivity is error", e.toString());
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
