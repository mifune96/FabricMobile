package com.fabric.apps.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionSharedPreferences {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

//    Context context;
//    int private_mode = 0;

    public static final String ID = "ID";
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
    public void saveSPInt(String keySP, int value) {
        editor.putInt(keySP, value);
        editor.commit();
    }
    public void saveSPBoolean(String keySP, boolean value) {
        editor.putBoolean(keySP, value);
        editor.commit();
    }
    public String getAccessToken() {
        return preferences.getString(AccessToken,"");
    }


    public String getID(){
        return preferences.getString(ID, "");
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
