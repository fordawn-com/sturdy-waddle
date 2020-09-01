package com.fordawn.application.exception;

public class SeriousException extends RuntimeException {

    public SeriousException() {
        super();
    }

    public SeriousException(String message) {
        super(message);
    }

    public SeriousException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeriousException(Throwable cause) {
        super(cause);
    }

    protected SeriousException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
