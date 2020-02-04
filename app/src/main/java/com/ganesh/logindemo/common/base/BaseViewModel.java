package com.ganesh.logindemo.common.base;


import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by Ganesh on 3/1/2018.
 */

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCleared() {
        super.onCleared();
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


}
