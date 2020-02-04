package com.ganesh.logindemo.view.splash;


import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;

import com.ganesh.logindemo.R;
import com.ganesh.logindemo.common.base.BaseActivity;
import com.ganesh.logindemo.common.base.BaseNavigator;
import com.ganesh.logindemo.common.base.BaseViewModel;
import com.ganesh.logindemo.common.utils.SPManager;
import com.ganesh.logindemo.databinding.ActivitySplashScreenBinding;
import com.ganesh.logindemo.view.home.HomeActivity;
import com.ganesh.logindemo.view.login.LoginActivity;

public class SplashScreenActivity extends BaseActivity<ActivitySplashScreenBinding, BaseViewModel>  {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;


    @Override
    public int getLayoutID() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_splash_screen;
    }

    @Override
    public void onBinding() {
        mContext = SplashScreenActivity.this;


        /*
         * Showing splash screen with a timer. This will be useful when you
         * want to show case your app logo / company
         */
        new Handler().postDelayed(() -> {

            SPManager spManager = SPManager.getInstance();
            if (spManager.isLogin()) { // if user logged in

                startActivity(new Intent(mContext, HomeActivity.class));

            } else {
                startActivity(new Intent(mContext, LoginActivity.class));
            }

            SplashScreenActivity.this.finish();
        }, SPLASH_TIME_OUT);
    }

    @Override
    public Class getViewModel() {
        return BaseViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return null;
    }

}
