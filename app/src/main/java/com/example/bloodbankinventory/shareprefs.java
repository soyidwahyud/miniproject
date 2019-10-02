package com.example.bloodbankinventory;

import android.content.Context;
import android.content.SharedPreferences;

import static com.example.bloodbankinventory.SessionManager.pref;

public class shareprefs {
    final static String FileName = "BloodBankInventory";
    public static final String IS_LOGIN = "IsLoggedIn";

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }
    public static boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
