package com.ganesh.logindemo.model;


import androidx.databinding.BaseObservable;

public class CustomerModel extends BaseObservable {


    private String customerName;
    private String userId;

    public CustomerModel(String customerName, String userId) {
        this.customerName = customerName;
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
