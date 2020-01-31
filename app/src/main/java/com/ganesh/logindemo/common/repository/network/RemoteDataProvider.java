package com.ganesh.logindemo.common.repository.network;


import com.ganesh.logindemo.model.CustomerModel;
import com.ganesh.logindemo.model.request.LoginRequestModel;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Ganesh on 23/05/17.
 */
public interface RemoteDataProvider {


    Disposable authenticateUser(LoginRequestModel loginRequestModel,
                                Consumer<CustomerModel> success,
                                Consumer<Throwable> error);



}
