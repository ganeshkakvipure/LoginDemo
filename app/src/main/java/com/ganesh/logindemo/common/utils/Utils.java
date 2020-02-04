package com.ganesh.logindemo.common.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ganesh.logindemo.common.base.BaseActivity;

public class Utils {

    private static volatile Utils INSTANCE;


    private Utils() {

    }

    public static Utils getInstance() {
        if (INSTANCE == null) {
            synchronized (Utils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Utils();
                }
            }
        }
        return INSTANCE;
    }


    public void showErrorMsg(Context mContext, String msg) {
        Toast toast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void hideKeypad(Context mContext) {
        View view = ((BaseActivity) mContext).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

}
