package com.android.wen.cstp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.android.wen.cstp.pojo.User;

/**
 * SharedPreferences (选项存储/应用存储/参数存储)
 */
public class UserUtils {
    //选项存储 *********************************************
    private static final String PREFS_NAME = "cstp_settings";
    private static final int PREFS_MODE = Context.MODE_PRIVATE;

    public static void save(Context context, User user) {
        SharedPreferences preferences =
                context.getSharedPreferences(PREFS_NAME, PREFS_MODE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", 1);
        editor.putString("yhm", user.getYhm());
        editor.putString("lxdh",user.getLxdh());
        editor.putString("xm",user.getXm());
        editor.putString("imsi",user.getImsi());
        //editor.putString("password",user.getPassword());
        editor.putString("sfzh",user.getSfzh());
        editor.putString("gzdw",user.getGzdw());
        editor.putString("rbsj",user.getRbsj());
        editor.putString("bb",user.getBb());
       // editor.putString("yhm",user.getYhm());
        editor.putString("yhkh",user.getYhkh());
        editor.putString("hphm",user.getHphm());
        editor.putString("xb",user.getXb());
        editor.putString("csrq",user.getCsrq());
        editor.putString("xzz",user.getXzz());
        editor.putString("yhlx",user.getYhlx());
        editor.putString("yyzt",user.getYyzt());
        editor.putString("dwdm",user.getDwdm());
        editor.putString("khh",user.getKhh());
        editor.putString("unitcode",user.getUnitcode());
        editor.putString("fzjg",user.getFzjg());
        editor.putString("xxly",user.getXxly());
        editor.commit();
    }

    public static User getUser(Context context) {
        SharedPreferences preferences =
                context.getSharedPreferences(PREFS_NAME, PREFS_MODE);
        Integer id = preferences.getInt("id", 0);
        String yhm = preferences.getString("yhm", "");
        String lxdh = preferences.getString("lxdh","");
        String xm = preferences.getString("xm","");
        String imsi = preferences.getString("imsi","");
        String password = preferences.getString("password","");
        String sfzh = preferences.getString("sfzh","");
        String gzdw = preferences.getString("gzdw","");
        String rbsj = preferences.getString("rbsj","");
        String bb = preferences.getString("bb","");
       // String yhm = preferences.getString("yhm",user.getYhm());
        String yhkh = preferences.getString("yhkh","");
        String hphm = preferences.getString("hphm","");
        String xb = preferences.getString("xb","");
        String csrq = preferences.getString("csrq","");
        String xzz = preferences.getString("xzz","");
        String yhlx = preferences.getString("yhlx","");
        String yyzt = preferences.getString("yyzt","");
        String dwdm = preferences.getString("dwdm","");
        String khh = preferences.getString("khh","");
        String unitcode = preferences.getString("unitcode","");
        String fzjg = preferences.getString("fzjg","");
        String xxly = preferences.getString("xxly","");



        User user = new User();
        user.setId(id);
        user.setYhm(yhm);

        user.setYhm(yhm);
        user.setLxdh(lxdh);
        user.setXm(xm);
        user.setImsi(imsi);

        user.setSfzh(sfzh);
        user.setGzdw(gzdw);
        user.setRbsj(rbsj);
        user.setBb(bb);
        user.setYhkh(yhkh);
        user.setHphm(hphm);
        user.setXb(xb);
        user.setCsrq(csrq);
        user.setXzz(xzz);
        user.setYhlx(yhlx);
        user.setYyzt(yyzt);
        user.setDwdm(dwdm);
        user.setKhh(khh);
        user.setUnitcode(unitcode);
        user.setFzjg(fzjg);
        user.setXxly(xxly);

        return user;
    }

    // Toast *****************************************
    public static void showToast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, @StringRes int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
