package com.android.wen.cstp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.wen.cstp.GlobalApp;
import com.android.wen.cstp.R;
import com.android.wen.cstp.pojo.User;
import com.android.wen.cstp.util.UserUtils;
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

public class ModifyActivity extends AppCompatActivity {
    @Bind(R.id.top_title)
    TextView topTitle;
    @Bind(R.id.edit_name_modify)
    EditText editNameModify;
    @Bind(R.id.edt_id_card_modify)
    EditText edtIdCardModify;
    @Bind(R.id.edit_mobile_phone_modify)
    EditText editMobilePhoneModify;
    //发证机关
    @Bind(R.id.tv_fzjg_modify)
    TextView tvFzjgModify;
    //号牌种类
    @Bind(R.id.tv_hpzl_modify)
    TextView tvHpzlModify;
    //号牌号码
    @Bind(R.id.et_hphm_modify)
    EditText etHphmModify;
    @Bind(R.id.tv_unit_modify)
    TextView tvMobileUnitModify;
    @Bind(R.id.et_password_modify)
    EditText etPasswordModify;
    @Bind(R.id.et_passwords_modify)
    EditText etPasswordsModify;
    @Bind(R.id.tv_bank_car_modify)
    EditText etBankCarModify;
    @Bind(R.id.tv_location_now_modify)
    TextView tvLocationNowModify;
    @Bind(R.id.edit_siren_modify)
    EditText etSirenModify;//警号或用户名
    @Bind(R.id.tv_sex_modify)
    TextView tvSexModify;
    @Bind(R.id.tv_birthday_modify)
    TextView tvBirthdayModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ButterKnife.bind(this);
        topTitle.setText("修改");
        initData();
    }

    private void initData() {
        editNameModify.setText(UserUtils.getUser(this).getYhm());
        edtIdCardModify.setText(UserUtils.getUser(this).getSfzh());
        editMobilePhoneModify.setText(UserUtils.getUser(this).getLxdh());
        tvFzjgModify.setText(UserUtils.getUser(this).getFzjg().substring(0,1));
        tvHpzlModify.setText(UserUtils.getUser(this).getHphm().substring(0,1));
        etHphmModify.setText(UserUtils.getUser(this).getHphm().substring(1,5));
        tvMobileUnitModify.setText(UserUtils.getUser(this).getGzdw());
        //etPasswordModify.setText(UserUtils.getUser(this).password);
        etBankCarModify.setText(UserUtils.getUser(this).getYhkh());
        tvLocationNowModify.setText(UserUtils.getUser(this).getXzz());
        etSirenModify.setText(UserUtils.getUser(this).getYhm());//警号或用户名
        tvSexModify.setText(UserUtils.getUser(this).getXb());//性别
        tvBirthdayModify.setText(UserUtils.getUser(this).getCsrq());

    }

    @OnClick({R.id.top_back, R.id.btn_modify, R.id.tv_fzjg_modify, R.id.tv_hpzl_modify, R.id.tv_unit_modify, R.id.tv_sex_modify, R.id.tv_birthday_modify})
    public void onClick(View view) {
        AlertDialog.Builder builder;
        switch (view.getId()) {
            case R.id.top_back://退回
                finish();
                break;
            case R.id.btn_modify://提交
                Submit();
                break;
            case R.id.tv_fzjg_modify://发证机关
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.fzjgs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvFzjgModify.setText(GlobalApp.fzjgs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_unit_modify://选择单位
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.units, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvMobileUnitModify.setText(GlobalApp.units[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_sex_modify://选择性别
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.sexs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvSexModify.setText(GlobalApp.sexs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_hpzl_modify://选择号牌类型
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.hpzls, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvHpzlModify.setText(GlobalApp.hpzls[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_unit_reg://选择单位
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.units, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvMobileUnitModify.setText(GlobalApp.units[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_sex://选择性别
                builder = new AlertDialog.Builder(this);
                builder.setItems(GlobalApp.sexs, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tvSexModify.setText(GlobalApp.sexs[which]);
                    }
                });
                builder.create().show();
                break;
            case R.id.tv_birthday_modify://选择生日
                DateTimePickDialogUtil dateTimePicKDialog = new DateTimePickDialogUtil(
                        this);
                dateTimePicKDialog.dateTimePicKDialog(tvBirthdayModify);
                break;

        }
    }

    public void Submit() {
        if (editNameModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写真实姓名", Toast.LENGTH_LONG).show();
            return;
        }
        if (edtIdCardModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写身份证号码", Toast.LENGTH_LONG).show();
            return;
        }
        if (editMobilePhoneModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvMobileUnitModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写工作单位", Toast.LENGTH_LONG).show();
            return;
        }
        if (etPasswordModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请填写密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (etPasswordsModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请确认密码", Toast.LENGTH_LONG).show();
            return;
        }
        if (!etPasswordModify.getText().toString().equals(etPasswordsModify.getText().toString())) {
            Toast.makeText(this, "两次密码不同", Toast.LENGTH_LONG).show();
            return;
        }
        if (etBankCarModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "银行卡号不能为空（此为举报奖励用）", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvSexModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvBirthdayModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择出生日期", Toast.LENGTH_LONG).show();
            return;
        }
        if (tvLocationNowModify.getText().toString().isEmpty()) {
            Toast.makeText(this, "请选择现住址", Toast.LENGTH_LONG).show();
            return;
        }
        //提交数据
        NetPostData();
    }

    public void NetPostData() {
        User user = new User();
        //警号或用户名
        user.setYhm(getResult(etSirenModify));
        //名字
        user.setXm(getResult(editNameModify));
        //密码
        user.setPassword(getResult(etPasswordModify));
        // 国际移动设备身份码--终端号
        String imei = "";
        TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        imei = String.valueOf(tm.getSimState());
        user.setImsi(imei);
        //号牌号码   A+12345
        user.setHphm(getResult(tvHpzlModify) + getResult(etHphmModify));
        //发证机关
        user.setFzjg(getResult(tvFzjgModify) + getResult(tvHpzlModify));
        //银行卡号
        user.setYhkh(getResult(etBankCarModify));
        //电话号码
        user.setLxdh(getResult(editMobilePhoneModify));
        //身份证号码
        user.setSfzh(getResult(edtIdCardModify));
        //性别
        user.setXb(getResult(tvSexModify));
        //出生日期
        user.setCsrq(getResult(tvBirthdayModify));
        //现住址
        user.setXzz(getResult(tvLocationNowModify));
        //工作单位
        user.setGzdw(getResult(tvMobileUnitModify));
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
                            JSONObject jo = new JSONObject(s);
                            Toast.makeText(ModifyActivity.this, jo.getString("msg"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(ModifyActivity.this, LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(boolean isFromCache, Call call, @Nullable Response response,
                                        @Nullable Exception e) {
                        super.onError(isFromCache, call, response, e);
                        Toast.makeText(ModifyActivity.this, "网络异常，请选择合适网络环境", Toast.LENGTH_LONG).show();
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
