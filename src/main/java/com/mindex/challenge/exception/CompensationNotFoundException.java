package com.mindex.challenge.exception;

public class CompensationNotFoundException extends RuntimeException {

    public CompensationNotFoundException(final String errorMsg) {
        super(errorMsg);
    }
    
}