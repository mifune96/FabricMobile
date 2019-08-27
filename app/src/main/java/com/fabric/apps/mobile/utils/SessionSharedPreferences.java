package com.fabric.apps.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionSharedPreferences {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

//    Context context;
//    int private_mode = 0;

    String ID = "ID";
    String USER_NAME = "USERNAME";
    String AccessToken = "ACCESSTOKEN";
    public static String PREF_NAME = "LOGIN_USER";
    public static String IS_LOGIN = "IS_LOGIN";

    public SessionSharedPreferences(Context ctx){
        preferences = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public String getAccessToken() {
        return preferences.getString(AccessToken, null);
    }

    public void setAccessToken(String accssToken) {
        editor = preferences.edit();
        editor.putString(AccessToken, accssToken).apply();
    }

    public String getID(){
        return preferences.getString(ID, null);
    }

    public void setID(String usrID) {
        editor = preferences.edit();
        editor.putString(ID, usrID).apply();
    }

    public String getUSER_NAME(){
        return preferences.getString(USER_NAME, null);
    }

    public void setUSER_NAME(String usrName){
        editor = preferences.edit();
        editor.putString(USER_NAME, usrName).apply();
    }

    public boolean isLogin(){
        return preferences.getBoolean(IS_LOGIN, false);
    }

    public void setLogin(boolean isLogin){
        editor = preferences.edit();
        editor.putBoolean(IS_LOGIN, isLogin).apply();
    }

    public void logout(){
        editor = preferences.edit();
        editor.clear().apply();
    }

//    public SessionSharedPreferences(Context context) {
//        this.context = context;
//        pref = context.getSharedPreferences(PREF_NAME, private_mode);
//        editor = pref.edit();
//    }
//
//    public void setJumlah(Float jumlah){
//        editor.putFloat("jumlah", jumlah);
//        editor.commit();
//    }
//
//    public Float getJumlah(){
//        return  pref.getFloat("jumlah",0.0f);
//    }
//
//    public void setQty(Float[] array, String arrayName) {
//        editor.putInt(arrayName +"_size", array.length);
//        for(int i=0;i<array.length;i++)
//            editor.putFloat(arrayName + "_" + i, array[i]);
//        editor.commit();
//    }
//
//    public Float[] getQty(String arrayName) {
//        int size = pref.getInt(arrayName + "_size", 0);
//        Float array[] = new Float[size];
//        for(int i=0;i<size;i++)
//            array[i] = pref.getFloat(arrayName + "_" + i, 0.0f);
//        return array;
//    }


}
