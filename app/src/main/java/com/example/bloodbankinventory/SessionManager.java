package com.example.bloodbankinventory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.HashMap;

public class SessionManager {
    static SharedPreferences pref;


    static SharedPreferences.Editor editor;


    static Context _context;


    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Pref";


    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String IS_LOGOUT = "IsLoggedOut";

    public static final String KEY_USERNAME = "username";


    public static final String KEY_PASSWORD = "password";

    public static final String login = "sudahlogin";
    final static String FileName = "BloodBankInventory";

    public SessionManager(Context context){
        _context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static String readSharedSetting(Context ctx, String username, String password){
        SharedPreferences sharepref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharepref.getString(username, password);
    }
    public static void saveSharedSetting(Context ctx, String username, String password){
        SharedPreferences sharePref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePref.edit();
        editor.putString(username, password);
        editor.apply();
    }
    public static void SharedPrefesSAVE(Context ctx, String username, String password){
        SharedPreferences prefs = ctx.getSharedPreferences("username", 0);
        SharedPreferences.Editor prefsEDIT = prefs.edit();
        prefsEDIT.putString("username", username);
        prefsEDIT.commit();
    }

    public static void saveSPBoolean(String username, boolean value){
        editor.putBoolean(username, value);
        editor.commit();
    }

    public void createLoginSession(String username, String password){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_USERNAME, username);

        // Storing email in pref
        editor.putString(KEY_PASSWORD, password);

        // commit changes
        editor.commit();
    }
    public static void checkLogin(String login, boolean b){
        // Check login status
        if(isLoggedIn()){

            Intent i = new Intent(_context, home.class);

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            _context.startActivity(i);

        }
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_USERNAME, pref.getString(KEY_USERNAME, null));

        // user email id
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));

        // return user
        return user;
    }

    public static void logoutUser(String login, boolean b){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, login.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }
    public static boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    public static boolean isLoggedOut(){
        return pref.getBoolean(IS_LOGOUT, false);
    }
}
