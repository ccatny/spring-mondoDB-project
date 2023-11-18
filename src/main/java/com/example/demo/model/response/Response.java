package com.example.demo.model.response;

import java.io.Serializable;

public class Response<T> implements Serializable {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_FAIL = 500;

    public static final String MEG_SUCCESS = "Success";

    public static final String MEG_FAIL = "Fail";
    private int responseCode;
    private String responseMessage;
    private T responseData;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public static <T> Response<T> restResponse(T data, int code, String msg)
    {
        Response<T> response = new Response<>();
        response.setResponseCode(code);
        response.setResponseData(data);
        response.setResponseMessage(msg);
        return response;
    }

    public static <T> Response<T> restResponse(T data)
    {
        Response<T> response = new Response<>();
        response.setResponseCode(CODE_SUCCESS);
        response.setResponseData(data);
        response.setResponseMessage(null);
        return response;
    }

    public static <T> Response<T> success(T data)
    {
        return restResponse(data, CODE_SUCCESS, MEG_SUCCESS);
    }

    public static <T> Response<T> fail(String msg)
    {
        return restResponse(null, CODE_FAIL, msg);
    }
}
