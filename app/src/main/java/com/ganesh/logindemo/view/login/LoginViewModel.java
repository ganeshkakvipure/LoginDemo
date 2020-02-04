package com.ganesh.logindemo.view.login;


import android.text.TextUtils;

import com.ganesh.logindemo.common.base.BaseViewModel;
import com.ganesh.logindemo.common.repository.DataRepository;
import com.ganesh.logindemo.common.utils.SPManager;
import com.ganesh.logindemo.model.request.LoginRequestModel;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by Ganesh on 3/1/2018.
 */

public class LoginViewModel extends BaseViewModel {

    private MutableLiveData<Status> statusMutableLiveData=new MutableLiveData<>();

    public MutableLiveData<Status> getStatusMutableLiveData() {
        return statusMutableLiveData;
    }

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
                    statusMutableLiveData.setValue(Status.SUCCESS);
                } else {
                    statusMutableLiveData.setValue(Status.FAILED);
                }

            }, throwable -> statusMutableLiveData.setValue(Status.ERROR)));


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
            statusMutableLiveData.setValue(Status.ERROR_USER_ID);
            return false;
        } else if (TextUtils.isEmpty(loginRequestModel.getPassword())) {
            statusMutableLiveData.setValue(Status.ERROR_PASSWORD);
            return false;
        }
        return true;
    }


    enum Status{
        ERROR,ERROR_USER_ID,ERROR_PASSWORD,SUCCESS,FAILED
    }

}
