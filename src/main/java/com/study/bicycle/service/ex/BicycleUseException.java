package com.study.bicycle.service.ex;

public class BicycleUseException extends ServiceException{
    private static final long serialVersionUID = 1L;

    public BicycleUseException() {
        super();
    }

    public BicycleUseException(String message) {
        super(message);
    }

    public BicycleUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BicycleUseException(Throwable cause) {
        super(cause);
    }

    protected BicycleUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
