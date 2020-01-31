package com.ganesh.logindemo.common.repository;


import android.text.TextUtils;

import com.ganesh.logindemo.R;
import com.ganesh.logindemo.common.base.App;
import com.ganesh.logindemo.common.repository.db.LocalDataProvider;
import com.ganesh.logindemo.common.repository.network.ApiInterface;
import com.ganesh.logindemo.common.repository.network.RemoteDataProvider;
import com.ganesh.logindemo.common.repository.network.exceptions.NoInternetException;
import com.ganesh.logindemo.common.utils.Utils;
import com.ganesh.logindemo.model.CustomerModel;
import com.ganesh.logindemo.model.request.LoginRequestModel;
import com.ganesh.logindemo.model.response.BaseResponse;
import com.ganesh.logindemo.model.response.BaseResponseList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DataRepository implements RemoteDataProvider, LocalDataProvider {

    private static final String TAG = DataRepository.class.getSimpleName();

    private static volatile DataRepository INSTANCE;
    private ApiInterface mServices;

    private Utils utils;

    private DataRepository() {

       // mServices = ApiClient.getClient();
        utils = Utils.getInstance();
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


    private void handleResponse(BaseResponse baseResponse, Consumer<Throwable> error) {
        try {
            if (TextUtils.isEmpty(baseResponse.getResponseMessage())) {
                error.accept(new Throwable(App.getContext().getString(R.string.server_error)));
            } else {
                error.accept(new Throwable(baseResponse.getResponseMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleResponse(BaseResponseList baseResponse, Consumer<Throwable> error) {
        try {
            if (TextUtils.isEmpty(baseResponse.getResponseMessage()))
                error.accept(new Throwable(App.getContext().getString(R.string.server_error)));
            else
                error.accept(new Throwable(baseResponse.getResponseMessage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void noInternetAvailable(Consumer<Throwable> error) {
        try {
            error.accept(new NoInternetException(App.getContext().getString(R.string.no_internet_connection_msg)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Disposable getDefaultDisposable() {
        return new Disposable() {
            @Override
            public void dispose() {

            }

            @Override
            public boolean isDisposed() {
                return false;
            }
        };
    }

    private boolean isNetworkAvailable() {
        return utils.isNetworkAvailable(App.getContext());
    }


    @Override
    public Disposable authenticateUser(LoginRequestModel loginRequestModel, Consumer<CustomerModel> success, Consumer<Throwable> error) {
        if (isNetworkAvailable()) {
            return checkLogin(loginRequestModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responseModel -> {
                        if ("200".equals(responseModel.getResponseCode())) {
                            success.accept(responseModel.getData());
                        } else {
                            handleResponse(responseModel, error);
                        }
                    }, error);
        } else {
            noInternetAvailable(error);
            return getDefaultDisposable();
        }
    }


    /**
     * suppose this is API call from
     * @param loginRequestModel
     * @return
     */
    private Observable<BaseResponse<CustomerModel>> checkLogin (LoginRequestModel  loginRequestModel){

       return Observable.create(new ObservableOnSubscribe<BaseResponse<CustomerModel>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseResponse<CustomerModel>> emitter) throws Exception {
                try{
                    String userName="test";
                    String password="123";
                    BaseResponse<CustomerModel> response=new BaseResponse<>();
                    CustomerModel customerModel;
                    if(userName.equals(loginRequestModel.getUserName()) && password.equals(loginRequestModel.getPassword())){
                        customerModel=new CustomerModel("Test User","123");
                        response.setResponseCode("200");
                        response.setResponseMessage("Login Success");
                        response.setData(customerModel);
                    }else {
                        response.setResponseCode("201");
                        response.setResponseMessage("Login Failed");
                    }
                    emitter.onNext(response);
                }catch (Exception ex){
                    emitter.onError(ex);
                }
            }
        });



    }

}
