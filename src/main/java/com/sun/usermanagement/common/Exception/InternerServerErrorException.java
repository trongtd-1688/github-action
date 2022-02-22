package com.sun.usermanagement.common.Exception;

public class InternerServerErrorException extends UserManagementException {

    public InternerServerErrorException(ErrorObject error) {
        super(error);
    }
}
