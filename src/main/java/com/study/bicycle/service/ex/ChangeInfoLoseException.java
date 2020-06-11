package com.study.bicycle.service.ex;

public class ChangeInfoLoseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ChangeInfoLoseException() {
        super();
    }

    public ChangeInfoLoseException(String message) {
        super(message);
    }

    public ChangeInfoLoseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChangeInfoLoseException(Throwable cause) {
        super(cause);
    }

    protected ChangeInfoLoseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
