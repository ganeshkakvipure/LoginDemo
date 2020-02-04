package com.ganesh.logindemo.view.login;


import android.content.Intent;

import com.ganesh.logindemo.R;
import com.ganesh.logindemo.common.base.BaseActivity;
import com.ganesh.logindemo.common.utils.Utils;
import com.ganesh.logindemo.databinding.ActivityLoginBinding;
import com.ganesh.logindemo.model.request.LoginRequestModel;
import com.ganesh.logindemo.view.home.HomeActivity;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel>  {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, false, getString(R.string.title_activity_login));
        mBinding.setModel(new LoginRequestModel());
        mBinding.setViewModel(mViewModel);

        mViewModel.getStatusMutableLiveData().observe(this, status -> {
            switch (status){
                case SUCCESS:
                    onLoginSuccess();
                    break;

                case FAILED:
                    onLoginFailed();
                    break;

                case ERROR_USER_ID:
                    onUsernameError();
                    break;

                case ERROR_PASSWORD:
                    onPasswordError();
                    break;

                case ERROR:
                    onError();
                    break;
            }
        });


    }

    @Override
    public Class getViewModel() {
        return LoginViewModel.class;
    }



    public void onUsernameError() {
        Utils.getInstance().showErrorMsg(mContext, getString(R.string.error_empty_username));
    }


    public void onPasswordError() {
        Utils.getInstance().showErrorMsg(mContext, getString(R.string.error_empty_password));
    }

    public void onError() {
        Utils.getInstance().showErrorMsg(mContext, getString(R.string.error));
    }

    public void onLoginSuccess() {
        Intent intent;
        intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);
        finish();
    }


    public void onLoginFailed() {
        Utils.getInstance().hideKeypad(mContext);
        Utils.getInstance().showErrorMsg(mContext, getString(R.string.error_invalid_user));
    }



}

