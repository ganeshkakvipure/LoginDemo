package com.ganesh.logindemo.common.repository;


import com.ganesh.logindemo.model.request.LoginRequestModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DataRepository {

    private static volatile DataRepository INSTANCE;


    private DataRepository() {

    }

    public static DataRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Call Login API and send Customer Model to UI on success
     *
     * @param loginRequestModel
     * @param success
     * @param error
     * @return Disposable
     */


    public Disposable authenticateUser(LoginRequestModel loginRequestModel, Consumer<Boolean> success, Consumer<Throwable> error) {
        return checkLogin(loginRequestModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, error);
    }


    /**
     * validate user credentials.
     *
     * @param loginRequestModel
     * @return Observable<Boolean>
     */
    private Observable<Boolean> checkLogin(LoginRequestModel loginRequestModel) {

        return Observable.create(emitter -> {
            try {
                String userName = "test";
                String password = "123";
                emitter.onNext((userName.equals(loginRequestModel.getUserName()) && password.equals(loginRequestModel.getPassword())));
            } catch (Exception ex) {
                emitter.onError(ex);
            }
        });


    }

}
