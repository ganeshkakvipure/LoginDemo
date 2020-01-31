package com.ganesh.logindemo.model.request;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class LoginRequestModel extends BaseObservable {


    private String userName;

    private String password;


    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(com.ganesh.logindemo.BR.userName);

    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(com.ganesh.logindemo.BR.password);
    }

}