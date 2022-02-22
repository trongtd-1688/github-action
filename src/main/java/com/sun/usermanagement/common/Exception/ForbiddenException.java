package com.sun.usermanagement.common.Exception;

public class ForbiddenException extends UserManagementException {
    public ForbiddenException(ErrorObject error) {
        super(error);
    }
}
