package com.sun.usermanagement.common.Exception;

public class DataConflictException extends UserManagementException {
    public DataConflictException(ErrorObject errorObject) {
        super(errorObject);
    }
}
