package com.ganesh.logindemo.model.response;


import androidx.databinding.ObservableArrayList;

public class BaseResponseList<T> {


    private String responseCode;


    private String responseMessage;

    private ObservableArrayList<T> data;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ObservableArrayList<T> getData() {
        return data;
    }

    public void setData(ObservableArrayList<T> data) {
        this.data = data;
    }
}
