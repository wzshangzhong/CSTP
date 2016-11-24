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
        editor.putString("username", user.getUsername());
        editor.putString("imgUser", user.getImgUserPath());
        editor.commit();
    }

    public static User getUser(Context context) {
        SharedPreferences preferences =
                context.getSharedPreferences(PREFS_NAME, PREFS_MODE);
        Integer id = preferences.getInt("id", 0);
        String username = preferences.getString("username", "");
        String imgUserString = preferences.getString("imgUser", "");
        User user = new User(id, username, "", imgUserString);
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
