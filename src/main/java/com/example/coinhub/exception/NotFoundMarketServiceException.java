package com.example.coinhub.exception;

public class NotFoundMarketServiceException extends RuntimeException {

    public NotFoundMarketServiceException(String message) {
        super(message);
    }

    public NotFoundMarketServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundMarketServiceException(Throwable cause) {
        super(cause);
    }

    public NotFoundMarketServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
