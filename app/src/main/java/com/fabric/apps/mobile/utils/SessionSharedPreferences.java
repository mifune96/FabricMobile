package com.fabric.apps.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Sampler;

public class SessionSharedPreferences {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

//    Context context;
//    int private_mode = 0;

    public static final int ID = 0;
//    public static final String ID = "ID";
    public static final String USER_NAME = "USERNAME";
    public static final String AccessToken = "ACCESSTOKEN";
    public static final String PREF_NAME = "LOGIN_USER";
    public static final String IS_LOGIN = "IS_LOGIN";

    public SessionSharedPreferences(Context ctx){
        preferences = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public void saveSPString(String keySP, String value) {
        editor.putString(keySP, value);
        editor.commit();
    }
    public void saveSPInt(int keySP, int value) {
        editor.putInt(String.valueOf(keySP), value);
        editor.commit();
    }

//    public void saveSPInt(String keySP, int value) {
//        editor.putInt(keySP, value);
//        editor.commit();
//    }

    public void saveSPBoolean(String keySP, boolean value) {
        editor.putBoolean(keySP, value);
        editor.commit();
    }
    public String getAccessToken() {
        return preferences.getString(AccessToken,"");
    }


//    public Integer getID(){
//        return preferences.getInt(String.valueOf(ID), 0); //udah gini doang coba tes dulu
//    }
    public int getID(){
        return preferences.getInt(String.valueOf(ID), 0);
    }



    public String getUSER_NAME(){
        return preferences.getString(USER_NAME, null);
    }

    public boolean getIS_LOGIN(){
        return preferences.getBoolean(IS_LOGIN,false);
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }




}
