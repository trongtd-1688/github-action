package com.sun.usermanagement.common.Exception;

public class BadRequestException extends UserManagementException {
    public BadRequestException(ErrorObject errorObject) {
        super(errorObject);
    }
}
