package com.ganesh.logindemo.common.base;

/**
 * Created by Ganesh on 23/05/17.
 */

public interface UICallbacks {

    int getLayoutID();

    void onBinding();

    Class getViewModel();

    BaseNavigator getNavigatorReference();


}
