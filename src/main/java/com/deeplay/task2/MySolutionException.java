package com.deeplay.task2;

public class MySolutionException extends Exception {
    private final MySolutionExceptionErrorCode error;

    public MySolutionException(MySolutionExceptionErrorCode error) {
        this.error = error;
    }

    public String getErrorCode() {
        return error.toString();
    }
}
