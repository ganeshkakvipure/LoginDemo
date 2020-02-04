package com.ganesh.logindemo.view.login;


import android.text.TextUtils;

import com.ganesh.logindemo.common.base.BaseViewModel;
import com.ganesh.logindemo.common.repository.DataRepository;
import com.ganesh.logindemo.common.utils.SPManager;
import com.ganesh.logindemo.model.request.LoginRequestModel;

/**
 * Created by Ganesh on 3/1/2018.
 */

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    /**
     * Valid user input and call login API
     *
     * @param loginRequestModel
     */
    public void onLoginClick(LoginRequestModel loginRequestModel) {
        if (isValid(loginRequestModel)) {
            compositeDisposable.add(DataRepository.getInstance().authenticateUser(loginRequestModel, isLoginSuccess -> {
                if (isLoginSuccess) {
                    SPManager.getInstance().setIsLogin(true);
                    SPManager.getInstance().setUserID(loginRequestModel.getUserName());
                    mNavigator.onLoginSuccess();
                } else {
                    mNavigator.onLoginFailed();
                }

            }, this::checkError));


        }

    }

    /**
     * Validate user input for username and password
     *
     * @param loginRequestModel
     * @return
     */

    private boolean isValid(LoginRequestModel loginRequestModel) {
        if (TextUtils.isEmpty(loginRequestModel.getUserName())) {
            mNavigator.onUsernameError();
            return false;
        } else if (TextUtils.isEmpty(loginRequestModel.getPassword())) {
            mNavigator.onPasswordError();
            return false;
        }
        return true;
    }



}
