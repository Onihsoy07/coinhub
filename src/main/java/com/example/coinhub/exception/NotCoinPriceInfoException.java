package com.example.coinhub.exception;

public class NotCoinPriceInfoException extends RuntimeException {

    public NotCoinPriceInfoException(String message) {
        super(message);
    }

    public NotCoinPriceInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCoinPriceInfoException(Throwable cause) {
        super(cause);
    }

    public NotCoinPriceInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
