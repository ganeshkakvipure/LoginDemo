package com.ganesh.logindemo.common.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.ganesh.logindemo.R;
import com.ganesh.logindemo.common.utils.SPManager;
import com.ganesh.logindemo.databinding.ToolbarBinding;
import com.ganesh.logindemo.view.login.LoginActivity;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;


/**
 * Created by Ganesh on 4/7/17.
 */

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity implements UICallbacks {

    protected T mBinding;
    protected V mViewModel;
    protected Context  mContext;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        mBinding = DataBindingUtil.setContentView(BaseActivity.this, getLayoutID());
        mViewModel = (V) new ViewModelProvider(BaseActivity.this).get(getViewModel());
        mViewModel.setNavigator(getNavigatorReference());
        onBinding();
    }


    /**
     * set toolbar
     *
     * @param toolbarBinding
     * @param showBackArrow
     * @param title
     */
    protected void setToolbar(ToolbarBinding toolbarBinding, boolean showBackArrow, String title) {
        setSupportActionBar(toolbarBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(showBackArrow);
        getSupportActionBar().setDisplayShowHomeEnabled(showBackArrow);
        getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(this, R.drawable.ic_back_arrow));
        toolbarBinding.txtToolbarTitle.setText(title);
        setTitle(title);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_logout:
                SPManager.getInstance().logout();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }






}