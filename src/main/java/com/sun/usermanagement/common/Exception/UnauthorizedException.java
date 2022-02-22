package com.sun.usermanagement.common.Exception;

public class UnauthorizedException extends UserManagementException {

    public UnauthorizedException(ErrorObject errorObject) {
        super(errorObject);
    }
}
