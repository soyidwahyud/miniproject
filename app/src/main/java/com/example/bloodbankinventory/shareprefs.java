    package com.example.bloodbankinventory;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import static com.example.bloodbankinventory.SessionManager.pref;

public class shareprefs {
    final static String FileName = "BloodBankInventory";
    public static final String IS_LOGIN = "IsLoggedIn";
    static final String KEY_USER_TEREGISTER ="user", KEY_PASS_TEREGISTER ="pass";
    static final String KEY_USERNAME_SEDANG_LOGIN = "Username_logged_in";
    static final String KEY_STATUS_SEDANG_LOGIN = "Status_logged_in";

    Context context;
    public shareprefs(Context context) {
        this.context = context;
    }
    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }
    public void saveLoginDetails(String username, String password) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }
    public boolean isUserLogedOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isUsernameEmpty = sharedPreferences.getString("username", "").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("password", "").isEmpty();
        return isUsernameEmpty || isPasswordEmpty;
    }

    public boolean Logout(){
        SharedPreferences sp = context.getSharedPreferences("LoginDetails",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.remove("username");
        editor.remove("password");
        editor.commit();
        return true;
    }
    public static void clearLoggedInUser (Context context){
        SharedPreferences sharedpref = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref.edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.apply();
    }
    public static boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
    public static void LoggedInUser (Context context){
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(KEY_USERNAME_SEDANG_LOGIN);
        editor.remove(KEY_STATUS_SEDANG_LOGIN);
        editor.apply();
    }
}
