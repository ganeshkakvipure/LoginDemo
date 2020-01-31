package com.ganesh.logindemo.common.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.ganesh.logindemo.common.base.App;

/**
 * Created by Ganesh on 23/11/17.
 */
public class SPManager {
    private static SPManager myManager;
    private static SharedPreferences s;

    private String IS_LOGIN = "isLogin";
    private String CUSTOMER_NAME = "customerName";
   private String  USER_ID="userId";


    private SPManager(Context context) {
        if (s == null)
            s = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static SPManager getInstance() {
        return myManager != null ? myManager : new SPManager(App.getContext());
    }

    private void saveString(String key, String data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putString(key, data);
        editor.apply();
    }

    private void saveInt(String key, int data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putInt(key, data);
        editor.apply();
    }

    private void saveBoolean(String key, boolean data) {
        SharedPreferences.Editor editor = s.edit();
        editor.putBoolean(key, data);
        editor.apply();
    }

    private void delete(String key) {
        SharedPreferences.Editor editor = s.edit();
        //editor.putString(key, "null");
        editor.remove(key);
        //editor.clear();
        editor.apply();
    }

    private String retrieveString(String key) {
        return s.getString(key, "");
    }

    private int retrieveInt(String key) {
        return s.getInt(key, 0);
    }

    private boolean retrieveBool(String key) {
        return s.getBoolean(key, false);
    }

    private boolean retrieveBool(String key, boolean defaultValue) {
        return s.getBoolean(key, defaultValue);
    }




    public boolean isLogin() {
        return retrieveBool(IS_LOGIN);
    }

    public void setIsLogin(boolean isLogin) {
        saveBoolean(IS_LOGIN, isLogin);
    }


    public void logout() {
        SharedPreferences.Editor editor = s.edit();
        editor.clear();
        editor.apply();
    }


    public String getCustomerName() {
        return retrieveString(CUSTOMER_NAME);
    }

    public void setCustomerName(String customerName) {
        saveString(CUSTOMER_NAME, customerName);
    }



    public String getUserID() {
        return retrieveString(USER_ID);
    }

    public void setUserID(String userID) {
        saveString(USER_ID, userID);
    }


}