package com.study.bicycle.service.ex;

public class PhoneIsHaveException extends ServiceException{
    private static final long serialVersionUID = 1L;

    public PhoneIsHaveException() {
        super();
    }

    public PhoneIsHaveException(String message) {
        super(message);
    }

    public PhoneIsHaveException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneIsHaveException(Throwable cause) {
        super(cause);
    }

    protected PhoneIsHaveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
