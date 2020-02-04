package com.ganesh.logindemo.view.login;


import com.ganesh.logindemo.common.base.BaseNavigator;

/**
 * Created by Ganesh on 3/1/2018.
 */



public interface LoginNavigator extends BaseNavigator {

    void onUsernameError();

    void onPasswordError();

    void onLoginSuccess();

    void onLoginFailed();

}
