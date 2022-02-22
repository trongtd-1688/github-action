package com.sun.usermanagement.common.Exception;

public class DataNotFoundException extends UserManagementException {

    public DataNotFoundException(ErrorObject errorObject) {
        super(errorObject);
    }
}
