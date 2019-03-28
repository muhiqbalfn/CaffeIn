package com.nuzul.caffein.dbSQLite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nuzul.caffein.MainActivity;
import com.nuzul.caffein.SignInActivity;

import java.util.HashMap;

public  class  SessionManagement{
    private SharedPreferences mSharedPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    int PRIVATE_MODE;

    private static final String PREF_NAME = "SharedPreferences";

    private static final String IS_LOGIN = "is_login";
    public static final String KEY_USER = "username";
    public static final String KEY_PASS = "password";

    public SessionManagement(Context mContext) {
        this.mContext = mContext;
        mSharedPref = this.mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPref.edit();
    }

    //untuk menyimpan SharedPreferences
    public void createLoginSession(String user, String pass) {
        // Storing login value as TRUE
        mEditor.putBoolean(IS_LOGIN, true);

        mEditor.putString(KEY_USER, user);
        mEditor.putString(KEY_PASS, pass);
        mEditor.commit();
    }

    //untuk mendapatkan informasi user
    public HashMap<String, String> getUserInformation() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USER, mSharedPref.getString(KEY_USER, null));
        user.put(KEY_PASS, mSharedPref.getString(KEY_PASS, null));
        return user;
    }

    //jika sudah login
    public boolean isLoggedIn() {
        return mSharedPref.getBoolean(IS_LOGIN, false);
    }

    //jika belum login
    public void checkIsLogin() {
        if (!isLoggedIn()) {
            // user is not logged in redirect to MainActivity
            Intent i = new Intent(mContext, SignInActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
        }
    }

    public void logoutUser(){
        mEditor.clear();
        mEditor.commit();

        Intent i = new Intent(mContext, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(i);
    }

}