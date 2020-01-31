package com.ganesh.logindemo.common.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ganesh.logindemo.R;
import com.ganesh.logindemo.common.base.BaseActivity;

import java.text.DecimalFormat;

/**
 * Created by Ganesh on 23/11/2017.
 */

public class Utils {


    static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }



    public double parseQtyValue(double price) {

        return Double.parseDouble(new DecimalFormat("##.###").format(price));
    }


    public float parseFloatValue(String price) {
        float resPrice = 0.0f;
        try {
            resPrice = Float.parseFloat(getNotNullString(price));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resPrice;
    }

    public int parseIntValue(String value) {
        int resPrice = 0;
        try {
            resPrice = Integer.parseInt(getNotNullString(value));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resPrice;
    }

    public String getNotNullString(String value) {
        return value == null ? "" : value.trim();
    }

    public int getValidInt(String valule) {
        int res = 0;
        try {
            res = Integer.parseInt(getNotNullString(valule));
        } catch (Exception ex) {
            res = 0;
        }
        return res;
    }



    public boolean isNetworkAvailable(Context context) {

        if(context == null)  return false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true;
                    }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                        return true;
                    }
                }
            } else {

                try {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        Log.i("update_statut", "Network is available : true");
                        return true;
                    }
                } catch (Exception e) {
                    Log.i("update_statut", "" + e.getMessage());
                }
            }
        }
        Log.i("update_statut","Network is available : FALSE ");
        return false;
    }





    public void hideKeypad(Context mContext) {
        View view = ((BaseActivity) mContext).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showDialogWhenException(Context context) {
        Toast.makeText(context, context.getString(R.string.server_error), Toast.LENGTH_SHORT).show();
    }

    public void showNoInternetMsg(Context mContext) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showMessageDialog(mContext.getString(R.string.no_internet_connection),
                    mContext.getString(R.string.no_internet_connection_msg), Constants.DialogRequestCode.NO_INTERNET);
        } else {
            Toast.makeText(mContext, mContext.getString(R.string.no_internet_connection_msg), Toast.LENGTH_SHORT).show();
        }
    }

    public void showFailureMsg(Context context) {
        Toast.makeText(context, context.getString(R.string.failed_con_server), Toast.LENGTH_SHORT).show();
    }

    public void showErrorMsg(Context mContext, String msg) {
        if (mContext instanceof BaseActivity) {
            ((BaseActivity) mContext).showMessageDialog(mContext.getString(R.string.error_title), msg, Constants.DialogRequestCode.ON_ERROR);
        } else {
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        }
    }

    public void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public long getTimeStamp() {
        Long tsLong = System.currentTimeMillis();
        return tsLong;
    }

    public int getParceInt(String string) {
        int qty = 0;
        try {
            qty = Integer.parseInt(getNotNullString(string));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return qty;
    }

    public String parseIntToBoolenString(int flag) {
        return flag == 1 ? "true" : "false";
    }






}
