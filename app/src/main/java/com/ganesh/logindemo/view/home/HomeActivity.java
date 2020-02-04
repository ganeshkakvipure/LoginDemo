package com.ganesh.logindemo.view.home;


import android.view.Menu;

import com.ganesh.logindemo.R;
import com.ganesh.logindemo.common.base.BaseActivity;
import com.ganesh.logindemo.common.base.BaseNavigator;
import com.ganesh.logindemo.common.base.BaseViewModel;
import com.ganesh.logindemo.common.utils.SPManager;
import com.ganesh.logindemo.databinding.ActivityHomeBinding;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, BaseViewModel>  {

    @Override
    public int getLayoutID() {
        return R.layout.activity_home;
    }

    @Override
    public void onBinding() {
        setToolbar(mBinding.toolbar, false, getString(R.string.title_activity_home));
        mBinding.setUserID(SPManager.getInstance().getUserID());
    }


    @Override
    public Class getViewModel() {
        return BaseViewModel.class;
    }

    @Override
    public BaseNavigator getNavigatorReference() {
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
