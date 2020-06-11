package com.study.bicycle.controller.ex;

public class EmptyListException extends ControllerException{
    private static final long serialVersionUID = 1L;

    public EmptyListException() {
        super();
    }

    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyListException(Throwable cause) {
        super(cause);
    }

    protected EmptyListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
