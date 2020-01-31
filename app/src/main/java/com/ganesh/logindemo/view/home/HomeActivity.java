package com.ganesh.logindemo.view.home;


import android.view.Menu;

import com.ganesh.logindemo.R;
import com.ganesh.logindemo.common.base.BaseActivity;
import com.ganesh.logindemo.common.base.BaseNavigator;
import com.ganesh.logindemo.common.utils.SPManager;
import com.ganesh.logindemo.common.utils.Utils;
import com.ganesh.logindemo.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements HomeNavigator {
    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    public int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, false, getString(R.string.title_activity_home));
        mBinding.setCustomerName(SPManager.getInstance().getCustomerName());
    }


    @Override
    public Class getViewModel() {
        return HomeViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return this;
    }



    @Override
    public void onError(String errorMessage) {
        Utils.getInstance().showErrorMsg(mContext, errorMessage);
    }

    @Override
    public void onNoInternetConnection() {
        Utils.getInstance().showNoInternetMsg(mContext);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
