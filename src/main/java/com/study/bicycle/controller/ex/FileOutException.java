package com.study.bicycle.controller.ex;

public class FileOutException extends ControllerException{
    private static final long serialVersionUID = 1L;

    public FileOutException() {
        super();
    }

    public FileOutException(String message) {
        super(message);
    }

    public FileOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOutException(Throwable cause) {
        super(cause);
    }

    protected FileOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
