package com.ganesh.logindemo.common.base;


import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Ganesh on 3/1/2018.
 */

public class BaseViewModel<N extends BaseNavigator> extends ViewModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected N mNavigator;


    public void setNavigator(N mNavigator) {
        this.mNavigator = mNavigator;
    }


    protected void checkError(Throwable throwable) {
            mNavigator.onError(throwable.getMessage());
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        mNavigator = null;
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


}
