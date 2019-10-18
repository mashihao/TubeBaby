package com.shsy.tubebaby.Converter;

/**
 * Created by MSH on 2017/11/23.
 */

public class NetWorkErrorException extends RuntimeException {

    public NetWorkErrorException() {
    }

    public NetWorkErrorException(String detailMessage) {
        super(detailMessage);
    }

    public NetWorkErrorException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public NetWorkErrorException(Throwable throwable) {
        super(throwable);
    }

}
