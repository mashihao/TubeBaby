package com.shsy.tubebaby.network;

import java.io.Serializable;

/**
 * 实体类
 */

public class ResponseRetrofit<T> implements Serializable {

    private boolean isSuccess = false;
    private int errorCode = 0;
    private String message = "";
    private T result;

    @Override
    public String toString() {
        return "ResponseRetrofit{" +
                "isSuccess=" + isSuccess +
                ", errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
