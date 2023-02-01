package com.mindex.challenge.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(final String errorMsg) {
        super(errorMsg);
    }
    
}