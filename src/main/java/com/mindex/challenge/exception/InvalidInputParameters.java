package com.mindex.challenge.exception;

public class InvalidInputParameters extends RuntimeException {

    public InvalidInputParameters(final String errorMsg) {
        super(errorMsg);
    }
    
}
